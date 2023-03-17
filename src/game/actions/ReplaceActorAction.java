package game.actions;

import edu.monash.fit2099.engine.*;

/**
 * Revive actions mostly for skeleton- could handle different actor. Child class of action
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Action
 */
public class ReplaceActorAction extends Action {
    private Location respawnLocation;
    private Actor actor;
    private Actor replacementActor;

    /**
     * Takes in the spawn location and the actor that has to be revived
     *
     * @param respawnLocation The location the actor is being respawned at
     * @param actor The actor being respawned
     */

    public ReplaceActorAction(Location respawnLocation, Actor actor, Actor replacementActor) {
        this.respawnLocation = respawnLocation;
        this.actor = actor;
        this.replacementActor = replacementActor;
    }

    /**
     * Revive action creates corpse (a place holder for the actor that need to be revived)
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A string of the result of the action
     */

    @Override
    public String execute(Actor actor, GameMap map) {

        Location corpseLocation = map.locationOf(actor);
        map.removeActor(actor);
        corpseLocation.addActor(replacementActor);

        String result = String.format("%s will revive in next turn", actor.toString());
        return result;
    }

    /**
     * Accesses the location that the respawn will occur at
     *
     * @return The respawn location
     */
    public Location getRespawnLocation() {
        return respawnLocation;
    }

    /**
     * Accesses the actor to be replaced
     *
     * @return The actor being replaced
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * Accesses the replacement actor
     * @return The replacement actor
     */
    public Actor getReplacementActor() {
        return replacementActor;
    }

    /**
     * Displays the menu description for replacing an actor
     *
     * @param actor The actor being replaced
     * @return The printout to the console
     */
    @Override
    public String menuDescription(Actor actor) {

        return "Replacing actor";
    }


}
