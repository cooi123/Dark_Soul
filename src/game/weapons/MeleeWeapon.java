package game.weapons;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * MeleeWeapon Class extends WeaponItem
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see WeaponItem
 */
public class MeleeWeapon extends WeaponItem {
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public MeleeWeapon(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }
}
