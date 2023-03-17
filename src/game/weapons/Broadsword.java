package game.weapons;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.SwapWeaponAction;
import game.interfaces.Tradeable;
import game.utils.Utils;


/**
 * The Broadsword class represents a Broadsword and is the child class of GameWeaponItem.
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0.0
 * @see GameWeaponItem
 */
public class Broadsword extends GameWeaponItem implements Tradeable {

    /**
     * Stores the cost of the Broadsword
     */
    int cost;

    /**
     * A constructor for Broadsword.
     */
    public Broadsword() {
        super("Broadsword", '!', 30, "strikes", 80);
        cost = 500;
    }

    /**
     * Calculates the amount of damage inflicted by the broadsword
     *
     * @return The amount of damage the broadsword has on its target
     */
    @Override
    public int damage() {
        verb = "strikes";
        damage = 30;
        boolean successRate = new Utils().randomBooleanGenerator(20);
        if (successRate == true) {
            damage *= 2;
            verb = "critical strikes";
        }
        return damage;
    }

    /**
     * Accesses the name of the weapon for printing purposes
     *
     * @return The name of the weapon: "Broadsword"
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Allows the broadsword to be purchased at the vendor
     *
     * @param purchaser The player making the purchase
     * @param map The game map the player is on
     * @return The result of the purchase
     */
    @Override
    public String purchase(Actor purchaser, GameMap map) {
        String successful = "Unable to buy ";
        if (purchaser.asSoul().subtractSouls(this.getCost())) {
            Action swapWeapon = new SwapWeaponAction(this);
            return swapWeapon.execute(purchaser, map);
        }
        return "";
    }

    /**
     * Accesses the cost of the broadsword so that it can be bought at the vendor
     *
     * @return Cost of the Broadsword
     */
    @Override
    public int getCost() {
        return cost;
    }
}
