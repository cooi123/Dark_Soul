package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.enums.Abilities;
import game.enums.Status;
import game.playerItems.EstusFlaskItem;

/**
 * A class for a drinking action that can be allowed for particular items. A child class of Action
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Action
 */
public class DrinkAction extends Action {

    /**
     * Represents percentage of max hit points that DrinkAction will heal
     */
    int healValue;

    /**
     * A constructor for a DrinkAction Item that initalises the DrinkAction's heal value attribute
     * as that provided by the Estus Flask.
     */
    public DrinkAction() {
        healValue = EstusFlaskItem.getHealValue();
    }

    /**
     * An overridden method that executes the DrinkAction by checking if a Estus Flask has charges
     * and calling methods to heal an Actor
     *
     * @param actor   The actor performing the action
     * @param gamemap The GameMap the actor is on
     * @return A String describing the action executed to be printed in the game menu
     */
    public String execute(Actor actor, GameMap gamemap) {
        int i = 0;
        while (i < actor.getInventory().size() && !actor.getInventory().get(i).toString().contains("Estus Flask")) {
            i++;
        }
        Item flask = actor.getInventory().get(i);

        if (flask.hasCapability(Abilities.HEAL)) {
            actor.addCapability(Status.DRINKING);
            actor.heal(healValue);
        }
        return menuDescription(actor);
    }

    /**
     * A method providing a description of the action for the game menu
     *
     * @param actor The actor performing the action.
     * @return String describing the DrinkAction
     */
    public String menuDescription(Actor actor) {
        int i = 0;
        while (i < actor.getInventory().size() && !actor.getInventory().get(i).toString().contains("Estus Flask")) {
            i++;
        }
        Item flask = actor.getInventory().get(i);
        return actor + " drinks " + flask.toString();
    }

}
