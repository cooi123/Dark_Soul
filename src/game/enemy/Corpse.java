package game.enemy;

import edu.monash.fit2099.engine.*;
import game.actions.RespawnAction;
import game.enums.Abilities;

/**
 * Corpse for place holder for actor to revive and is a child class of Actor
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Actor
 */
public class Corpse extends Actor {

    /**
     * An instance of the body of the corpse
     */
    Actor mainBody;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param body        the body to will be revive;
     * @param displayChar the character that will represent the Actor in the display
     */
    public Corpse(String name, char displayChar, Actor body) {
        super(name, displayChar, 0);
        addCapability(Abilities.UNTARGETABLE);
        this.mainBody = body;
    }

    /**
     * Execute the respawn the next turn when this is created.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return RespawnAction()
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        return new RespawnAction(map.locationOf(this), mainBody);
    }
}
