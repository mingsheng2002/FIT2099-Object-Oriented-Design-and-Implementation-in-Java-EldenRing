package game.items.remembrances;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.ExchangeAction;
import game.enums.Status;
import game.utils.SurroundingChecker;
import game.weapons.portableweapons.AxeOfGodrick;
import game.weapons.portableweapons.GraftedDragon;

import java.util.ArrayList;

public abstract class Remembrance extends Item implements Exchangeable {

    private ArrayList<Item> itemsToBeExchanged = new ArrayList<>();
    private ArrayList<WeaponItem> weaponsToBeExchanged = new ArrayList<>();

    private ArrayList<ExchangeAction> exchangeActions;

    public Remembrance(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public void initialiseExchangeActions() {
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

    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.clearExchangeActions();
        if (this.hasCapability(Status.READY_TO_BE_EXCHANGED) && SurroundingChecker.surroundingHasActorWithCapability(currentLocation, Status.PROVIDE_EXCHANGE_SERVICE)) {
            this.addExchangeActions();
            this.removeCapability(Status.READY_TO_BE_EXCHANGED);
        }
    }

    @Override
    public void removeExchangeableFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }

    @Override
    public ArrayList<Item> getItemsToBeExchanged() {
        return this.itemsToBeExchanged;
    }

    @Override
    public ArrayList<WeaponItem> getWeaponItemsToBeExchanged() {
        return this.weaponsToBeExchanged;
    }

    @Override
    public DropAction getDropAction(Actor actor) {
        this.clearExchangeActions();
        return super.getDropAction(actor);
    }
}
