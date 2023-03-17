package game.ground;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * Fire class is a child class of ground and burns actors standing on it
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Ground
 */
public class Fire extends Ground {

    /**
     * An instance of the fire cool down
     */
    int resetTurn;

    /**
     * An instance of the current turn to keep track of activation of fire
     */
    int currentTurn;

    /**
     * The amount of damage done by the fire
     */
    int damage;

    /**
     * currentTurn to keep track of when it activates
     * Reset turn used for the cool down
     * Constructor
     *
     * @param reset An integer used for the fire cool down
     * @param damage The amount of damage done by the fire
     */
    public Fire(int reset, int damage) {
        super('V');
        resetTurn = reset;
        currentTurn = 0;
        this.damage = damage;

    }

    /**
     * when actors is at the location deals damage
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return The actions that can be made
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {


        if (!actor.hasCapability(Abilities.FIRE_RESISTANCE) && location.containsAnActor()) {
            actor.hurt(damage);

        }
        return new Actions();


    }

    /**
     * increment the current turn
     * when it reaches change the ground back to dirt
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        currentTurn++;
        if (currentTurn > resetTurn) {
            location.setGround(new Dirt());
        }
    }
}
