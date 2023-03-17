package game.enemy;

import edu.monash.fit2099.engine.*;
import game.playerItems.SoulToken;
import game.actions.RespawnAction;
import game.enums.Status;
import game.utils.Utils;

/**
 * Mimic class, is part of enemy
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Enemy
 */
public class Mimic extends Enemy {

    /**
     * The mimic's initial location
     */
    private Location initialLocation;

    /**
     * An instance of the mimic
     */
    private Actor initialActor;

    /**
     * Takes the inital location, used when reset
     * Takes initial actor, used when reset
     *
     * @param initialActor The initial actor of the mimic
     * @param initialLocation The initial location of the mimic
     */
    public Mimic(Actor initialActor, Location initialLocation) {
        super("Mimic", 'M', 100, 200);
        this.initialActor = initialActor;
        this.initialLocation = initialLocation;

    }

    /**
     * Used to process the actor each turn.
     * When dead, generate random number 1- 3 for amount of token drop
     * When reset, uses respawn action -respawn as chest instead of mimic
     *
     * @param actions The possible actions that can be made this turn
     * @param lastAction The previous action made
     * @param map The game map the mimic is on
     * @param display The I/O object to which messages may be written
     * @return The playTurn() method in Enemy
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            int no = Utils.randomNumberGenerator(1, 4);
            for (int i = 0; i < no; i++) {
                SoulToken soulToken = new SoulToken();
                soulToken.addSouls(100);
                map.locationOf(this).addItem(soulToken);
            }
        } else if (hasCapability(Status.RESET)) {
            return new RespawnAction(initialLocation, initialActor);
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Reset
     */
    @Override
    public void resetInstance() {
        addCapability(Status.RESET);
    }

    /**
     * Accesses an intrinsic weapon
     * @return IntrinsicWeapon()
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(55, "Kick");
    }
}
