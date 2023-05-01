package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resets.ResetManager;

public class ResetAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {

        ResetManager.getInstance().run();

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " dies and the game is reset";
    }
}
