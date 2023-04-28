package game.actions.playeractions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.playerclasses.Player;
import game.controllers.RunesManager;
import game.items.Runes;

public class PickUpRunesAction extends PickUpAction {

    private final Runes playerRunes;
    private final Runes droppedRunes;
    private int amount;
    private Actor player;

    public PickUpRunesAction(Actor actor) {
        super(RunesManager.getInstance().getDroppedRunes());
        this.playerRunes = RunesManager.getInstance().getPlayerRunes();
        this.droppedRunes = RunesManager.getInstance().getDroppedRunes();
        this.player = actor;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Runes droppedRunes = RunesManager.getInstance().getDroppedRunes();
        RunesManager.getInstance().incrementPlayerRunes(droppedRunes.getTotalAmount());
        playerRunes.pickedUp();
        return super.execute(actor, map);
    }

    public String menuDescription(Actor actor) {
        return actor + " retrieves " + droppedRunes;
    }
}
