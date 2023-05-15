package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;

import java.util.ArrayList;

public interface Exchangeable {

    ArrayList<Item> getItemsToBeExchanged();

    ArrayList<WeaponItem> getWeaponItemsToBeExchanged();

    int getExchangeRewardedRunes();

    void removeExchangeableFromInventory(Actor actor);

}
