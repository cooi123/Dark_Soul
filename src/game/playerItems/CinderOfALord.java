package game.playerItems;

import edu.monash.fit2099.engine.*;
import game.actions.SwapWeaponAction;
import game.enums.Abilities;
import game.interfaces.Tradeable;
import game.weapons.GameWeaponItem;

/**
 * The CinderOfALord class represents a cinder of a lord and is the child class of PortableItem
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.1
 * @see PortableItem
 */
public class CinderOfALord extends PortableItem implements Tradeable {

    /**
     * Stores the weapon of a lord of cinder than can be accessed by trading the cinder of lord
     */
    private GameWeaponItem bossWeapon;


    /**
     * A constructor for CinderOfALord
     *
     * @param bossWeapon The weapon associated with the lord of cinder that dropped this cinder of a lord
     */
    public CinderOfALord(GameWeaponItem bossWeapon) {
        super("Cinder of A Lord", '%');
        this.addCapability(Abilities.TRADE);
        this.bossWeapon = bossWeapon;

    }

    /**
     * Performs the trade action of swapping a cinder of a lord for the weapon associated
     *
     * @param purchaser The player holding the cinder of a lord
     * @param map       The game map the player is standing on
     * @return A string of the result of the trade
     */
    @Override
    public String purchase(Actor purchaser, GameMap map) {
        purchaser.removeItemFromInventory(this);
        SwapWeaponAction trade = new SwapWeaponAction(bossWeapon);
        String result = trade.execute(purchaser, map);
        return result;
    }

    /**
     * Sets that a cinder of a lord cannot be dropped
     *
     * @param actor The player that has the cinder of lord in their inventory
     * @return Null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Accesses the cost of buying a cinder of a lord with souls
     *
     * @return Currently cannot be bought with souls so returns 0
     */
    @Override
    public int getCost() {
        return 0;
    }

    /**
     * Accesses the weapon associated with the lord of cinder that dropped the cinder of lord as a string
     *
     * @return The lord of cinder weapon as a String
     */
    @Override
    public String toString() {
        return name + " " + bossWeapon.toString();
    }
}
