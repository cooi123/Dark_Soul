package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;
import game.enums.Status;

/**
 * A class that represents the floor inside a building and is a child class of ground
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see Ground
 */
public class Floor extends Ground {

    /**
     * A constructor for the Floor class
     */
    public Floor() {
        super('_');
    }

    /**
     * Added check for capabilty to enter floor, enemy cant entered
     *
     * @param actor the Actor to check
     * @return True if the actor can enter, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        boolean can = false;
        if (actor.hasCapability(Abilities.ENTER_FLOOR)) {
            can = true;

        }
        return can;
    }
}
