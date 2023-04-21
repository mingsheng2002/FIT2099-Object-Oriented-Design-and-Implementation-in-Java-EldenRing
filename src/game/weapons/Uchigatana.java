package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Purchasable;
import game.Sellable;
import game.controllers.PurchaseManager;
import game.controllers.SellManager;
import game.enums.Status;

public class Uchigatana extends WeaponItem implements Purchasable, Sellable {

  private static final int DAMAGE = 115;
  private static final int HIT_RATE = 80;
  private static final int PURCHASE_PRICE = 5000;
  private static final int SELL_PRICE = 500;

  /**
   * Constructor.
   */
  /////////////////////////////////////////// XY - not sure about the verb /////////////
  public Uchigatana() {
    super("Uchigatana", ')', DAMAGE, "hit", HIT_RATE);
    PurchaseManager.getInstance().registerPurchasable(this);
    SellManager.getInstance().registerSellable(this);
  }

  @Override
  public int getPurchasePrice() {
    return PURCHASE_PRICE;
  }

  @Override
  public int getSellPrice() {
    return SELL_PRICE;
  }
}
