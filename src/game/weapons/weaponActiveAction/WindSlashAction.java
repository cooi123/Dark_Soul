package game.weapons.weaponActiveAction;

import edu.monash.fit2099.engine.*;
import game.actions.DyingAction;
import game.enums.Status;
import game.weapons.StormRuler;

/**
 * The WindSlashAction class represents the Wind Slash Action that gets activated when the Storm Ruler if fully charged
 * and is a child class of WeaponAction
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0.0
 * @see WeaponAction
 * @see StormRuler
 */
public class WindSlashAction extends WeaponAction {

    /**
     * The target of the Wind Slash Action attack
     */
    Actor target;

    /**
     * A constructor for WindSlashAction
     *
     * @param target The target of the Wind Slash Action attack
     */
    public WindSlashAction(Actor target) {
        super(new StormRuler());
        this.target = target;
    }

    /**
     * Executes the Wind Slash Action on Yhorm the Giant
     *
     * @param actor The actor holding the Storm Ruler performing the wind slash action
     * @param map   The game map the actor is on
     * @return A string result of the wind slash action describing the attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.ACTIVATING_SKILL);

        String result = "";

        target.addCapability(Status.STUNNED);
        actor.removeCapability(Status.FULLY_CHARGED);
        actor.removeCapability(Status.CHARGING);
        result += menuDescription(actor);
        int damage = weapon.damage();
        result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        Action dying = new DyingAction(target);
        result += dying.execute(actor, map);
        if (result == "") {
            result = actor + " is not standing adjacent to Yhorm";
        }
        return result;
    }

    /**
     * A string description of the action
     *
     * @param actor The actor holding the Storm Ruler performing the wind slash action
     * @return A string of the text placed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " stuns " + target + " for 1 turn ";
    }

}
