package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;
import game.interfaces.Behaviour;

/**
 * This class represents the random wandering of actors and is a child class of Action
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see Action
 */
public class WanderBehaviour extends Action implements Behaviour {

    /**
     * An instance of a random generator
     */
    private final Random random = new Random();

    /**
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<Action>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        if (!actions.isEmpty() && actor.hasCapability(Status.WANDERING)) {
            return actions.get(random.nextInt(actions.size()));
        } else {
            return null;
        }

    }

    /**
     * Calls the menuDescription
     *
     * @param actor The actor that is wandering around
     * @param map The game map the actor is on.
     * @return menuDescription()
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /**
     * Displays a menu description of the wandering action
     *
     * @param actor The actor that is wandering around
     * @return The printout to the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Raagrh...";
    }
}
