package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemy.Undead;
import game.utils.Utils;

/**
 * A class that is a child of Ground in which Undead are spawned from.
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see Ground
 */
public class Cemetery extends Ground {

    /**
     * Constructor.
     */
    public Cemetery() {
        super('c');
    }

    /**
     * 25% chance of creating new undead and adds it to map
     * make sure the location doesnt contain an actor to prevent conflict.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (Utils.randomBooleanGenerator(25) && !location.containsAnActor()) {
            location.addActor(new Undead("Undead"));
        }
        super.tick(location);
    }
}
