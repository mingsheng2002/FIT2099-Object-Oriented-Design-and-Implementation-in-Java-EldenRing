package game.actors.traders;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;
import game.weapons.Purchasable;
import game.weapons.Sellable;

import java.util.ArrayList;
import java.util.List;

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
   * A list of purchasable items
   */
  private List<Purchasable> purchasables = new ArrayList<>();
  /**
   * A list of sellable items
   */
  private List<Sellable> sellables = new ArrayList<>();

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
   * This method add purchasable item into purchasables list.
   * @param purchasable the purchasable item to add
   * @see Purchasable
   */
  public void addNewPurchasable(Purchasable purchasable) {
    purchasables.add(purchasable);
  }

  /**
   * This method add sellable item into sellables list.
   * @param sellable the sellable item to add
   * @see Sellable
   */
  public void addNewSellable(Sellable sellable){
    sellables.add(sellable);
  }

  /**
   * Getter that return a list of purchasable items.
   * @return a list of purchasable items
   */
  public List<Purchasable> getPurchasables() {
    return purchasables;
  }

  /**
   * Getter that return a list of sellable items.
   * @return a list of sellable items
   */
  public List<Sellable> getSellables() {
    return sellables;
  }
}
