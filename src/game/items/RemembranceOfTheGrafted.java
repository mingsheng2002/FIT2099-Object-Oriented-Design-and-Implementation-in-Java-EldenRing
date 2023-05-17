package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.ExchangeAction;
import game.actions.playeractions.SellAction;
import game.enums.Status;
import game.utils.SurroundingChecker;
import game.weapons.portableweapons.AxeOfGodrick;
import game.weapons.portableweapons.GraftedDragon;

import java.util.ArrayList;

public class RemembranceOfTheGrafted extends Item implements Sellable, Exchangeable {

    /**
     * Sell price of
     */
    private static final int SELL_PRICE = 20000;
    private static final int EXCHANGE_REWARDED_RUNES = 20000;
    private ArrayList<Item> itemsToBeExchanged = new ArrayList<>();
    private ArrayList<WeaponItem> weaponsToBeExchanged = new ArrayList<>();
    private SellAction sellAction;
    private ArrayList<ExchangeAction> exchangeActions;

    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', true);
        this.weaponsToBeExchanged.add(new AxeOfGodrick());
        this.weaponsToBeExchanged.add(new GraftedDragon());
        this.sellAction = new SellAction(this);
        initialiseExchangeActions();
    }

    public ArrayList<Item> getItemsToBeExchanged() {
        return this.itemsToBeExchanged;
    }

    public ArrayList<WeaponItem> getWeaponItemsToBeExchanged() {
        return this.weaponsToBeExchanged;
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
    public int getExchangeRewardedRunes() {
        return EXCHANGE_REWARDED_RUNES;
    }

    @Override
    public void removeExchangeableFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.removeAction(sellAction);
        this.clearExchangeActions();
        if (this.hasCapability(Status.READY_TO_BE_SOLD) && SurroundingChecker.surroundingHasActorWithCapability(currentLocation, Status.PROVIDE_SELL_SERVICE)) {
            this.addAction(sellAction);
            this.removeCapability(Status.READY_TO_BE_SOLD);
        }
        if (this.hasCapability(Status.READY_TO_BE_EXCHANGED) && SurroundingChecker.surroundingHasActorWithCapability(currentLocation, Status.PROVIDE_EXCHANGE_SERVICE)) {
            this.addExchangeActions();
            this.removeCapability(Status.READY_TO_BE_EXCHANGED);
        }
    }

    private void initialiseExchangeActions() {
        this.exchangeActions = new ArrayList<>();
        for (Item item : itemsToBeExchanged) {
            ExchangeAction exchangeAction = new ExchangeAction(this, item);
            exchangeActions.add(exchangeAction);
        }
        for (WeaponItem weaponItem : weaponsToBeExchanged) {
            ExchangeAction exchangeAction = new ExchangeAction(this, weaponItem);
            exchangeActions.add(exchangeAction);
        }
    }

    private void clearExchangeActions() {
        for (ExchangeAction exchangeAction : exchangeActions) {
            this.removeAction(exchangeAction);
        }
    }

    private void addExchangeActions() {
        for (ExchangeAction exchangeAction : exchangeActions) {
            this.addAction(exchangeAction);
        }
    }
}
