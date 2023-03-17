package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Tradeable;

/**
 * Class buy action extends action
 * Buy action for weapon
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Action
 */
public class BuyAction extends Action {

    /**
     * The item that is being purchased/traded
     */
    Tradeable item;

    /**
     * Constructor
     *
     * @param item The item that will be swapped
     */
    public BuyAction(Tradeable item) {
        this.item = item;
    }

    /**
     * Uses subtract souls method in soul interface to verify the purchase
     * if successfully purchase, use swap weapon action to swap the weapon
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return The result of the purchase
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String successful = "Unable to buy " + item;
        if (actor.asSoul().subtractSouls(item.getCost())) {
            successful = "Purchase successful ";
            successful += item.purchase(actor, map);
        }
        return successful;

    }

    /**
     * Description for when the transaction is successful.
     *
     * @param actor The actor performing the action.
     * @return The printout to the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + item.toString() + "Cost: " + item.getCost();
    }
}
