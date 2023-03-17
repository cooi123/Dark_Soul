package game.weapons.weaponActiveAction;

import edu.monash.fit2099.engine.*;
import game.actions.RangeAttackAction;
import game.ground.Fire;
import game.weapons.GameWeaponItem;
import game.enums.Abilities;
import game.interfaces.RangeInteraction;

/**
 * This class is used for the Dark moon long bow active skills and extends WeaponAction
 * It stores rangeInteraction weapon and uses range attack action to perform the attack
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see WeaponAction
 */
public class FireArrowAction extends WeaponAction {
    Actor target;
    RangeInteraction rangeWeapon;
    String cord;

    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public FireArrowAction(GameWeaponItem weaponItem, Actor target, String cord) {
        super(weaponItem);
        if (weaponItem.asRange() != null) {
            rangeWeapon = weaponItem.asRange();
        }
        this.target = target;
        this.cord = cord;
    }

    /**
     * Calls the range attack action and execute it- if attack missed does not set the ground on fire
     *
     * @param actor The actor throwing the fire arrow
     * @param map The game map the actor is on
     * @return The result of throwing the fire arrow
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        RangeAttackAction attack = new RangeAttackAction(target, cord, rangeWeapon);
        result += attack.execute(actor, map);
        if (rangeWeapon.targetable(actor, target, map)) {
            if (map.locationOf(target).getGround().hasCapability(Abilities.FLAMMABLE)) {
                map.locationOf(target).setGround(new Fire(3, 25));
                result += " burn ground at " + cord;

            }
        }
        return result;
    }

    /**
     * Menu description
     *
     * @param actor The actor throwing the fire arrow
     * @return The printout to the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + "with " + weapon.toString() + " and burn ground at " + cord;
    }
}
