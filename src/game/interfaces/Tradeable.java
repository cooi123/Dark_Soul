package game.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This interface is used for anything that can be traded and be pass to the vendor
 *
 * @author Lab7_Team6
 * * @version 1.0
 */
public interface Tradeable {
    /**
     * Applies the purchase e.g swap weapon action or buff the actor...
     * Also able to deal with the currency, e.g soul or trade other item with another item
     *
     * @param purchaser The actor making the purchase
     * @param map The game map the actor is on
     * @return A string result of the purchase
     */
    public String purchase(Actor purchaser, GameMap map);

    /**
     * Returns cost
     *
     * @return The cost of the purchase
     */
    public int getCost();


}
