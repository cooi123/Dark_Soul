package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.RangeAttackAction;
import game.interfaces.Behaviour;
import game.interfaces.RangeInteraction;

/**
 * Behaviour class for enemies play turn
 *
 * @author Lab7_Team6
 * @version 1.0
 */
public class RangeAttackBehaviour implements Behaviour {

    /**
     * Stores an instance of the range of the item
     */
    private RangeInteraction item;

    /**
     * Stores an instance of the actor getting attacked
     */
    private Actor target;

    /**
     * Get the targets and the range item
     *
     * @param target The target of the attack
     * @param item The attack item
     */
    public RangeAttackBehaviour(Actor target, RangeInteraction item) {
        this.target = target;
        this.item = item;
    }

    /**
     * Use the targetable method in range interaction interface to check if the target is within the range
     * Used interface- no need to hard code the range for the weapon.
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return RangeAttackAction if the item is targetable, null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Action action = null;
        if (item.targetable(actor, target, map)) {
            String cord = map.locationOf(target).x() + "," + map.locationOf(target).y();
            action = new RangeAttackAction(target, cord, (RangeInteraction) item);
        }

        return action;
    }
}
