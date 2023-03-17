package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A class for a wall in the game map that actors need to go around, extends Ground
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see Ground
 */
public class Wall extends Ground {

    /**
     * A constructor for Ground class
     */
    public Wall() {
        super('#');
    }

    /**
     * Stops actors from being able to stand on the wall
     * @param actor The actor trying to stand on the wall
     * @return False
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Stops actors from throwing objects through a wall
     * @return True
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}
