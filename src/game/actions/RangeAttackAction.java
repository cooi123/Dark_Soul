package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.RangeInteraction;

/**
 * Attack action that uses range interaction item and is a child class of AttackAction()
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see AttackAction
 */
public class RangeAttackAction extends AttackAction {
    RangeInteraction item;

    /**
     * Constructor for RangeAttackAction class
     *
     * @param target    The Actor to attack
     * @param direction The direction in which the attack will take place
     */
    public RangeAttackAction(Actor target, String direction, RangeInteraction item) {
        super(target, direction);
        this.item = item;
    }

    /**
     * Uses the range interaction method to check whether the attack is blocked by the path.
     * Could be override to make the attack blocked not just from wall, maybe cannot shoot through other actor...
     *
     * @param actor The actor attempting to attack
     * @param map The game map the actor is on
     * @return True if the attack is blocked, false otherwise
     */
    public boolean attackBlocked(Actor actor, GameMap map) {
        return !item.targetable(actor, target, map);
    }

    /**
     * Uses the super attack action if the path is not blocked
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return The execute method in AttackAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (attackBlocked(actor, map)) {
            return actor + "'s attack is blocked.";
        }
        return super.execute(actor, map);
    }

}
