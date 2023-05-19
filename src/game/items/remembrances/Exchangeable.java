package game.items.remembrances;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;

import java.util.ArrayList;

public interface Exchangeable {

    void removeExchangeableFromInventory(Actor actor);

    ArrayList<Item> getItemsToBeExchanged();

    ArrayList<WeaponItem> getWeaponItemsToBeExchanged();

}
