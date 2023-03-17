package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.ground.BonfireGround;
import game.nonEnemy.Player;

/**
 * Class to light bonfire to access functionality such as Rest and Teleport at bonfires other than the starting
 * bonfire. A child class of Action
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Action
 */
public class LightAction extends Action {

    /**
     * An instance of the bonfire name
     */
    String bonfireName;

    /**
     * An instance of the bonfire location
     */
    Location bonfireLocation;

    /**
     * Constructor for LightAction
     *
     * @param bonfireName The name of the bonfire being lit
     * @param bonfireLocation The location of the bonfire being lit
     */
    public LightAction(String bonfireName, Location bonfireLocation) {
        super();
        this.bonfireName = bonfireName;
        this.bonfireLocation = bonfireLocation;
    }

    /**
     * Method that triggers the static method in Bonfire ground, lightBonfire, to set static
     * boolean variable, isLit, to true. In doing so, unlocks additional functionality for some bonfires in the
     * game (including Rest and Teleport). This method also updates the respawn location of Player to implement the
     * game rule that a player respawns to the location of the last bonfire they interacted with.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A String describing the LightAction for the game m
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        BonfireGround.lightBonfire();
        Player.setRespawnLocation(bonfireLocation.map().at(bonfireLocation.x(), bonfireLocation.y() + 1));
        return menuDescription(actor);
    }

    /**
     * A method generating a description of the LightAction to be used in the game menu.
     *
     * @param actor The actor performing the action.
     * @return A String describing the LightAction.
     */
    @Override
    public String menuDescription(Actor actor) {
        String descriptionString = " lights " + bonfireName;
        return actor + descriptionString;
    }
}
