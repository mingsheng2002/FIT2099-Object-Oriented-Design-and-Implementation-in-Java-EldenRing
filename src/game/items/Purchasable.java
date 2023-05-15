package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * A Purchasable interface represents a weapon item that a player can buy from the trader. It gives promises to every
 * weapon item that can be purchased from the trader such that their classes implementing this interface should have also
 * implemented these methods listed below.
 *
 * This interface is crucial so that when the player purchases a weapon item from the Trader, then the weapon's purchase price
 * can be accessed, initialize a new PurchaseAction and also get the instance of the weapon item class itself via the
 * implementation of the specific methods.
 *
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public interface Purchasable {

  /**
   * Get the purchase price of the weapon item
   * @return the purchase price of the weapon item
   */
  int getPurchasePrice();

  /**
   * Initialize a PurchaseAction
   * @param purchasable Weapon item that is being purchased by Actor purchaser
   * @return a PurchaseAction
   */
  Action getPurchaseAction(Purchasable purchasable);

//  /**
//   * Get an instance of the weapon item itself
//   * @return an instance of WeaponItem of itself
//   */
//  WeaponItem getPurchasableInstance();
  void addPurchasableToInventory(Actor actor);
}
