package game.ground;

import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;

/**
 * A class that represents bare dirt and is flammable. A child class of Ground.
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see Ground
 */
public class Dirt extends Ground {

	/**
	 * A constructor for the Dirct class
	 */
	public Dirt() {
		super('.');
		addCapability(Abilities.FLAMMABLE);
	}

}
