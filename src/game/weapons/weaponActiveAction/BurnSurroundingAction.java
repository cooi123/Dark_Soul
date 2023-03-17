package game.weapons.weaponActiveAction;

import edu.monash.fit2099.engine.*;
import game.ground.Fire;
import game.enums.Abilities;

/**
 * Class extending Weapon action that burns the ground around the weapon
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see WeaponAction
 */
public class BurnSurroundingAction extends WeaponAction {

    /**
     * The amount of damage done by the burning
     */
    private int damage;

    /**
     * The burn cooldown counter
     */
    private int coolDown;

    /**
     * Constructor, takes in the weapon.
     *
     * @param weapon The weapon that is performing the action
     */
    public BurnSurroundingAction(WeaponItem weapon, int coolDown) {
        super(weapon);
        damage = 25;
        this.coolDown = coolDown;
    }

    /**
     * Look for adjacent squares if the ground is flammable, change the ground to fire
     *
     * @param actor The actor executing the burn action
     * @param map The game map the actor is on
     * @return menuDescription()
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        //scan adjacent squares
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(Abilities.FLAMMABLE)) {
                destination.setGround(new Fire(coolDown, damage));
            }
        }

        return menuDescription(actor);
    }

    /**
     * Return description when activating the action
     *
     * @param actor The actor performing the burn action
     * @return The printout to the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "burn the surrounding area" + "( " + actor.getWeapon().toString() + ")";
    }
}
