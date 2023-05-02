package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * An Action for Actor to consume some special items that can be consumed in the game.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Action
 */
public class ConsumeAction extends Action {

    /**
     * A consumable item.
     */
    Consumable consumable;

    /**
     * Constructor that initialise the consumable item in this action.
     * @param consumable
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * When executed, the actor will consume the particular consumable item and the effect will take place accordingly.
     * @param actor The actor performing the consume action.
     * @param map The game map the actor is on.
     * @return the result of actor consuming the item.
     * @see Consumable#consumedBy(Actor)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        consumable.consumedBy(actor);
        return menuDescription(actor);
    }

    /**
     * Describes which actor consumes which consumable item.
     * @param actor The actor performing the action.
     * @return a description used for the menu UI.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.consumable;
    }
}
