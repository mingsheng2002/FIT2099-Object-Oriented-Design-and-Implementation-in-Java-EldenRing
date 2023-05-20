package game.items.remembrances;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.ExchangeAction;
import game.enums.Status;
import game.items.Exchangeable;
import game.items.Sellable;
import game.utils.SurroundingChecker;

import java.util.ArrayList;

/**
 *  An abstract class representing a Remembrance item.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Item
 * @see Exchangeable
 */
public abstract class Remembrance extends Item implements Exchangeable {

    /**
     *  A list of items to be exchanged
     */
    private ArrayList<Item> itemsToBeExchanged = new ArrayList<>();
    /**
     * A list of weapons to be exchanged
     */
    private ArrayList<WeaponItem> weaponsToBeExchanged = new ArrayList<>();

    /**
     * A list of Exchange actions
     */
    private ArrayList<ExchangeAction> exchangeActions;

    /**
     * Constructor for Remembrance.
     * @param name The name of the Remembrance.
     * @param displayChar The character representing the Remembrance when displayed.
     * @param portable Specifies if the Remembrance is portable or not.
     */
    public Remembrance(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Initializes the ExchangeActions for the Remembrance by creating ExchangeAction instances for each item and weapon to be exchanged.
     * @see Item
     * @see ExchangeAction
     * @see WeaponItem
     */
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

    /**
     * Removes the ExchangeActions from the Remembrance's allowable actions.
     * @see ExchangeAction
     */
    private void clearExchangeActions() {
        for (ExchangeAction exchangeAction : exchangeActions) {
            this.removeAction(exchangeAction);
        }
    }

    /**
     * Adds the ExchangeActions to the Remembrance's allowable actions.
     * @see ExchangeAction
     */
    private void addExchangeActions() {
        for (ExchangeAction exchangeAction : exchangeActions) {
            this.addAction(exchangeAction);
        }
    }

    /**
     * Clears the ExchangeActions and adds ExchangeActions if the Remembrance is ready to be exchanged and the surrounding actors can provide exchange service.
     * @param currentLocation The location of the actor carrying this Remembrance.
     * @param actor The actor carrying this Remembrance.
     * @see Status#READY_TO_BE_EXCHANGED
     * @see Status#PROVIDE_EXCHANGE_SERVICE
     * @see SurroundingChecker#surroundingHasActorWithCapability(Location, Status)
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.clearExchangeActions();
        if (this.hasCapability(Status.READY_TO_BE_EXCHANGED) && SurroundingChecker.surroundingHasActorWithCapability(currentLocation, Status.PROVIDE_EXCHANGE_SERVICE)) {
            this.addExchangeActions();
            this.removeCapability(Status.READY_TO_BE_EXCHANGED);
        }
    }

    /**
     * Removes the Remembrance from the actor's inventory.
     * @param actor The actor removing the Remembrance.
     */
    @Override
    public void removeExchangeableFromInventory(Actor actor) {
        actor.removeItemFromInventory(this);
    }

    /**
     * Getter to get the list of items to be exchanged.
     * @return a list of items to be exchanged
     */
    @Override
    public ArrayList<Item> getItemsToBeExchanged() {
        return this.itemsToBeExchanged;
    }

    /**
     * Getter to get the list of weapons to be exchanged.
     * @return a list of weapons to be exchanged
     */
    @Override
    public ArrayList<WeaponItem> getWeaponItemsToBeExchanged() {
        return this.weaponsToBeExchanged;
    }

    /**
     *  Clears the ExchangeActions and drop the Remembrance.
     * @param actor The actor dropping this Item
     * @return The DropAction for dropping this Item.
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        this.clearExchangeActions();
        return super.getDropAction(actor);
    }
}
