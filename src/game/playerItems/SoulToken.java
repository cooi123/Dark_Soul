package game.playerItems;

import edu.monash.fit2099.engine.*;
import game.actions.PickUpSoulTokenAction;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * Token of soul, extends Item
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Item
 */
public class SoulToken extends Item implements Soul, Resettable {

    /**
     * An instance of the SoulObject
     */
    SoulObject soul;

    /***
     * Constructor.
     * Register instance
     */
    public SoulToken() {
        super("Soul Token", '$', false);
        soul = new SoulObject(0);

    }

    /**
     * Transfer souls method
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        if (soulObject.addSouls(soul.getNoOfSouls())) {
            subtractSouls(soul.getNoOfSouls());
        }
    }

    /**
     * Get soul
     *
     * @return The number of souls
     */
    @Override
    public int getSoul() {
        return 0;
    }

    /**
     * Add souls
     *
     * @param souls The number of souls to be incremented.
     * @return True if the souls can be added, false otherwise
     */
    @Override
    public boolean addSouls(int souls) {
        boolean isValid = false;
        if (souls >= 0) {
            soul.addSoul(souls);
            isValid = true;
        }
        return isValid;

    }

    /**
     * Checks if actor have ability to collect soul
     * automatically transfer soul
     * player do not need to click collect
     * then removes it
     * Reason: pick up item cannot be modified, if done by player needs additional checks
     *
     * @param actor an actor that will interact with this item
     * @return PickUpSoulTokenAction
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpSoulTokenAction(this);

    }

    /**
     * ensure that only when player dies it drops the item
     *
     * @param actor an actor that will interact with this item
     * @return DropItemAction() if the item is portable, null otherwise
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        if (portable && actor.hasCapability(Status.DEAD)) {
            return new DropItemAction(this);
        }
        return null;
    }

    /**
     * Used to remove this item also for resetting, so only 1 token can exist in the map
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (hasCapability(Status.TAKEN)) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * Remove this when reset
     */
    @Override
    public void resetInstance() {
        addCapability(Status.TAKEN);

    }

    /**
     * Check of the soul token it sill on the map
     *
     * @return True
     */
    @Override
    public boolean isExist() {
        return true;
    }

    /**
     * Displays the soul token information for printing purposes
     *
     * @return A string of the display
     */
    @Override
    public String toString() {
        return String.format("Soul token (%d)", soul.getNoOfSouls());
    }
}
