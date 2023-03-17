package game.enemy;

import edu.monash.fit2099.engine.*;
import game.playerItems.CinderOfALord;
import game.behaviours.WeaponActiveBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.weapons.YhormsMachete;

/**
 * Yhorm the giant that extends lord of cinder
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see LordOfCinder
 */
public class YhormTheGiant extends LordOfCinder {

    /**
     * Constructor for YhormTheGiant
     */
    public YhormTheGiant(Location initialLocation) {
        super("Yhorm the Giant", 'Y', 500, 5000, initialLocation);
        setInitialCondition();
        inventory.add(new YhormsMachete());
    }

    /**
     * Checks whether Yhorm is conscious or not
     *
     * @return True if Yhrom is conscious, False otherwise
     */
    @Override
    public boolean isConscious() {

        return hitPoints > 0;
    }

    /**
     * 1st check- less than 50% health for passive- add status enrage and activate skill
     * Weapon behaviour will then return burn action when it has capability activating skill
     * 2nd check- stuns- then return do nothing. then remove stuns, only stuns for 1 turn
     *
     * @param actions    The possible actions that can be made by Yhorm The Giant
     * @param lastAction The last action made by Yhorm
     * @param map        The game map containing the Yhorm
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {


        if (this.hasCapability(Status.STUNNED) && isConscious()) {
            removeCapability(Status.STUNNED);
            return new DoNothingAction();
        }
        if (!isConscious()) {
            Item cinderOfAlord = new CinderOfALord(new YhormsMachete());
            map.locationOf(this).addItem(cinderOfAlord);
        }


        return super.playTurn(actions, lastAction, map, display);

    }

    /**
     * remove status
     * set initial status with health
     */
    @Override
    protected void setInitialCondition() {
        addCapability(Abilities.FIRE_RESISTANCE);
        addCapability(Status.WEAK_AGAINST_STORM_RULER);
        removeCapability(Status.STUNNED);
        removeCapability(Status.RESET);
        removeCapability(Status.ENRAGE);
        removeCapability(Status.ACTIVATING_SKILL);

        behaviours.clear();
        behaviours.add(new WeaponActiveBehaviour());


        hitPoints = maxHitPoints;
        super.setInitialCondition();
    }
}
