package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.interfaces.Behaviour;

/**
 * Behaviour class for enemeys play turn
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Behaviour
 */
public class AttackBehaviour implements Behaviour {
    private Actor target;

    /**
     * Constructor
     *
     * @param target The target of the attack
     */
    public AttackBehaviour(Actor target) {
        this.target = target;
    }

    /**
     * Make sure that the target is on the adjacent squares
     * Prevent attacking from far away
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return AttackAction()
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Action returnAction = null;
        if (map.contains(target) && map.contains(actor)) {
            Location here = map.locationOf(actor);
            //scan adjacent squares
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.map().getActorAt(destination) == target) {
                    returnAction = new AttackAction(target, exit.getName());
                }
            }
        }

        return returnAction;
    }

}
