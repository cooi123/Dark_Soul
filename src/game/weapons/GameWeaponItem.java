package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.SwapWeaponAction;
import game.interfaces.RangeInteraction;

/**
 * The GameWeaponItem class represents all the game weapons and is a child class of WeaponItem
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see WeaponItem
 */
public class GameWeaponItem extends WeaponItem {

    /**
     * Constructor.
     *
     * @param name        Name of the item
     * @param displayChar Character to use for display when item is on the ground
     * @param damage      Amount of damage this weapon does
     * @param verb        Verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     The probability/chance to hit the target.
     */
    public GameWeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

    /**
     * Method to drop an item
     *
     * @param actor an actor that will interact with this item
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Method to pick up a Weapon
     *
     * @param actor an actor that will interact with this item
     * @return SwapWeaponAction()
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new SwapWeaponAction(this);
    }

    /**
     * Casts this Item to a Range if possible.
     *
     * @return a reference to the current Item as type Range, or null if this Item isn't a Range
     */
    public RangeInteraction asRange() {
        return this instanceof RangeInteraction ? (RangeInteraction) this : null;
    }

}
