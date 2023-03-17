package game.enemy;

import edu.monash.fit2099.engine.*;
import game.actions.RespawnAction;
import game.enums.Status;

/**
 * The boss of Design o' Souls
 * abstract class that extends enemy class- it contains the attack and follow.
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see Enemy
 */
public abstract class LordOfCinder extends Enemy {
    private Location respawnLocation;

    /**
     * Constructor
     * add capability of Has Dying message
     *
     * @param name The name of the lord of cinder
     * @param displayChar The display character for the lord of cinder on the map
     * @param hitPoints The lord of cinders starting hit points
     * @param soul The lord of cinders soul value
     * @param initialLocation The lord of cinders initial location
     */
    public LordOfCinder(String name, char displayChar, int hitPoints, int soul, Location initialLocation) {
        super(name, displayChar, hitPoints, soul);
        respawnLocation = initialLocation;
        addCapability(Status.HAS_DYING_MESSAGE);

    }

    /**
     * First check- if its resetting- respawn to initial location
     * 2nd check- if its dead- prints out dying message- drop cinder of a lord item
     * 3rd check- run super play turn for addtional checks
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (hasCapability(Status.RESET)) {
            setInitialCondition();
            return new RespawnAction(respawnLocation, this);

        }
        if (!hasCapability(Status.ENRAGE) && hitPoints < maxHitPoints / 2 && isConscious()) {
            addCapability(Status.ENRAGE);
            addCapability(Status.ACTIVATING_SKILL);
            String EmberMessage = " _____          _                ______                   \n" +
                    "|  ___|        | |               |  ___|                  \n" +
                    "| |__ _ __ ___ | |__   ___ _ __  | |_ ___  _ __ _ __ ___  \n" +
                    "|  __| '_ ` _ \\| '_ \\ / _ \\ '__| |  _/ _ \\| '__| '_ ` _ \\ \n" +
                    "| |__| | | | | | |_) |  __/ |    | || (_) | |  | | | | | |\n" +
                    "\\____/_| |_| |_|_.__/ \\___|_|    \\_| \\___/|_|  |_| |_| |_|\n" +
                    "                                                          \n" +
                    "                                                          ";
            display.println(EmberMessage);
        }
        if (!isConscious()) {
            String dyingMessage = " _        _______  _______  ______     _______  _______    _______ _________ _        ______   _______  _______    _______  _______  _        _        _______  _       \n" +
                    "( \\      (  ___  )(  ____ )(  __  \\   (  ___  )(  ____ \\  (  ____ \\\\__   __/( (    /|(  __  \\ (  ____ \\(  ____ )  (  ____ \\(  ___  )( \\      ( \\      (  ____ \\( (    /|\n" +
                    "| (      | (   ) || (    )|| (  \\  )  | (   ) || (    \\/  | (    \\/   ) (   |  \\  ( || (  \\  )| (    \\/| (    )|  | (    \\/| (   ) || (      | (      | (    \\/|  \\  ( |\n" +
                    "| |      | |   | || (____)|| |   ) |  | |   | || (__      | |         | |   |   \\ | || |   ) || (__    | (____)|  | (__    | (___) || |      | |      | (__    |   \\ | |\n" +
                    "| |      | |   | ||     __)| |   | |  | |   | ||  __)     | |         | |   | (\\ \\) || |   | ||  __)   |     __)  |  __)   |  ___  || |      | |      |  __)   | (\\ \\) |\n" +
                    "| |      | |   | || (\\ (   | |   ) |  | |   | || (        | |         | |   | | \\   || |   ) || (      | (\\ (     | (      | (   ) || |      | |      | (      | | \\   |\n" +
                    "| (____/\\| (___) || ) \\ \\__| (__/  )  | (___) || )        | (____/\\___) (___| )  \\  || (__/  )| (____/\\| ) \\ \\__  | )      | )   ( || (____/\\| (____/\\| (____/\\| )  \\  |\n" +
                    "(_______/(_______)|/   \\__/(______/   (_______)|/         (_______/\\_______/|/    )_)(______/ (_______/|/   \\__/  |/       |/     \\|(_______/(_______/(_______/|/    )_)\n" +
                    "                                                                                                                                                                        ";

            display.println(dyingMessage);

        }
        return super.playTurn(actions, lastAction, map, display);
    }


    /**
     * Only adds reset status and heal
     */
    @Override
    public void resetInstance() {
        this.heal(maxHitPoints);
        addCapability(Status.RESET);
    }


    /**
     * Heals to full health
     */
    @Override
    protected void setInitialCondition() {
        super.setInitialCondition();
    }
}
