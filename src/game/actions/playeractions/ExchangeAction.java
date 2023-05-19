package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.remembrances.Exchangeable;

public class ExchangeAction extends Action {

    private Exchangeable exchangeable;
    private WeaponItem weaponItemToBeExchanged = null;
    private Item itemToBeExchanged = null;

    public ExchangeAction(Exchangeable exchangeable, WeaponItem weaponItemToBeExchanged) {
        this.exchangeable = exchangeable;
        this.weaponItemToBeExchanged = weaponItemToBeExchanged;
    }

    public ExchangeAction(Exchangeable exchangeable, Item itemToBeExchanged) {
        this.exchangeable = exchangeable;
        this.itemToBeExchanged = itemToBeExchanged;
    }

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
