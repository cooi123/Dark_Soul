package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Respawn action extending action
 * Used for resetting as well as reviving skeleton
 *
 * @author Lab7_Team6
 * @version 1.0
 */
public class RespawnAction extends Action {
    private Actor respawnActor;
    private Location location;
    private Boolean success;

    /**
     * Takes in the location and the actor that are being respawn
     * this allow for replacement for other actor. e.g when revived, the corpse will be remove and skeleton will be added
     *
     * @param location The location the actor is being respawn at
     * @param actor The actor being respawn
     */
    public RespawnAction(Location location, Actor actor) {
        this.respawnActor = actor;
        this.location = location;
    }

    /**
     * 1st check- if the location contains an actor, remove it- 2 actors cant be at the same location
     * First remove the actor and then adds the respawn actor in the respawn location
     * allows for respawning other actor/ replacing other actors
     *
     * @param anActor The actor that is being respawned
     * @param map     The map the actor is on.
     * @return
     */

    @Override
    public String execute(Actor anActor, GameMap map) {
        if (location.containsAnActor()) {
            map.removeActor(location.getActor());
        }
        map.removeActor(anActor);
        map.addActor(respawnActor, location);
        return (respawnActor.toString() + " has respawn");

    }

    /**
     * String when execute
     *
     * @param actor The actor performing the action.
     * @return The printout to the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Respawn";
    }


}
