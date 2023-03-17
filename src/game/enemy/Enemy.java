package game.enemy;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.RemoveActorAction;
import game.interfaces.RangeInteraction;
import game.weapons.weaponActiveAction.WindSlashAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.RangeAttackBehaviour;
import game.behaviours.WeaponActiveBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.playerItems.SoulObject;

import java.util.ArrayList;

/**
 * A base enemy class that contains basic behaviour such as following and attacking player and is a child class of Actor
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Actor
 */
public abstract class Enemy extends Actor implements Resettable, Soul {
    protected ArrayList<Behaviour> behaviours = new ArrayList<>();
    private Behaviour attackBehaviour;
    private Behaviour followBehaviour;
    private Behaviour rangeAttackBehaviour;
    private SoulObject soul;

    /**
     * Constructor.
     * initially all enemy has the behaviour of weapon active.
     * The weapon active will randomly return the active skills if the enemy weapon has any.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param soul        enemy's soul value
     */
    public Enemy(String name, char displayChar, int hitPoints, int soul) {
        super(name, displayChar, hitPoints);
        this.soul = new SoulObject(soul);
        addCapability(Status.ENEMY);
        registerInstance();
        behaviours.add(new WeaponActiveBehaviour());
    }

    /**
     * Provides the actions for player. Usually provides the attack action, however when player is disarmed it doesn't provide anything
     * It also adds in follow behaviour when player is on the adjacent squares.
     * Wandering status removed when enemy is chasing so that wandering behaviour will not return move actions
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        //For range attack

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !otherActor.hasCapability(Status.DISARMED)) {

            //if it see hostile opponent, adds player into follow behaviou
            addCapability(Status.CHASING);
            removeCapability(Status.WANDERING);
            //make sure that the behaviours are only added once.
            if (hasCapability(Abilities.RANGEATTACK) && rangeAttackBehaviour == null) {
                for (Item item : getInventory()) {
                    if (item instanceof RangeInteraction) {
                        rangeAttackBehaviour = new RangeAttackBehaviour(otherActor, (RangeInteraction) item);
                        behaviours.add(rangeAttackBehaviour);
                    }
                }
            }
            if (!otherActor.hasCapability(Abilities.RANGEATTACK)) {
                actions.add(new AttackAction(this, direction));
            }
            if (attackBehaviour == null) {
                attackBehaviour = new AttackBehaviour(otherActor);
                behaviours.add(attackBehaviour);

            }
            // add only once
            if (followBehaviour == null) {
                followBehaviour = new FollowBehaviour(otherActor);
                behaviours.add(followBehaviour);
            }
        }
        //For Wind Slash active, checks if the storm ruler if fully charged, and checks if the enemy is able to return the action
        //Makes the enemy return the active instead of the sword, because weapon doesn't have access the its target.
        if (otherActor.hasCapability(Status.FULLY_CHARGED) && hasCapability(Status.WEAK_AGAINST_STORM_RULER)) {
            actions.add(new WindSlashAction(this));
        }

        return actions;
    }

    /**
     * First checks- if the enemy is still alive, if not remove from map.
     * this is done for the undead reset, also for enemy that has dying message.
     * As the dying action only process the soul transfer and drop actions
     * <p>
     * Second checks-  for last actions
     * Third checks- cycle through the behaviour.
     * Behaviour is used for to make the enemy do certain action automatically.
     * if none of the condition checks, return do nothing.
     *
     * @param actions    Collection of possible actions that the enemy can make
     * @param lastAction The Action the enemy took last turn
     * @param map        The game map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return The action made
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            return new RemoveActorAction();
        }
        if (lastAction != null && lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        for (Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * For reset manager to checks if the actor is still on the map.
     *
     * @return true if this class exists.
     */
    @Override
    public boolean isExist() {
        return true;
    }

    /**
     * This is used to transfer soul from this enemy to the other actor.
     * Uses the add souls method in the receiver
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        if (soulObject.addSouls(soul.getNoOfSouls())) {
            subtractSouls(soul.getNoOfSouls());
        }
    }

    /**
     * getter for souls
     *
     * @return the number of soul
     */
    @Override
    public int getSoul() {
        return soul.getNoOfSouls();
    }

    /**
     * Checks if the soul input  is positive
     *
     * @param souls number of souls to be incremented.
     * @return true if its positive
     */
    public boolean addSouls(int souls) {
        boolean isValid = false;
        if (souls >= 0) {
            soul.addSoul(souls);
            isValid = true;

        }
        return isValid;
    }

    /**
     * @param souls number souls to be deducted
     * @return True if the actor has enough souls, false otherwise
     */
    public boolean subtractSouls(int souls) {
        boolean isValid = false;
        if (soul.getNoOfSouls() >= souls) {
            soul.subtractSoul(souls);
            isValid = true;
        }
        return isValid;
    }

    /**
     * Prints out the name, current health, its maximum health and the weapon.
     * Need to checks if it intrisicWeapon as theres no to String for that class
     *
     * @return log string.
     */
    @Override
    public String toString() {
        String weapon = "";
        if (getWeapon() instanceof IntrinsicWeapon) {
            weapon = "no weapon";
        } else {
            weapon = getWeapon().toString();
        }
        return String.format("%s(%d/%d)(%s) ", name, hitPoints, maxHitPoints, weapon);
    }

    /**
     * Method that other enemy can override when reset or when created.
     */
    protected void setInitialCondition() {
        attackBehaviour = null;
        followBehaviour = null;
        hitPoints = maxHitPoints;
    }

//
}

