package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.enums.Abilities;
import game.enums.Status;

/**
 * Action used to pick up soul, extends pick up item action
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see PickUpItemAction
 */
public class PickUpSoulTokenAction extends PickUpItemAction {

    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public PickUpSoulTokenAction(Item item) {
        super(item);
    }

    /**
     * Transfer the souls from the token to the actor
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return The execute method in PickUpItem action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Abilities.RETRIEVE_SOULS)) {

            item.asSoul().transferSouls(actor.asSoul());
            item.addCapability(Status.TAKEN);

        }
        return super.execute(actor, map);
    }

    /**
     * Displays the menu description for picking up a soul token
     *
     * @param actor The player picking up the soul
     * @return The printout to the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("Pick up %s", item);
    }
}
