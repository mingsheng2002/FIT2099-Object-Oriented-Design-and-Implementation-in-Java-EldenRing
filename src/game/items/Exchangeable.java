package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import java.util.ArrayList;

/**
 * An Exchangable interface represents an item that a player can exchange with some specific trader. It gives promises to every
 * item that can be exchanged to the specific trader such that their classes implementing this interface should have also
 * implemented these methods listed below.
 *
 * This interface is crucial so that when the player exchange an item to the Trader, then the item should be removed via
 * the removeExchangeableFromInventory() method.
 *
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public interface Exchangeable {

    /**
     * Remove the item from actor's inventory after exchanging
     * @param actor
     */
    void removeExchangeableFromInventory(Actor actor);

    /**
     * Getter to get the list of items to be exchanged.
     * @return a list of items to be exchanged
     */
    ArrayList<Item> getItemsToBeExchanged();

    /**
     * Getter to get the list of weapons to be exchanged.
     * @return a list of weapons to be exchanged
     */
    ArrayList<WeaponItem> getWeaponItemsToBeExchanged();

}
