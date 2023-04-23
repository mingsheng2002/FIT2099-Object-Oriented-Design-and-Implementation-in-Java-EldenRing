package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.enums.Status;

public class ConsumeAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.RESTING)){
            actor.heal(250);
            // increase the usage by one TBC
            for (Item item : actor.getItemInventory()){
                if (item.hasCapability(Status.HEALING)){
                    item.tick(map.locationOf(actor));
                }

            }


        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes Flask Of Crimson Tears";
    }
}
