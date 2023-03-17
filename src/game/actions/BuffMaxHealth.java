package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Tradeable;

/**
 * Class extending Action adds health to player
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Tradeable
 */
public class BuffMaxHealth implements Tradeable {

    private int hitPointIncrease;
    private int cost;

    /**
     * constructor
     * cost
     * buff percentage
     */
    public BuffMaxHealth() {
        this.hitPointIncrease = 25;
        this.cost = 200;
    }

    /**
     * Performs the purchase action of refilling and extending a players HP
     *
     * @param purchaser The player buying the increase of HP from the vendor
     * @param map The game map the player is on
     * @return A string result of the purchase
     */
    @Override
    public String purchase(Actor purchaser, GameMap map) {
        purchaser.increaseMaxHp(25);
        return "increased " + hitPointIncrease;
    }

    /**
     * Displays a string of the max HP modifier for printing purposes
     *
     * @return A string description of this class
     */
    @Override
    public String toString() {
        return String.format("Max HP modifier (+%dhp)", hitPointIncrease);
    }

    /**
     * Accesses the amount of souls required to purchase the max health
     *
     * @return The cost of souls
     */
    @Override
    public int getCost() {
        return cost;
    }
}
