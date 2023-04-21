package game.weapons;

import game.Sellable;
import game.controllers.PurchaseManager;
import game.controllers.SellManager;
import game.enums.Status;

public class Grossmesser extends CurvedSword implements Sellable {

  private static final int DAMAGE = 115;
  private static final int HIT_RATE = 85;
  private static final int SELL_PRICE = 100;

  /**
   * Constructor.
   *
   */
  public Grossmesser() {
    super("Grossmesser", '?', DAMAGE, "uses Grossmesser to attack", HIT_RATE);
    this.addCapability(Status.AREA_ATTACK);
    SellManager.getInstance().registerSellable(this);
  }

  @Override
  public int getSellPrice() {
    return SELL_PRICE;
  }
}
