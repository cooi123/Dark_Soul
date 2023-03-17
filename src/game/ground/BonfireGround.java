package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.LightAction;
import game.actions.RestAction;
import game.actions.TeleportAction;

import java.util.ArrayList;

/**
 * A class that extends Ground to create Bonfire that Player can interact with - using it to Rest
 * (which has a reset function) or to teleport between bonfire locations once the bonfires are lit - done by lighting
 * the second encountered bonfire). Each bonfire allows teleporting to all other bonfires.
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Ground
 */
public class BonfireGround extends Ground {

    private final static int healValue = 100;
    private Location bonfireLocation;
    private static ArrayList<Location> bonfireTeleportDestinationLocations = new ArrayList<>();
    private String bonfireName;
    private boolean isStartingBonfire;
    private static boolean isLit = false;

    /**
     * Constructor for Bonfire Ground. Sets unique display character for BonfireGround.
     * @param bonfireName       The name of a particular bonfire
     * @param bonfireLocation   The location of a particular bonfire
     * @param isStartingBonfire An indicator of whether the bonfire is the initial bonfire which does not
     *                          need to be lit before a Player can rest by it.
     */
    public BonfireGround(String bonfireName, Location bonfireLocation, boolean isStartingBonfire) {
        super('B');
        this.bonfireLocation = bonfireLocation;
        setBonfireDestinationLocation(bonfireLocation);
        this.bonfireName = bonfireName;
        this.isStartingBonfire = isStartingBonfire;
    }

    /**
     * A method allowing BonfireGround's allowable actions to be accessed which includes
     * LightAction, RestAction and TeleportAction. Which actions are returned is dependent on the
     * particular bonfire and whether bonfire's have been lit.
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Actions object - ArrayList containing actions giving Player the option to light bonfire, rest or teleport
     */
    public Actions allowableActions(Actor actor, Location location, String direction) {

        Actions allowableActions;

        if (!isLit && !this.isStartingBonfire) {
            allowableActions = new Actions(new LightAction(bonfireName, this.bonfireLocation));

        } else if (!isLit) {
            allowableActions = new Actions(new RestAction(bonfireName, bonfireLocation));
        } else {

            allowableActions = new Actions(new RestAction(bonfireName, bonfireLocation));

            for (int i = 0; i < bonfireTeleportDestinationLocations.size(); i++) {
                if (!bonfireTeleportDestinationLocations.get(i).getGround().toString().equals(this.bonfireName)) {
                    String destinationName = bonfireTeleportDestinationLocations.get(i).getGround().toString();

                    Actions teleportActions = new Actions(new TeleportAction(bonfireTeleportDestinationLocations.get(i), destinationName));
                    allowableActions.add(teleportActions);
                }
            }
        }
        return allowableActions;
    }

    /**
     * Static method that allows the healing value the Bonfire provides Player to be accessed through static
     * method call rather than instance.
     *
     * @return a fixed integer value representing the percentage of Player's max hit points that will be healed
     * by the Bonfire
     */
    public static int getHealValue() {
        return healValue;
    }

    /**
     * A method that determines whether a particular actor can enter the location occupied
     * by BonfireGround
     *
     * @param actor the Actor to check
     * @return False - indicating an Actor cannot enter BonfireGround location
     */
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * A method that takes a bonfire's location on instantiation and adds that location to a static list of
     * all bonfire locations so all bonfires can know the locations of all other bonfires.
     *
     * @param bonfireLocation The location of a particular bonfire
     */
    private void setBonfireDestinationLocation(Location bonfireLocation) {
        bonfireTeleportDestinationLocations.add(bonfireLocation);
    }

    /**
     * A method used to access the name of a particular bonfire.
     *
     * @return bonfireName
     */
    public String toString() {
        return bonfireName;
    }

    /**
     * A method used to set the static boolean variable 'isLit' which effects whether bonfires
     * can teleport the player and if the player can rest at some bonfires.
     */
    public static void lightBonfire() {
        isLit = true;
    }

}
