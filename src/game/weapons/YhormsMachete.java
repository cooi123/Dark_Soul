package game.weapons;

import edu.monash.fit2099.engine.*;
import game.weapons.weaponActiveAction.BurnSurroundingAction;
import game.enums.Status;

/**
 * The YhormsMachete class represents the Machete Yhorm the Giant uses and is the child class of GameWeaponItem.
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0.0
 * @see GameWeaponItem
 */
public class YhormsMachete extends GameWeaponItem {

    /**
     * Allows Yhorm to deactivate rage mode
     */
    private int coolDown;

    /**
     * A constructor for YhormsMachete
     */
    public YhormsMachete() {
        super("Yhorm's Great Machete", 'M', 95, "slices", 60);
        coolDown = 0;
    }

    /**
     * Changes the hit rate for when Yhorm is enraged
     *
     * @return The hit rate percentage of the Machete
     */
    @Override
    public int chanceToHit() {
        if (this.hasCapability(Status.ENRAGE)) {
            hitRate = 90;
        }
        return hitRate;
    }

    /**
     * Retrieves the active skill associated with Yhorms Machete
     *
     * @param target    The target of the giant axe attack
     * @param direction The direction the target is facing, e.g. "north"
     * @return BurnAction which is an active skill associated with the weapon
     * @see BurnSurroundingAction
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if (target.hasCapability(Status.ENRAGE)) {
            this.addCapability(Status.ENRAGE);
            BurnSurroundingAction burn = new BurnSurroundingAction(this, 3);
            //need to wait three turn before activate
            coolDown = 3;
            return burn;
        }
        return null;
    }

    /**
     * Accesses the name of the weapon for printing purposes and defines the weapon mode
     *
     * @return The name of the weapon: Yhorms Machete and the weapon mode
     */
    public String toString() {
        String weaponMode = "";
        if (this.hasCapability(Status.ENRAGE)) {
            weaponMode = "Ember Form";
        }
        return name + weaponMode;
    }

    /**
     * Lets Yhorms Machete know when a turn has occurred and activates the cool down if necessary
     *
     * @param currentLocation The location of Yhorm the Giant
     * @param actor           Yhorm the Giant
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    /**
     * Checks whether Yhorm the Giant can begin to cool down.
     *
     * @return returns true if cool down = 0, false otherwise
     */
    private boolean activatable() {
        boolean active = false;
        if (coolDown == 0) {
            active = true;
        }
        return active;
    }


}