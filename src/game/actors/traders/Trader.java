package game.actors.traders;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;
import game.weapons.Purchasable;
import game.weapons.Sellable;

/**
 * An abstract trader class
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Actor
 */
public abstract class Trader extends Actor {

  /**
   * Constructor for Trader.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   * @see Status#PROTECTED
   */
  public Trader(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    this.addCapability(Status.PROTECTED); // cannot be attacked
  }

  /**
   * An abstract method that add new purchasable item.
   * @param purchasable the purchasable item to add
   */
  public abstract void addNewPurchasable(Purchasable purchasable);

  /**
   * An abstract method that add new sellable item.
   * @param sellable the sellable item to add
   */
  public abstract void addNewSellable(Sellable sellable);
}
