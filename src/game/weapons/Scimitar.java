package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;

public class Scimitar extends CurvedSword implements Purchasable, Sellable {

  private static final int DAMAGE = 118;
  private static final int HIT_RATE = 88;
  private static final int PURCHASE_PRICE = 600;
  private static final int SELL_PRICE = 100;

  /**
   * Constructor.
   *
   */
  public Scimitar() {
    super("Scimitar", 's', DAMAGE, "hits", HIT_RATE);
    this.addCapability(Status.AREA_ATTACK);
  }

  @Override
  public int getPurchasePrice() {
    return PURCHASE_PRICE;
  }

  @Override
  public int getSellPrice() {
    return SELL_PRICE;
  }

  @Override
  public WeaponItem getInstance() {
    return this;
  }
}
