package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Purchasable;
import game.Sellable;
import game.controllers.PurchaseManager;
import game.controllers.SellManager;
import game.enums.Status;

public class GreatKnife extends WeaponItem implements Purchasable, Sellable {

  private static final int DAMAGE = 75;
  private static final int HIT_RATE = 70;
  private static final int PURCHASE_PRICE = 3500;
  private static final int SELL_PRICE = 350;

  /**
   * Constructor.
   *
   */
  //////////////////////////////////////////// XY - not sure about the verb /////////////
  public GreatKnife() {
    super("Great Knife", '/', DAMAGE, "hit", HIT_RATE);
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