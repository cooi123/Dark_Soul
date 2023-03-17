package game.playerItems;

/**
 * Class representing the number of Souls of an Actor or Item.
 *
 * @author Lab7_Team6
 * @version 1.0
 */
public class SoulObject {

    /**
     * An instance of the number of souls
     */
    private int noOfSouls;

    /**
     * Constructor instantiating SoulObjects
     *
     * @param newNoOfSouls The number of Souls that the Actor or Item with the SoulObject has
     */
    public SoulObject(int newNoOfSouls) {
        setNoOfSouls(newNoOfSouls);
    }

    /**
     * Method to set the number of souls represented by a SoulObject
     *
     * @param newNoOfSouls The number of Souls that the Actor or Item with the SoulObject has
     */
    public void setNoOfSouls(int newNoOfSouls) {
        if (newNoOfSouls > 0) {
            noOfSouls = newNoOfSouls;
        }
    }

    /**
     * Method to access the number of souls represented by a SoulObject
     *
     * @return Integer value representing the number of souls
     */
    public int getNoOfSouls() {
        return noOfSouls;
    }

    /**
     * Method to increase the number of souls represented by a SoulObject
     *
     * @param noToAdd Integer value representing the increase in the number of souls
     */
    public void addSoul(int noToAdd) {
        noOfSouls += noToAdd;
    }

    /**
     * Method to decrease the number of souls represented by a SoulObject
     *
     * @param noToSub Integer value representing the decrease in the number of souls
     */
    public void subtractSoul(int noToSub) {
        noOfSouls -= noToSub;
    }


}
