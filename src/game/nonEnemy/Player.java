package game.nonEnemy;

import edu.monash.fit2099.engine.*;
import game.ResetManager;
import game.actions.RespawnAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Tradeable;
import game.nonEnemy.NonEnemy;
import game.playerItems.CinderOfALord;
import game.playerItems.EstusFlaskItem;
import game.playerItems.SoulToken;
import game.weapons.Broadsword;
import game.weapons.DarkmoonLongbow;
import game.weapons.YhormsMachete;

/**
 * Player class represents a player in the game and is a child class of NonEnemy
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see NonEnemy
 */
public class Player extends NonEnemy implements Resettable, Tradeable {

    /**
     * An instance of the EstusFlask
     */
    private EstusFlaskItem EstusFlask;

    /**
     * An instance of the players previous location
     */
    private Location previousLocation;

    /**
     * An instance of the respawn location of the player
     */
    private static Location respawnLocation;

    /**
     * An instance of the menu displayed to the player
     */
    private final Menu menu = new Menu();

    /**
     * Constructor.
     * Contain broadsword
     * runs set initial condition and register the instance for reset;
     * <p>
     * the respawn location is fixed, for improvement, have a change respawn method
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     * @param respawn     inital location used for respaswn.
     */
    public Player(String name, char displayChar, int hitPoints, Location respawn) {
        super(name, displayChar, hitPoints, 10000);
        setInitialCondition();
        this.addItemToInventory(EstusFlask = new EstusFlaskItem("Estus Flask", 'E', false));
        this.addItemToInventory(new Broadsword());
        this.addItemToInventory(new CinderOfALord(new YhormsMachete()));
        this.addItemToInventory(new CinderOfALord(new DarkmoonLongbow()));
        addCapability(Abilities.VIEW_MENU);

        registerInstance();
        respawnLocation = respawn;
    }


    /**
     * Play turn handles the resetting, resting and dying of the player
     * 1st check - if is reseting or resetting- runs the set initial condition
     * 2nd check- if is dead- play turn checks it as there 2 ways player can die- damage from ground and enemy damage
     * Having isconscious checks here make sure that damage from ground e.g valley would be reflected and the dying message will print out
     * When Dead: adds capability of dead- soul token can be drop- runs reset manager create token of soul and transfer, drop it.
     * Then return respawn action with the respawn location.
     * <p>
     * 3rd check- last actions
     * final check- weapon active skills and all allowable actions to be printed.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return A menu display to the console
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        if (hasCapability(Status.RESET) || hasCapability(Status.RESTING)) {
            setInitialCondition();
        }

        if (!isConscious()) {
            this.addCapability(Status.DEAD);
            String dyingMessage = "                 _,.---._                                     .=-.-.   ,----.                .=-.-. \n" +
                    " ,--.-.  .-,--.,-.' , -  `.  .--.-. .-.-.         _,..---._  /==/_ /,-.--` , \\  _,..---._   /==/_ / \n" +
                    "/==/- / /=/_ //==/_,  ,  - \\/==/ -|/=/  |       /==/,   -  \\|==|, ||==|-  _.-`/==/,   -  \\ |==|, |  \n" +
                    "\\==\\, \\/=/. /|==|   .=.     |==| ,||=| -|       |==|   _   _\\==|  ||==|   `.-.|==|   _   _\\|==|  |  \n" +
                    " \\==\\  \\/ -/ |==|_ : ;=:  - |==|- | =/  |       |==|  .=.   |==|- /==/_ ,    /|==|  .=.   |/==/. /  \n" +
                    "  |==|  ,_/  |==| , '='     |==|,  \\/ - |       |==|,|   | -|==| ,|==|    .-' |==|,|   | -|`--`-`   \n" +
                    "  \\==\\-, /    \\==\\ -    ,_ /|==|-   ,   /       |==|  '='   /==|- |==|_  ,`-._|==|  '='   / .=.     \n" +
                    "  /==/._/      '.='. -   .' /==/ , _  .'        |==|-,   _`//==/. /==/ ,     /|==|-,   _`/ :=; :    \n" +
                    "  `--`-`         `--`--''   `--`..---'          `-.`.____.' `--`-``--`-----`` `-.`.____.'   `=`     ";
            display.println(dyingMessage);
            flaskUpdate();
            ResetManager.getInstance().run();

            SoulToken soulToken = new SoulToken();
            soulToken.registerInstance();
            transferSouls(soulToken);
            map.at(previousLocation.x(), previousLocation.y()).addItem(soulToken);

            return new RespawnAction(respawnLocation, this);


        }

        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();


        String weapon = "";
        if (getWeapon() instanceof IntrinsicWeapon) {
            weapon = "no weapon";
        } else {

            actions.add(getWeapon().getActiveSkill(this, ""));
            weapon = getWeapon().toString();
        }
        display.println(String.format("%s (%d/%d), holding %s, %d souls", name, hitPoints, maxHitPoints, weapon, soul.getNoOfSouls()));
        // return/print the console menu
        previousLocation = map.locationOf(this);
        return menu.showMenu(this, actions, display);

    }


    /**
     * Set respawn location, however bond fire cant access it- maybe implement it on the soul interface
     *
     * @param newRespawnLocation The location of the respawn
     */
    public static void setRespawnLocation(Location newRespawnLocation) {
        respawnLocation = newRespawnLocation;
    }

    /**
     * Method overriding Actor class - heals Player by a percentage of max hit points
     * and ensures that hit points is not greater than max hit points
     * As Player healing is associated with changes to players flask - calls method to update flask
     *
     * @param points number of hit points to add.
     */
    @Override
    public void heal(int points) {
        this.flaskUpdate();
        super.heal((int) (maxHitPoints * (points / 100.0)));
    }

    /**
     * Method that identifies if Player is healed from drinking flask or resting at fire and calls methods to update
     * flask as required
     */
    public void flaskUpdate() {
        if (this.hasCapability(Status.DRINKING)) {
            EstusFlask.useCharge();
            this.removeCapability(Status.DRINKING);
        } else {
            EstusFlask.chargeReset();
            if (this.hasCapability(Status.RESTING)) {
                this.removeCapability(Status.RESTING);
            }
        }
    }

    /**
     * For resetting
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }

    /**
     * Set initial conditions
     */
    private void setInitialCondition() {
        removeCapability(Status.RESET);
        removeCapability(Status.DEAD);
        removeCapability(Status.RESTING);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Abilities.REST);
        this.addCapability(Abilities.ENTER_FLOOR);
        addCapability(Status.HAS_DYING_MESSAGE);
        this.addCapability(Abilities.FALL_FROM_CLIFF);
        addCapability(Abilities.RETRIEVE_SOULS);
        addCapability(Abilities.BUY);
        this.hitPoints = maxHitPoints;

    }

    /**
     * Make sure the player is on the map
     *
     * @return True
     */
    @Override
    public boolean isExist() {
        return true;
    }

    /**
     * A method to make a purchase
     *
     * @param purchaser The player making the purchase
     * @param map The game map the player is on
     * @return Null
     */
    @Override
    public String purchase(Actor purchaser, GameMap map) {
        return null;
    }

    /**
     * The cost of a purchase
     * @return 0
     */
    @Override
    public int getCost() {
        return 0;
    }

}
