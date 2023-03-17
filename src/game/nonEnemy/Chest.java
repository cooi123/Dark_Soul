package game.nonEnemy;

import edu.monash.fit2099.engine.*;
import game.actions.OpeningChestAction;
import game.enums.Abilities;
import game.enums.Status;
import game.nonEnemy.NonEnemy;
import game.utils.Utils;

/**
 * Chest class, extends non-enemy and its untargetable
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see NonEnemy
 */
public class Chest extends NonEnemy {

    /**
     * The initial location of the chest
     */
    private Location initialLocation;

    /**
     * Constructor implementing Super constructor and initialising NonEnemy soul attribute
     */
    public Chest(Location initialLocation) {
        super("Chest", '&', Integer.MAX_VALUE, 0);
        addCapability(Status.DEAD);
        addCapability(Abilities.UNTARGETABLE);
    }

    /**
     * Allow player to interacte withe chest, return open chest action
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return The actions allowed
     */

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();

        int noOfToken = Utils.randomNumberGenerator(1, 4);
        actions.add(new OpeningChestAction(this, noOfToken));


        return actions;
    }

    /**
     * Does nothing
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction()
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        return new DoNothingAction();
    }

    /**
     * Displays the action of the chest for printing purposes
     *
     * @return A string of the action of the chest
     */
    @Override
    public String toString() {
        return name + " waiting to be open ";
    }
}
