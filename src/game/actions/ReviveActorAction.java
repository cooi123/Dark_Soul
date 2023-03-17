package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.actions.ReplaceActorAction;
import game.actions.RespawnAction;

/**
 * The ReviveActorAction class revives an actor in the game. it is a child class of ReplaceActorAction
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 * @see ReplaceActorAction
 */
public class ReviveActorAction extends ReplaceActorAction {
    /**
     * Takes in the spawn location and the actor that has to be revived
     *
     * @param respawnLocation The location the actor will be respawned at
     * @param actor The actor to be replaced
     * @param replacementActor The actor replacing the current one
     */
    public ReviveActorAction(Location respawnLocation, Actor actor, Actor replacementActor) {
        super(respawnLocation, actor, replacementActor);
    }

    /**
     * Respawns the actor next turn
     *
     * @return RespawnAction()
     */
    @Override
    public Action getNextAction() {
        return new RespawnAction(getRespawnLocation(), getActor());
    }

}
