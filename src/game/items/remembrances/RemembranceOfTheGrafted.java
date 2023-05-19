package game.items.remembrances;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.SellAction;
import game.enums.Status;
import game.items.Sellable;
import game.utils.SurroundingChecker;
import game.weapons.portableweapons.AxeOfGodrick;
import game.weapons.portableweapons.GraftedDragon;

public class RemembranceOfTheGrafted extends Remembrance implements Sellable {

    /**
     * Sell price of
     */
    private static final int SELL_PRICE = 20000;
    private SellAction sellAction;

    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', true);
        this.getWeaponItemsToBeExchanged().add(new AxeOfGodrick());
        this.getWeaponItemsToBeExchanged().add(new GraftedDragon());
        this.initialiseExchangeActions();
        this.sellAction = new SellAction(this);
    }

    @Override
    public int getSellPrice() {
        return SELL_PRICE;
    }

    @Override
    public void removeSellableFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.removeAction(sellAction);
        if (this.hasCapability(Status.READY_TO_BE_SOLD) && SurroundingChecker.surroundingHasActorWithCapability(currentLocation, Status.PROVIDE_SELL_SERVICE)) {
            this.addAction(sellAction);
            this.removeCapability(Status.READY_TO_BE_SOLD);
        }
        super.tick(currentLocation, actor);
    }

    @Override
    public DropAction getDropAction(Actor actor) {
        this.removeAction(sellAction);
        return super.getDropAction(actor);
    }
}
