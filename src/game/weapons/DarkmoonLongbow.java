package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.RangeInteraction;
import game.utils.Utils;
import game.weapons.weaponActiveAction.FireArrowAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Darkmoon longbow class, is a game weapon item, implements range interaction (Dependency inversion)
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see GameWeaponItem
 */
public class DarkmoonLongbow extends GameWeaponItem implements RangeInteraction {
    private int range;
    WeaponAction activeSkill = null;
    Actor currentActor;
    private Actor holder;
    ArrayList<Location> location = new ArrayList<>();

    /**
     * Constructor initialised the instance variable
     */
    public DarkmoonLongbow() {
        super("Darkmoon Longbow", 'r', 70, "shot", 80);
        addCapability(Abilities.RANGEATTACK);
        addCapability(Abilities.TRADE);
        range = 3;
    }

    /**
     * Check the surrounding aronud to find actor that can be interact with using the get targets method
     * adds the actor that can be attacked using range attackAction into allowable actions
     * Initialised the active skills as Fire arrow requires the target
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        holder = actor;
        GameMap map = currentLocation.map();
        allowableActions.clear();
        activeSkill = null;
        ArrayList<Actor> actors = getTargets(actor, map);
        for (Actor target : actors) {
            if (!target.hasCapability(Abilities.UNTARGETABLE)) {
                location.add(map.locationOf(target));
                Location targetLocation = map.locationOf(target);

                String xDirection = "";
                if (currentLocation.x() - targetLocation.x() > 0) {
                    xDirection = "left " + (currentLocation.x() - targetLocation.x()) + ",";
                } else if (currentLocation.x() - targetLocation.x() < 0) {
                    xDirection = "right " + ((currentLocation.x() - targetLocation.x()) / -1) + ",";
                }
                String yDirection = "";
                if (currentLocation.y() - targetLocation.y() > 0) {
                    yDirection = " up " + (currentLocation.y() - targetLocation.y());
                } else if (currentLocation.y() - targetLocation.y() < 0)
                    yDirection = " down " + ((currentLocation.y() - targetLocation.y()) / -1);
                String cords = xDirection + yDirection;

//                String cords = targetLocation.x() + " ," + targetLocation.y();
                allowableActions.add(new RangeAttackAction(target, cords, this));
                activeSkill = new FireArrowAction(this, target, cords);
            }
        }
        currentActor = actor;
        super.tick(currentLocation, actor);
    }

    /**
     * Goes through each of the location obtain from tick and triggers the get allowable action(similar to how engine triggers them)
     *
     * @return A list of actions that are allowed by the actor using the Longbow
     */
    @Override
    public List<Action> getAllowableActions() {
        for (Location location : location) {
            if (location.containsAnActor()) {
                holder.getAllowableActions(location.getActor(), "", location.map());
            }
        }
        location.clear();
        return allowableActions.getUnmodifiableActionList();
    }

    /**
     * Checks if the actor has ability of activating
     *
     * @param target    The target actor
     * @param direction The direction of target, e.g. "north"
     * @return Weapon action fire arrow active
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if (target.hasCapability(Status.ENRAGE)) {
            addCapability(Status.EMBER_FORM);
            return activeSkill;
        }
        return super.getActiveSkill(target, direction);
    }

    /**
     * Used for the passive weapon mode
     *
     * @return The damage made by the longbow
     */
    @Override
    public int damage() {
        if (Utils.randomBooleanGenerator(15)) {
            return (damage * 2);
        }
        return super.damage();
    }

    /**
     * Range interaction interface
     *
     * @return The range of the longbow
     */
    @Override
    public int getRange() {
        return range;
    }

    /**
     * Uses number range to generate an iterable list of the location between the target and the shooter
     * Only targetable when face to face (directly straight on and theres no ground that can block object e.g wall)
     * This is used in range attack behaviour to check if the attack is blocked
     *
     * @param actor The actor throwing the longbow
     * @param target The target of the attack
     * @param map The game map the actors are on
     * @return True if the target can be targeted, false otherwise
     */
    @Override
    public boolean targetable(Actor actor, Actor target, GameMap map) {
        boolean targetable = false;
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        NumberRange xs, ys;
        if (Math.abs(here.x() - there.x()) <= range && Math.abs(here.y() - there.y()) <= range) {
            if (here.x() == there.x() || here.y() == there.y()) {
                xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
                ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

                for (int x : xs) {
                    for (int y : ys) {
                        Ground currentGround = (map.at(x, y).getGround());
                        if (currentGround.blocksThrownObjects())
                            return false;
                    }
                }
                targetable = true;
            }
        }

        return targetable;
    }

    /**
     * Calculate the x and y distance to make sure that they are within the range.
     *
     * @param shooter The actor shooting the arrow
     * @param target The target of the attack
     * @param map The game map the actors are on
     * @return True if the target is within range, false otherwise
     */
    @Override
    public boolean targetWithinRange(Actor shooter, Actor target, GameMap map) {
        boolean isValid = false;
        Location here = map.locationOf(shooter);
        Location there = map.locationOf(target);
        if (Math.abs(here.x() - there.x()) <= range && Math.abs(here.y() - there.y()) <= range) {
            isValid = true;
        }

        return isValid;

    }

    /**
     * Generate a iterable list from (3 left, 3 right, 3 up and 3 down)
     * Checks if it contains actor and also it is within the map and the adds the actor in to an array list
     * Return the list to be used the tick.
     *
     * @param shooter The actor shooting the arrow
     * @param map The game map the shooter is on
     * @return An array of all possible targets within the range
     */
    public ArrayList<Actor> getTargets(Actor shooter, GameMap map) {
        ArrayList<Actor> actors = new ArrayList<>();
        Location here = map.locationOf(shooter);
        NumberRange xs, ys;

        xs = new NumberRange(here.x() - range, range * 2 + 1);
        ys = new NumberRange(here.y() - range, range * 2 + 1);
        NumberRange mapXs = map.getXRange();
        NumberRange mapYs = map.getYRange();
        for (int x : xs) {
            for (int y : ys) {
                if (mapXs.contains(x) && mapYs.contains(y)) {
                    if (map.at(x, y).containsAnActor() && map.at(x, y).getActor() != shooter) {
                        Actor currentActor = map.at(x, y).getActor();
                        actors.add(currentActor);
                    }
                }
            }
        }
        return actors;
    }

    /**
     * To string method, changes if its in ember form
     *
     * @return A string of the name of the weapon and the weapon mode
     */
    public String toString() {
        String weaponMode = "";
        if (this.hasCapability(Status.EMBER_FORM)) {
            weaponMode = " Ember Form";
        }
        return name + weaponMode;
    }
}
