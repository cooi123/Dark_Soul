package game.playerItems;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped, extends Item.
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see Item
 */
public class PortableItem extends Item {

    /**
     * Constructor for PortableItem
     *
     * @param name The name of the item
     * @param displayChar The map display character of the item
     */
    public PortableItem(String name, char displayChar) {
        super(name, displayChar, true);
    }
}
