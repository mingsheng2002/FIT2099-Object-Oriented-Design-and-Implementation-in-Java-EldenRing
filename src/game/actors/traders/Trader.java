package game.actors.traders;

import edu.monash.fit2099.engine.actors.Actor;
import game.weapons.Purchasable;
import game.weapons.Sellable;

public abstract class Trader extends Actor {

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Trader(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
  }

  public abstract void addNewPurchasable(Purchasable purchasable);

  public abstract void addNewSellable(Sellable sellable);
}
