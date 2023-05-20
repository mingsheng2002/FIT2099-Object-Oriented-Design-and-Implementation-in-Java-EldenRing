package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Exchangeable;

/**
 * An Action for Actor to exchange certain items with the trader.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Action
 */
public class ExchangeAction extends Action {

    /**
     * Item to give to the Trader.
     */
    private Exchangeable exchangeable;
    /**
     * Weapon item that receive from the Trader.
     */
    private WeaponItem weaponItemToBeExchanged = null;
    /**
     * Item that receive from the Trader.
     */
    private Item itemToBeExchanged = null;

    /**
     * Constructor for ExchangeAction
     * @param exchangeable  The item that can be exchanged to Trader
     * @param weaponItemToBeExchanged The weapon item that can be exchanged from Trader
     */
    public ExchangeAction(Exchangeable exchangeable, WeaponItem weaponItemToBeExchanged) {
        this.exchangeable = exchangeable;
        this.weaponItemToBeExchanged = weaponItemToBeExchanged;
    }

    /**
     * Constructor for ExchangeAction
     * @param exchangeable  The item that can be exchanged to Trader
     * @param itemToBeExchanged The weapon item that cna be exchanged from Trader
     */
    public ExchangeAction(Exchangeable exchangeable, Item itemToBeExchanged) {
        this.exchangeable = exchangeable;
        this.itemToBeExchanged = itemToBeExchanged;
    }

    /**
     * When executed, this exchangeable item will be removed from player's inventory and
     * the new item or new weapon item will be added into player's inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of actor exchanging the item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // remove the exchangeable item
        exchangeable.removeExchangeableFromInventory(actor);
        // add into inventory the new item
        if (weaponItemToBeExchanged != null) {
            actor.addWeaponToInventory(weaponItemToBeExchanged);
        }
        else if (itemToBeExchanged != null) {
            actor.addItemToInventory(itemToBeExchanged);
        }
        return menuDescription(actor);
    }

    /**
     * Describes which actor exchanges which item.
     * @param actor The actor performing the action.
     * @return a description used for the menu UI.
     */
    @Override
    public String menuDescription(Actor actor) {
        String result = "";
        if (weaponItemToBeExchanged != null) {
            result = actor + " exchanges " + exchangeable + " for " + weaponItemToBeExchanged;
        }
        else if (itemToBeExchanged != null) {
            result = actor + " exchanges " + exchangeable + " for " + itemToBeExchanged;
        }
        return result;
    }
}
