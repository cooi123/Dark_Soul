package game.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This interface is used for any class that requires range interaction between the actors
 * This interface can be used on items, ground or even actors.
 *
 * @author Lab7_Team6
 * @version 1.0
 */
public interface RangeInteraction {
    /**
     * @return Range
     */
    public int getRange();

    /**
     * checks whether the target is targetable by the shooter.
     * E.g Can be used to check if there's a wall in the path
     *
     * @param shooter The actor holding the range item
     * @param target  The actor that is being targeted
     * @param map     The map where both actors are currently on.
     * @return boolean
     */
    public boolean targetable(Actor shooter, Actor target, GameMap map);

    /**
     * Checks within the range if the target is there
     *
     * @param shooter Actor holding the item
     * @param target  Actor thats being targeted
     * @param map     The map whgich both actors are on
     * @return boolean
     */
    public boolean targetWithinRange(Actor shooter, Actor target, GameMap map);

    /**
     * check if its an instance of range and cast it to range
     *
     * @return a down cast object that has a reference of this interface
     */
    default RangeInteraction asRange() {
        return this instanceof RangeInteraction ? (RangeInteraction) this : null;
    }


}
