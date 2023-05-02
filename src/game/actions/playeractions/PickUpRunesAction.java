package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.controllers.RunesManager;
import game.items.Runes;

/**
 * An Action for Actor to pick up the runes dropped on the ground.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see PickUpAction
 */
public class PickUpRunesAction extends PickUpAction {

    /**
     * Runes that is held by the player.
     */
    private final Runes playerRunes;
    /**
     * Runes that is dropped onto the ground.
     */
    private final Runes droppedRunes;
    /**
     * Player to pick up the runes.
     */
    private Actor player;

    /**
     * Constructor that initialises player's runes and dropped runes.
     * @param actor actor to pick up the runes.
     * @see RunesManager#getInstance()
     * @see RunesManager#getPlayerRunes()
     * @see RunesManager#getDroppedRunes()
     */
    public PickUpRunesAction(Actor actor) {
        super(RunesManager.getInstance().getDroppedRunes());
        this.playerRunes = RunesManager.getInstance().getPlayerRunes();
        this.droppedRunes = RunesManager.getInstance().getDroppedRunes();
        this.player = actor;
    }

    /**
     * When executed, the dropped runes amount will be added into the player's runes amount.
     * @param actor The actor performing the pick-up-runes action.
     * @param map The game map the actor is on.
     * @return the result of actor picking up the dropped runes from the ground.
     * @see RunesManager#getInstance()
     * @see RunesManager#incrementPlayerRunes(int)
     * @see RunesManager#getDroppedRunes()
     * @see PickUpAction#execute(Actor, GameMap)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Runes droppedRunes = RunesManager.getInstance().getDroppedRunes();
        RunesManager.getInstance().incrementPlayerRunes(droppedRunes.getTotalAmount());
        playerRunes.pickedUp();
        return super.execute(actor, map);
    }

    /**
     * Describes which actor retrieves the runes from the ground.
     * @param actor The actor performing the action.
     * @return a description used for the menu UI.
     */
    public String menuDescription(Actor actor) {
        return actor + " retrieves " + droppedRunes;
    }
}
