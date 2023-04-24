package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;

public class ResetAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return "Game has successfully been reset.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " resets the game.";
    }
}
