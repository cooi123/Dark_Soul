package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponAction;
import game.weapons.weaponActiveAction.ChargeAction;
import game.enums.Status;
import game.interfaces.Resettable;
import game.utils.Utils;

/**
 * The StormRuler class represents a Storm Ruler and is the child class of GameWeaponItem.
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0.0
 * @see GameWeaponItem
 */
public class StormRuler extends GameWeaponItem implements Resettable {

    /**
     * A counter for how many times the weapon has been charged
     */
    private int counter;

    /**
     * A constructor for StormRuler
     */
    public StormRuler() {
        super("Storm Ruler", '7', 70, "strikes", 60);
        counter = 0;
        registerInstance();
    }

    /**
     * Calculates the amount of damage inflicted by the Storm Ruler
     *
     * @return The amount of damage the broadsword has on its target
     */
    @Override
    public int damage() {
        verb = "strikes";
        damage = 70;
        if (hasCapability(Status.FULLY_CHARGED) || Utils.randomBooleanGenerator(20)) {
            damage = damage * 2;
        }
        return damage;
    }

    /**
     * Retrieves the active skill associated with the Storm Ruler
     *
     * @param target    The target of the giant axe attack
     * @param direction The direction the target is facing, e.g. "north"
     * @return ChargeAction which is an active skill associated with the weapon
     * @see ChargeAction
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        WeaponAction weaponAction = null;
        if (!hasCapability(Status.CHARGING)) {
            removeCapability(Status.ACTIVATING_SKILL);
            weaponAction = new ChargeAction(this);
        }
        if (target.hasCapability(Status.ACTIVATING_SKILL)) {
            counter = 0;
            target.removeCapability(Status.ACTIVATING_SKILL);
            target.removeCapability(Status.FULLY_CHARGED);
            removeCapability(Status.CHARGING);
            removeCapability(Status.FULLY_CHARGED);
            weaponAction = new ChargeAction(this);
        }
        return weaponAction;
    }

    /**
     * Lets the Storm Ruler know when a turn has occurred and changes the weapon capabilities based on how many times
     * chargeAction has been selected
     *
     * @param currentLocation The location of the actor carrying the Storm Ruler
     * @param actor           The actor carrying the Storm Ruler
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor.hasCapability(Status.CHARGING)) {
            addCapability(Status.CHARGING);
            counter += 1;
        }
        if (counter >= 3 && !actor.hasCapability(Status.ACTIVATING_SKILL)) {
            actor.removeCapability(Status.DISARMED);
            actor.addCapability(Status.FULLY_CHARGED);
            addCapability(Status.FULLY_CHARGED);
        }
        if (actor.hasCapability(Status.ACTIVATING_SKILL)) {
            removeCapability(Status.CHARGING);
            removeCapability(Status.FULLY_CHARGED);
        }
        super.tick(currentLocation, actor);
    }

    /**
     * Accesses the name of the weapon for printing purposes and defines the weapon mode
     *
     * @return The name of the weapon: Storm Ruler and the weapon mode
     */
    @Override
    public String toString() {
        String weaponMode = "";
        weaponMode = " charges (" + counter + "/3)";
        if (hasCapability(Status.CHARGING)) {
            weaponMode += "Charging";
        }
        if (hasCapability(Status.FULLY_CHARGED)) {
            weaponMode = " FULLY CHARGED";
        }
        return name + weaponMode;
    }

    /**
     * Resets the counter to zer0
     */
    @Override
    public void resetInstance() {
        counter = 0;
    }

    /**
     * Checks to see if the Storm Ruler is on the map
     *
     * @return true if the Storm Ruler is on the map, false otherwise
     * @see Resettable
     */
    @Override
    public boolean isExist() {
        return true;
    }
}
