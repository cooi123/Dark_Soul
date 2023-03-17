package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.interfaces.Behaviour;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see Behaviour
 */
public class FollowBehaviour implements Behaviour {

    /**
     * An instance of the actor being followed
     */
    private Actor target;

    /**
     * Constructor.
     *
     * @param subject The Actor to follow
     */
    public FollowBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Scan the surroding exits and find the next location that get the actor closer to the target
     * Return a new move actor action to that location
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return If map contains the target and the actor it returns MoveActorAction, otherwise null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = distance(here, there);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}