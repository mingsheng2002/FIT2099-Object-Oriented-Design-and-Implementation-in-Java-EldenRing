package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.controllers.RunesManager;
import game.items.Runes;

public class PickUpRunesAction extends PickUpAction {

    private final Runes runes;
    private int amount;
    private Player player;

    public PickUpRunesAction(Runes runes,Player player) {
        super(runes);
        this.runes = runes;
        this.player = player;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // change back to not portable
        runes.togglePortability();
        runes.setHasPickUp(true);

        amount =  runes.getTotalAmount();
        RunesManager.getInstance().incrementAmount(amount);
        map.locationOf(actor).removeItem(runes);

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " retreive Runes (value: "+ getAmount() + ")";
    }
}
