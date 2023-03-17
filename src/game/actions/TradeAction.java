package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;
import game.interfaces.Tradeable;

/**
 * The TradeAction class represents a trading action and is a child class of Action
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see Action
 */
public class TradeAction extends Action {

    /**
     * An instance of the item to be traded
     */
    Tradeable item;

    /**
     * The constructor for TradeAction
     *
     * @param item The item to be traded
     */
    public TradeAction(Tradeable item) {
        this.item = item;
    }

    /**
     * Executes the trade between the vendor and the player
     *
     * @param actor The player trading with the vendor
     * @param map The game map the player is on
     * @return The result of the trade
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.ENRAGE);
        return "Trade successful " + item.purchase(actor, map);
    }

    /**
     * Displays the trade option to the player
     *
     * @param actor The player who has the option to make the trade
     * @return A string of the menu print out of the trade action option
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("Trade %s with %s", actor.getWeapon(), item.toString());
    }
}
