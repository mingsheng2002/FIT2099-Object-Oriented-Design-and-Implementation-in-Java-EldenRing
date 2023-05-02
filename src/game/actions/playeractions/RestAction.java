package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.controllers.RunesManager;
import game.enums.Status;

/**
 * An Action for Actor to rest at the Site of Lost Grace.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Action
 */
public class RestAction extends Action {

    /**
     * When executed, the player's runes will be added capability of RESTING before resetting the game. After resetting,
     * the capability of RESTING will be remvoved from the player's runes.
     * @param actor The actor performing the reset action.
     * @param map The game map the actor is on.
     * @return the result of actor resting on the Site Of Lost Grace
     * @see RestAction#execute(Actor, GameMap)
     * @see RunesManager#getPlayerRunes()
     * @see Status#RESTING
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // player resting, not dying - runes cannot be dropped
        RunesManager.getInstance().getPlayerRunes().addCapability(Status.RESTING);

        new ResetAction().execute(actor, map);

        // remove the RESTING after everything has been reset
        RunesManager.getInstance().getPlayerRunes().removeCapability(Status.RESTING);

        return menuDescription(actor);
    }

    /**
     * Describes which actor rests on the Site of Lost Grace and resets the game.
     * @param actor The actor performing the action.
     * @return a description used for the menu UI.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at the Site of Lost Grace and resets the game";
    }
}
