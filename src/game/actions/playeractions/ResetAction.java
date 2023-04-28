package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.controllers.RunesManager;
import game.enums.Status;
import game.resets.ResetManager;

public class ResetAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        // player choose to reset, not dying
        if (actor.isConscious()) {
            RunesManager.getInstance().getPlayerRunes().addCapability(Status.TEMPORARILY_UNDROPPABLE);
        }
        ResetManager.getInstance().run();
        // player choose to reset, not dying
        if (actor.isConscious()) {
            RunesManager.getInstance().getPlayerRunes().removeCapability(Status.TEMPORARILY_UNDROPPABLE);
        }
        return "Game has successfully been reset";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " resets the game";
    }
}
