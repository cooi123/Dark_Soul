package game.weapons.weaponActiveAction;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Status;

/**
 * The ChargeAction class represents the Charging action that gets activated by the user holding the Storm Ruler when
 * they want to access the Wind Slash Action and is a child class of WeaponAction
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0.0
 * @see WeaponAction
 * @see game.weapons.StormRuler
 */
public class ChargeAction extends WeaponAction {

    /**
     * A constructor for ChargeAction
     *
     * @param weapon The Storm Ruler
     */
    public ChargeAction(WeaponItem weapon) {
        super(weapon);
    }

    /**
     * Executes the Charge action on the Storm Ruler
     *
     * @param actor The actor holding the Storm Ruler performing the charge action
     * @param map   The game map the actor is on
     * @return A string result of the charge action describing it
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.CHARGING);
        actor.addCapability(Status.DISARMED);
        return menuDescription(actor);
    }

    /**
     * A string description of the action
     *
     * @param actor The actor holding the Storm Ruler performing the charging action
     * @return A string of the text placed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " charges " + weapon.toString();
    }
}
