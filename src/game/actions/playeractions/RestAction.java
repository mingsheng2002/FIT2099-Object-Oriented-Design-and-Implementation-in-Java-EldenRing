package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.controllers.RunesManager;
import game.enums.Status;

public class RestAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        // player resting, not dying - runes cannot be dropped
        RunesManager.getInstance().getPlayerRunes().addCapability(Status.RESTING);

        new ResetAction().execute(actor, map);

        // remove the RESTING after everything has been reset
        RunesManager.getInstance().getPlayerRunes().removeCapability(Status.RESTING);

        return "\nGame has successfully been reset\n";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at the Site of Lost Grace and resets the game";
    }
}
