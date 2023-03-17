package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * The RemoveActorAction class removes an actor from the map and is the child class of Action
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Action
 */
public class RemoveActorAction extends Action {
    /**
     * Remove actor from map
     * Only have this to print out the description
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return menuDescription()
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Displays the menu description for removing an actor from the game
     *
     * @param actor The actor being removed from the game
     * @return The printout to the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been removed from the game";
    }
}
