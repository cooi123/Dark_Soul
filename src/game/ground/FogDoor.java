package game.ground;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.nonEnemy.Player;
import game.actions.EnterDoorAction;

import java.util.ArrayList;

/**
 * A class for the FogDoor which provides a door between GameMaps by moving the Player from one GameMap to another
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Ground
 */
public class FogDoor extends Ground {

    /**
     * An Array of the fog door locations
     */
    private static ArrayList<Location> fogDoorLocations = new ArrayList<>();

    /**
     * The name of the fog door
     */
    String doorName;

    /**
     * Constructor for the FogDoor, the order in which FogDoor's are instantiated effects the location they can access
     * as doors identify their destination from an order of door location in an ArrayList. Each door connects to one
     * other door.
     *
     * @param fogDoorLocation The location of a particular FogDoor.
     * @param doorName The name of a particular FogDoor.
     */
    public FogDoor(Location fogDoorLocation, String doorName) {
        super('=');
        fogDoorLocations.add(fogDoorLocation);
        this.doorName = doorName;
    }

    /**
     * Method providing the actions that FogDoor provides in the event that the Player is standing on the
     * door's location. The FogDoor provides an EnterDoorAction which moves the player to another FogDoor location.
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Actions object which contains action to enter door that Player can execute
     */
    public Actions allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor()) {
            return new Actions(new EnterDoorAction(fogDoorLocations, doorName));
        } else {
            return new Actions();
        }
    }

    /**
     * Method that determines if an Actor can enter a FogDoor's location. If the Actor is a Player, it can enter,
     * otherwise Actor's cannot enter the FogDoor's location.
     *
     * @param actor the Actor to check
     * @return Boolean indicator as to whether Actor is a Player and can enter FogDoor's location.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        boolean playerCheck = false;
        if (actor instanceof Player) {
            playerCheck = true;
        }
        return playerCheck;
    }

    /**
     * Method used to access the name of a particular FogDoor.
     *
     * @return String of FogDoor's name
     */
    public String toString() {
        return doorName;
    }


}
