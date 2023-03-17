package game.nonEnemy;

import edu.monash.fit2099.engine.*;
import game.actions.DyingAction;
import game.interfaces.Soul;
import game.playerItems.SoulObject;


/**
 * Abstract class extending Actor to provide require implementation for Actors that are not
 * enemies within the game
 *
 * @author Lab7_Team6
 * @see Actor
 * @version 1.0
 */
public abstract class NonEnemy extends Actor implements Soul {

    /**
     * An instance of the number of souls held by a non-enemy
     */
    SoulObject soul;

    /**
     * Constructor implementing Super constructor and initialising NonEnemy soul attribute
     *
     * @param name         The name of a non-enemy
     * @param displayChar  The display character of a non-enemy
     * @param hitPoints    The number of hit points of a non-enemy
     * @param newNoOfSouls The number of souls a non-enemy begins with
     */
    public NonEnemy(String name, char displayChar, int hitPoints, int newNoOfSouls) {
        super(name, displayChar, hitPoints);
        soul = new SoulObject(newNoOfSouls);
    }

    /**
     * A method representing a non-enemies turn within the game
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return if a non-enemy is not conscious, returns a Dying Action otherwise null
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            return new DyingAction(this);
        }
        return null;
    }

    /***
     * Method to transfer souls between Actors
     * Using getSoul method to get the value that are being transfered
     * getSoul could return negative when buying.
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
     * Method to identify the number of souls of a non-enemy
     *
     * @return Integer representing the number of souls of a non-enemy
     */
    public int getSoul() {
        return soul.getNoOfSouls();
    }

    /**
     * Method to increase the number of souls of a non-enemy
     *
     * @param souls number of souls to be incremented.
     * @return Boolean indicator to identify if the souls have been successfully increased
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
     * Method to decrease the number of souls of a non-enemy
     *
     * @param souls number souls to be deducted
     * @return Boolean indicator to identify if the souls have been successfully decreased
     */
    public boolean subtractSouls(int souls) {
        boolean isValid = false;
        if (soul.getNoOfSouls() >= souls) {
            soul.subtractSoul(souls);
            isValid = true;
        }
        return isValid;
    }

}
