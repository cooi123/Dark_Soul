package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;
import game.enums.Status;
import game.weapons.StormRuler;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Goes through the weapon in actor and damage the target
	 * Also goes to dying action to check if target is still alive
	 * The dullness passive check is done here
	 * Checks if the weapon is part of storm ruler using instance of
	 * seperated the dying action to allow for modularity/ the dying action could be used for other actions
	 *
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return The result of the attack
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}
		int damage = weapon.damage();
		if (weapon instanceof StormRuler && !target.hasCapability(Status.WEAK_AGAINST_STORM_RULER)) {
			damage = damage / 2;
		}


		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		Action dyingAction = new DyingAction(target);
		result += dyingAction.execute(actor, map);

		return result;
	}

	/**
	 * Displays the menu description of an attack
	 *
	 * @param actor The actor performing the action.
	 * @return The printout to the console
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
