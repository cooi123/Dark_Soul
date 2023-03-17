package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.ground.BonfireGround;
import game.nonEnemy.Player;
import game.ResetManager;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Class extending Action which provides a RestAction to reset Player and other Actor attributes
 *
 * @author Lab7_Team6
 * @version 1.0
 */
public class RestAction extends Action {

    /**
     * The amount resting will heal an actor by
     */
    int healValue;

    /**
     * An instance of the name of the bonfire
     */
    String bonfireName;

    /**
     * An instance of the location of the bonfire
     */
    Location bonfireLocation;

    /**
     * Constructor to instantiate RestAction and initialise heal value the RestAction provides to the Player
     *
     * @param bonfireName The name of the bonfire
     * @param bonfireLocation The location of the bonfire
     */
    public RestAction(String bonfireName, Location bonfireLocation) {
        healValue = BonfireGround.getHealValue();
        this.bonfireName = bonfireName;
        this.bonfireLocation = bonfireLocation;
    }

    /**
     * Method that executes RestAction to perform heal, flask changes and enemy reset (optional now)
     * this method also updates Player's respawnLocation
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return Description describing Action
     */
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Abilities.REST)) {
            actor.addCapability(Status.RESTING);
        }
        actor.heal(healValue);
        Player.setRespawnLocation(bonfireLocation.map().at(bonfireLocation.x(), bonfireLocation.y() + 1));
        ResetManager.getInstance().run();


        return menuDescription(actor);
    }

    /**
     * Method providing description of Action for game menu
     *
     * @param actor The actor performing the action
     * @return String describing the Action
     */
    public String menuDescription(Actor actor) {
        return actor + " rests by " + bonfireName;
    }


}
