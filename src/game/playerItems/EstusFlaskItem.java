package game.playerItems;

import edu.monash.fit2099.engine.Item;
import game.actions.DrinkAction;
import game.enums.Abilities;

/**
 * A class representing the Estus Flask Item used to heal the Player
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Item
 */
public class EstusFlaskItem extends Item {

    private final int startChargeValue = 3;
    private int currentChargeValue = startChargeValue;
    private final int chargesExhausted = 0;
    private final static int healValue = 40;             //heals 40% of maxHP
    DrinkAction drink;

    /**
     * Estus Flask constructor used to call Super constructor and to add a HEAL capability used to indicate
     * full charge and to add a DrinkAction as allowable for the Estus Flask
     *
     * @param name        - used to name particular Estus Flask, however likely there will be only one called Estus Flask
     * @param displayChar - the character used to display the item
     * @param portable    - an indication of whether the item can be picked up and put down
     */
    public EstusFlaskItem(String name, char displayChar, boolean portable) {
        super(name, displayChar, false);
        addCapability(Abilities.HEAL);
        allowableActions.add(drink = new DrinkAction());
    }

    /**
     * Method used to identify the number of charges of a fully charged Estus Flask
     *
     * @return Integer of number of charges when full
     */
    public int getStartChargeValue() {
        return startChargeValue;
    }

    /**
     * Method used to identify the number of remaining charges
     *
     * @return Integer of number of remaining charges at a given time
     */
    public int getCurrentChargeValue() {
        return currentChargeValue;
    }


    /**
     * Static method that returns the Flask Item's heal value (which is consistent across Flasks)
     *
     * @return Integer healValue, representing the % of max hit points that the flask heals
     */
    public static int getHealValue() {
        return healValue;
    }


    /**
     * Method that decrements the number of current charges of an Estus Flask to
     * reflect its use to heal and changing an indicator when all charges are exhausted
     */
    public void useCharge() {
        currentChargeValue--;
        if (currentChargeValue == chargesExhausted && this.hasCapability(Abilities.HEAL)) {
            this.removeCapability(Abilities.HEAL);
            allowableActions.remove(drink);
        }
    }

    /**
     * Method used to reset number of charges for Estus Flask to full, start value and to
     * reinstate indicator that Estus Flask can heal
     */
    public void chargeReset() {
        currentChargeValue = startChargeValue;
        if (!this.hasCapability(Abilities.HEAL)) {
            addCapability(Abilities.HEAL);
            allowableActions.add(drink);
        }
    }

    /**
     * Overridden method providing description of Estus Flask Item including its current and starting
     * charge values
     *
     * @return String identifying Flask name and charge values
     */
    public String toString() {
        return name + " (" + currentChargeValue + "/" + startChargeValue + ")";
    }


}
