package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;

/**
 * Class providing action for Actor to enter a door and be moved to an associated door's locations by using
 * an algorithm to determine the destination from a door the position of the door locations within an ArrayList.
 * A child class of Action
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Action
 */
public class EnterDoorAction extends Action {

    /**
     * An Array of all the destination locations
     */
    ArrayList<Location> destinationsLocations;

    /**
     * AN instance of the name of the door
     */
    String doorName;

    /**
     * Constructor to instantiate EnterDoorActions used to move Actor's from one door location to another
     *
     * @param destinations An ArrayList of all door locations within the game
     * @param doorName The name of a particular Fog Door
     */
    public EnterDoorAction(ArrayList destinations, String doorName){
        super();
        this.destinationsLocations = destinations;
        this.doorName = doorName;
    }

    /**
     * Executes the action of entering a door by finding the door to be entered within an ArrayList of doors and using
     * algorithm to determine destination door and using destination door's location from the ArrayList to move the
     * Actor. The action is predicated on each door connecting to one other door. As the destination is identified by the
     * the result of the EnterDoorAction is dependent on the order of instantiation of doors which impacts their order
     * in the ArrayList. E.g. 1st door in array connects to 2nd door in array, 2nd door in array connects to 1st door.
     * 3rd door connects to 4th door and 4th door connects to 3rd door etc.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String describing the Action for inclusion in the game menu.
     */
    public String execute(Actor actor, GameMap map){
        Location destination;
        int i = 0;
        while (i < destinationsLocations.size() && !destinationsLocations.get(i).getGround().toString().equals(doorName)){
            i++;
        }
        if (i % 2 == 0){
            destination = destinationsLocations.get(i+1);
        } else {
            destination = destinationsLocations.get(i-1);
        }
        map.moveActor(actor, destination);

        return menuDescription(actor);
    }

    /**
     * A method providing a description of the action for inclusion in the games menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    public String menuDescription(Actor actor){
        String string = " enters " + doorName;
        return actor + string;
    }

}
