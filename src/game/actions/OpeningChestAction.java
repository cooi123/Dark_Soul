package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enemy.Mimic;
import game.playerItems.SoulToken;
import game.utils.Utils;

/**
 * Action to interact with the chest, is a child class of Action
 *
 * @author Lab7_Team6
 * @version 1.0
 * @see Action
 */
public class OpeningChestAction extends Action {
    /**
     * chest- used for replacing the chest actor with Mimic or Soul tokens
     * no- a randomly generated number given by the caller of this function- Chest.
     */
    private Actor chest;
    private int no;

    /**
     * Stores the initial chest-used for replacing when mimic spawn and also remove the chest when opened
     * no is randomly generate within 1-3 provided by the chest- used for the number of soul token dropped.
     *
     * @param chest
     * @param no
     */
    public OpeningChestAction(Actor chest, int no) {
        this.chest = chest;
        this.no = no;
    }

    /**
     * 50% of dropping no of soul token- remove the chest when sucessful
     * 50% chance of spawining mimic using the replace actor action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return menuDescription()
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if (Utils.randomBooleanGenerator(50)) {
            for (int i = 0; i < no; i++) {
                SoulToken soulToken = new SoulToken();
                soulToken.addSouls(100);
                map.locationOf(chest).addItem(soulToken);
            }
        } else {
            Action spawnMimic = new ReplaceActorAction(map.locationOf(chest), chest, new Mimic(chest, map.locationOf(chest)));
            spawnMimic.execute(chest, map);
        }


        map.removeActor(chest);

        return menuDescription(actor);
    }

    /**
     * Displays the menu description for opening the chest
     *
     * @param actor The actor opening the chest
     * @return The printout to the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " open " + chest;
    }
}
