package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resets.ResetManager;

/**
 * An Action for Actor to reset the whole game.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Action
 */
public class ResetAction extends Action {

    /**
     * When executed, all the enemies and items that are resettable will be reset.
     * @param actor The actor performing the reset action.
     * @param map The game map the actor is on.
     * @return the result of actor resetting the game.
     * @see ResetManager#getInstance()
     * @see ResetManager#run()
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        ResetManager.getInstance().run();

        return menuDescription(actor);
    }

    /**
     * Describes which actor resets the game.
     * @param actor The actor performing the action.
     * @return a description used for the menu UI.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " dies and the game is reset";
    }
}
