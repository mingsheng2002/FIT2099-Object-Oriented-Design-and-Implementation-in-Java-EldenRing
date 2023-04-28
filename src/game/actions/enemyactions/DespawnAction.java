package game.actions.enemyactions;



import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;

public class DespawnAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        // actor is currently despawning from map in this round
        actor.addCapability(Status.DESPAWNING);
        map.removeActor(actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " has despawned from the map";
    }
}