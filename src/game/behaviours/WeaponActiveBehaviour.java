package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.utils.Utils;

/**
 * Weapon active behaviour
 *
 * @author Lab7_Team6
 * @version 1.0
 */
public class WeaponActiveBehaviour implements Behaviour {

    /**
     * If actor not activating, randomly give the active skills to be activated.
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return The active skills associated with the weapon
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        WeaponAction activeSkill = null;

        if (actor.hasCapability(Status.ACTIVATING_SKILL) || Utils.randomBooleanGenerator(50)) {
            activeSkill = actor.getWeapon().getActiveSkill(actor, "360");
        }

        return activeSkill;

    }


}
