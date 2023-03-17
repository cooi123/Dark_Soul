package game;

import game.interfaces.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that does soft-reset on the instances.
 * @author Lab7_Team6
 * @version 1.1
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private List<Resettable> resettableList;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager, if no instance exists calls constructor to instantiate
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance() {
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Constructor creating instance of Reset Manager
     */
    private ResetManager() {
        resettableList = new ArrayList<>();
    }

    /**
     * Method that facilitates game reset by traversing resettableList and calling
     * resetInstance for each to update their attributes such that each Actor's next turn
     * will finalise the Actor's reset by moving it's location
     */
    public void run() {
        cleanUp();
        for (Resettable object : resettableList) {
            object.resetInstance();
        }
    }

    /**
     * Method used in various constructors to add a particular instance
     * to a list of Resettable instances
     * @param resettable the interface instance (any class implementing the Resettable Interface)
     */
    public void appendResetInstance(Resettable resettable) {
        resettableList.add(resettable);
    }

    /**
     * Method that cleans up instances (actor, item, or ground) that doesn't exist anymore in the map
     */
    private void cleanUp() {

        for (Resettable object : resettableList) {
            if (!object.isExist()) {
                resettableList.remove(object);
            }
        }
    }
}
