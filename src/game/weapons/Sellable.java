package game.weapons;

import edu.monash.fit2099.engine.actions.Action;

/**
 * A Sellable interface represents a weapon item that a player can sell to the trader. It gives promises to every
 * weapon item that can be sold to the trader such that their classes implementing this interface should have also
 * implemented these methods listed below.
 *
 * This interface is crucial so that when the player sells a weapon item to the Trader, then the weapon's selling price
 * can be accessed and initialization of a new SellAction via the implementation of the specific methods.
 *
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public interface Sellable {

  /**
   * Get the selling price of the weapon item
   * @return the selling price of the weapon item
   */
  int getSellPrice();

  /**
   * Initialize a SellAction
   * @param sellable Weapon item that is being sell by Actor customer
   * @return a SellAction
   */
  Action getSellAction(Sellable sellable);
}
