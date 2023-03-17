package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.nonEnemy.Player;

/**
 * A class providing an action for Player to teleport between bonfire locations.
 * @author Lab7_Team6
 * @version 1.0
 * @see Action
 */
public class TeleportAction extends Action {

    /**
     * An instance of the destination
     */
    Location destination;

    /**
     * An instance of the name of the destination bonfire
     */
    String destinationBonfireName;

    /**
     * Constructor to instantiate a TeleportAction object
     *
     * @param destination The location of the destination bonfire
     * @param bonfireName The name of the destination bonfire
     */
    public TeleportAction(Location destination, String bonfireName) {
        super();
        this.destination = destination;
        this.destinationBonfireName = bonfireName;
    }

    /**
     * Overridden method that executes the TeleportAction as part of a Player's turn by moving the Player to the
     * the destination bonfire. This method also updates the respawn location of Player to implement the
     * game rule that a player respawns to the location of the last bonfire they interacted with.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A string to be used in the game menu
     */
    public String execute(Actor actor, GameMap map) {

        map.moveActor(actor, destination.map().at(destination.x(), destination.y() + 1));
        Player.setRespawnLocation(destination.map().at(destination.x(), destination.y() + 1));
        return menuDescription(actor);
    }

    /**
     * A method to generate a description of the TeleportAction for use in the game menu
     *
     * @param actor The actor performing the action.
     * @return String describing the action for the game menu
     */
    public String menuDescription(Actor actor) {
        String string = " teleports to " + destinationBonfireName;
        return actor + string;
    }
}
