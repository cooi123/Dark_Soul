package game.enemy;


import edu.monash.fit2099.engine.*;
import game.actions.RemoveActorAction;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.utils.Utils;

/**
 * A undead minion, extends Enemy
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Enemy
 */
public class Undead extends Enemy {

    /**
     * Constructor.
     * All Undead are represented by an 'u' and have 50 max hit points and 50 souls .
     *
     * @param name the name of this Undead
     */
    public Undead(String name) {
        super(name, 'u', 50, 50);
        behaviours.add(new WanderBehaviour());
        addCapability(Status.WANDERING);
    }


    /**
     * remove the undead
     */
    @Override
    public void resetInstance() {

        hurt(Integer.MAX_VALUE);

    }

    /**
     * Checks if its still wandering that randomly kills it using the remove actor actions;
     * otherwise used the supers play turn
     *
     * @param actions The possible actions that can be made by the undead
     * @param lastAction The last action made by an undead
     * @param map The game map the undead is on
     * @param display The I/O object to which messages may be written
     * @return mostly the supers play turn which is attacking/ wandering.
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.WANDERING) & Utils.randomBooleanGenerator(10)) {
            return new RemoveActorAction();
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Override the getIntrinsic weapon to change the base attack from 5 to 20;
     *
     * @return a modified punch
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20, "punches");

    }
}
