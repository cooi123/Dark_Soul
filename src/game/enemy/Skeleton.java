package game.enemy;

import edu.monash.fit2099.engine.*;
import game.actions.ReviveActorAction;
import game.behaviours.WanderBehaviour;
import game.behaviours.WeaponActiveBehaviour;
import game.actions.RespawnAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul;
import game.utils.Utils;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;

/**
 * Skeleton class, extends Enemy
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Enemy
 */
public class Skeleton extends Enemy {

    /**
     * The skeletons initial location
     */
    private Location initialLocation;

    /**
     * Constructor.
     * Created from application
     * takes in the initial location for respawning
     * Randomly assign broadsword or giant axe to the inventory;
     *
     * @param name the name of the Actor
     */
    public Skeleton(String name, Location initialLocation) {
        super(name, 's', 100, 250);

        this.initialLocation = initialLocation;
        WeaponItem weapon;
        if (Utils.randomBooleanGenerator(50)) {
            weapon = new Broadsword();
        } else
            weapon = new GiantAxe();
        addItemToInventory(weapon);
        setInitialCondition();
    }

    /**
     * First check- Being Rest- Goes to the spawn location
     * Second check- ability to Revive and status of Revive- the transfer soul method contains rng that adds the status of revived
     * Last check - goes through the super play turns.
     *
     * @param actions The possible actions that can be made by the skeleton
     * @param lastAction The last action made by the skeleton
     * @param map The game map the skeleton is on
     * @param display The I/O object to which messages may be written
     * @return The playTurn() method in Enemy
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            if (hasCapability(Status.RESET)) {
                setInitialCondition();
                return new RespawnAction(initialLocation, new Skeleton("Skeleton", initialLocation));

            }

            if (hasCapability(Abilities.REVIVE) && hasCapability(Status.REVIVED)) {
                removeCapability(Abilities.REVIVE);
                addCapability(Status.WANDERING);
                hitPoints = maxHitPoints;
                return new ReviveActorAction(map.locationOf(this), this, new Corpse(name, '!', this));

            }

        }
        return super.playTurn(actions, lastAction, map, display);
    }


    /***
     * When reset, move to initial location
     * Add Status of Reset
     * DO large amount of damage so it goes through the isConscious chek
     * remove this skeleton but create a new one on the position
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
        hurt(Integer.MAX_VALUE);
    }

    /**
     * When the method runs, it is assumed that the skeleton is dead
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        if (hasCapability(Abilities.REVIVE) && Utils.randomBooleanGenerator(50)) {
            addCapability(Status.REVIVED);
        } else {
            super.transferSouls(soulObject);
        }

    }

    /**
     * Removes all additional status and abilities
     * Clears the behaviour and add initial behaviours and capability
     */
    @Override
    protected void setInitialCondition() {
        behaviours.clear();
        behaviours.add(new WanderBehaviour());
        behaviours.add(new WeaponActiveBehaviour());
        addCapability(Status.WANDERING);
        addCapability(Abilities.REVIVE);
        removeCapability(Status.RESET);
        removeCapability(Status.REVIVED);
    }
}
