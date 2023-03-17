package game.ground;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * The gorge or endless gap that is dangerous for the Player, extends ground
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see Ground
 */
public class Valley extends Ground {

    /**
     * The Constructor for the Valley class
     */
    public Valley() {
        super('+');
    }


    /**
     * Checks if a player can enter the valley or not
     *
     * @param actor The actor wanting to enter the valley
     * @return True if the actor can enter, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        boolean can = false;
        if (actor.hasCapability(Abilities.FALL_FROM_CLIFF)) {
            can = true;
        }
        return can;
    }

    /**
     * The actions that can be made by an actor at the valley
     *
     * @param actor The actor at the valley
     * @param location The location of the valley
     * @param direction The direction of the valley from the actor
     * @return The actions that can be made by the actor
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {

        if (actor.hasCapability(Abilities.FALL_FROM_CLIFF) && location.containsAnActor()) {
            actor.hurt(Integer.MAX_VALUE);
        }
        return new Actions();
    }
}
