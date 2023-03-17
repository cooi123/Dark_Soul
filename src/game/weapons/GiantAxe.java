package game.weapons;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import game.weapons.weaponActiveAction.SpinAttackAction;
import game.actions.SwapWeaponAction;
import game.interfaces.Tradeable;

/**
 * The GiantAxe class represents a GiantAxe and is the child class of GameWeaponItem.
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0.0
 * @see GameWeaponItem
 */
public class GiantAxe extends GameWeaponItem implements Tradeable {

    /**
     * The cost of purchasing a GiantAxe
     */
    int cost;

    /**
     * A constructor for GiantAxe
     */
    public GiantAxe() {
        super("Giant Axe", '?', 50, "hits", 80);
        cost = 1000;
    }

    /**
     * Accesses the name of the weapon for printing purposes
     *
     * @return The name of the weapon: "GiantAxe"
     */
    public String toString() {
        return name;
    }

    /**
     * Retrieves the active skill associated with the weapon
     *
     * @param target    The target of the giant axe attack
     * @param direction The direction the target is facing, e.g. "north"
     * @return SpinAttackAction which is the active skill associated with the weapon
     * @see SpinAttackAction
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return new SpinAttackAction(this);
    }

    /**
     * Allows the Giant Axe to be purchased at the vendor
     *
     * @param purchaser The player making the purchase
     * @param map The game map the player is on
     * @return SwapWeaponAction().execute()
     */
    @Override
    public String purchase(Actor purchaser, GameMap map) {
        Action swapWeapon = new SwapWeaponAction(this);
        return swapWeapon.execute(purchaser, map);
    }

    /**
     * Accesses the cost of purchasing the giant axe
     *
     * @return The cost of the axe
     */
    @Override
    public int getCost() {
        return cost;
    }
}

