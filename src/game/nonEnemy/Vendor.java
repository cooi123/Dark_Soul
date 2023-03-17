package game.nonEnemy;

import edu.monash.fit2099.engine.*;
import game.actions.BuffMaxHealth;
import game.actions.BuyAction;
import game.actions.TradeAction;
import game.enums.Abilities;
import game.interfaces.Tradeable;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;

/**
 * The Vendor Class represents the vendor and is a child class of NonEnemy
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.1
 * @see NonEnemy
 */
public class Vendor extends NonEnemy {

    /**
     * Stores a new instance of buying the Broadsword
     */
    BuyAction broadsword;

    /**
     * Stores a new instance of buying the GiantAxe
     */
    BuyAction giantAxe;

    /**
     * Stores a new instance of buying more HP
     */
    BuyAction increaseHp;

    /**
     * A constructor for Vendor, initialises buyActions
     */
    public Vendor() {
        super("Fire Keeper", 'F', Integer.MAX_VALUE, 0);
        broadsword = new BuyAction(new Broadsword());
        giantAxe = new BuyAction(new GiantAxe());
        increaseHp = new BuyAction(new BuffMaxHealth());
        addCapability(Abilities.UNTARGETABLE);
    }

    /**
     * Accesses the transactions that can be performed at the vendor
     *
     * @param otherActor The player interacting with the vendor
     * @param direction  The direction the player is facing
     * @param map        The current game map
     * @return The list of actions available to the otherActor
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if (otherActor.hasCapability(Abilities.BUY)) {
            actions.add(broadsword);
            actions.add(giantAxe);
            actions.add(increaseHp);
        }
        for (Item item : otherActor.getInventory()) {
            if (item instanceof Tradeable && item.hasCapability(Abilities.TRADE)) {
                actions.add(new TradeAction((Tradeable) item));
            }
        }
        return actions;
    }


    /**
     * Returns the vendor's turn within a game
     *
     * @param actions    The actions that can be made by the vendor
     * @param lastAction The action the vendor made last turn
     * @param map        The game map the vendor is on
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction()
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Accesses the vendor's action within a turn
     *
     * @return A string of the vendor's action within a turn
     */
    @Override
    public String toString() {
        return "Vendor " + name + " waiting for customer";
    }
}
