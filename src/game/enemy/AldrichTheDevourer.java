package game.enemy;

import edu.monash.fit2099.engine.*;
import game.playerItems.CinderOfALord;
import game.weapons.GameWeaponItem;
import game.behaviours.WeaponActiveBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.weapons.DarkmoonLongbow;

/**
 * Aldirch boss class extending lord of cinder
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see LordOfCinder
 */
public class AldrichTheDevourer extends LordOfCinder {
    /**
     * Constructor
     * add capability of Has Dying message
     *
     * @param initialLocation The initial location of Aldrich
     */
    public AldrichTheDevourer(Location initialLocation) {
        super("Aldrich the Devourere", 'A', 350, 5000, initialLocation);
        GameWeaponItem weapon = new DarkmoonLongbow();
        inventory.add(weapon);
        addCapability(Abilities.RANGEATTACK);
    }

    /**
     * Activates the ember found passive- checks if below half health, then heals 20%
     * When dies, drops cinder of lord
     * Injector for the tradeable item for cinder of a lord
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return playTurn method in LordOfCinder
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!hasCapability(Status.ENRAGE) && hitPoints < maxHitPoints / 2 && isConscious()) {
            addCapability(Status.ENRAGE);
            String EmberMessage = " _____          _                ______                   \n" +
                    "|  ___|        | |               |  ___|                  \n" +
                    "| |__ _ __ ___ | |__   ___ _ __  | |_ ___  _ __ _ __ ___  \n" +
                    "|  __| '_ ` _ \\| '_ \\ / _ \\ '__| |  _/ _ \\| '__| '_ ` _ \\ \n" +
                    "| |__| | | | | | |_) |  __/ |    | || (_) | |  | | | | | |\n" +
                    "\\____/_| |_| |_|_.__/ \\___|_|    \\_| \\___/|_|  |_| |_| |_|\n" +
                    "                                                          \n" +
                    "                                                          ";
            display.println(EmberMessage);
            double healing = hitPoints * 0.2;
            heal((int) healing);
            addCapability(Status.ACTIVATING_SKILL);
        }
        if (!isConscious()) {
            Item cinderOfAlord = new CinderOfALord(new DarkmoonLongbow());
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
        removeCapability(Status.RESET);
        removeCapability(Status.ENRAGE);
        removeCapability(Status.ACTIVATING_SKILL);

        behaviours.clear();
        behaviours.add(new WeaponActiveBehaviour());


        hitPoints = maxHitPoints;
        super.setInitialCondition();
    }
}
