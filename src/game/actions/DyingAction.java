package game.actions;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Dying action for soul transfer and item drop actions. A child class of Action
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Action
 */
public class DyingAction extends Action {
    private Actor target;

    /**
     * Constructor that takes the dying target
     *
     * @param target The actor that is dying
     */

    public DyingAction(Actor target) {
        this.target = target;

    }

    /**
     * Transfer soul from target to killer if killer is able to retrieve soul
     * drops all the portable item from soul
     * Doesn't remove the actor
     *
     * @param killer The actor that is killing the other actor
     * @param map    The map the actor is on.
     * @return A description of the action
     */
    @Override
    public String execute(Actor killer, GameMap map) {

        if (!target.isConscious()) {
            target.addCapability(Status.DEAD);
            if (killer.hasCapability(Abilities.RETRIEVE_SOULS)) {
                target.asSoul().transferSouls(killer.asSoul());
            }
            Actions dropActions = new Actions();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(killer));//changed this to target
            for (Action drop : dropActions)
                drop.execute(target, map);
            return menuDescription(killer);
        }

        return "";
    }

    /**
     * Menu description
     *
     * @param actor The actor performing the action.
     * @return The printout to the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return System.lineSeparator() + target + " is killed by " + actor;
    }
}
