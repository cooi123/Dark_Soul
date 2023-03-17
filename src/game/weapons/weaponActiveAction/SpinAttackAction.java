package game.weapons.weaponActiveAction;

import edu.monash.fit2099.engine.*;
import game.actions.DyingAction;
import game.weapons.GameWeaponItem;
import game.enums.Abilities;
import game.enums.Status;

/**
 * The SpinAttackAction class represents the Spin Attack Action that gets activated by the user holding the Giant Axe
 * and is a child class of WeaponAction
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0.0
 * @see WeaponAction
 * @see game.weapons.GiantAxe
 */
public class SpinAttackAction extends WeaponAction {

    /**
     * A constructor for SpinAttackAction
     *
     * @param weapon The Giant Axe
     */
    public SpinAttackAction(GameWeaponItem weapon) {
        super(weapon);
    }

    /**
     * Executes the Spin Attack Action on all adjacent squares surround the player holding the storm ruler
     *
     * @param actor The actor holding the Giant Axe performing the spin attack action
     * @param map   The game map the actor is on
     * @return A string result of the spin attack action describing the attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();
        String result = "";
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.map().isAnActorAt(destination)) {
                Actor target = destination.map().getActorAt(destination);
                if (!(actor.hasCapability(Status.ENEMY) && target.hasCapability(Status.ENEMY)) && !target.hasCapability(Abilities.UNTARGETABLE)) {
                    int damage = weapon.damage() / 2;
                    result += menuDescription(actor) + " hitting " + target + " for " + damage + " damage.";
                    target.hurt(damage);
                    Action dying = new DyingAction(target);
                    result += dying.execute(actor, map);
                }

            }
        }
        if (result == "") {
            result = actor + " swing " + weapon + "misses all targets";
        }
        return result;
    }

    /**
     * A string description of the action
     *
     * @param actor The actor holding the Giant Axe performing the spin attack action
     * @return A string of the text placed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " spins 360 degrees and swings the giant axe"; // Does this need to be more specific? - return result??
    }
}
