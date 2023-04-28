package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
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
    super("Grossmesser", '?', DAMAGE, "hits", HIT_RATE);
    this.addCapability(Status.AREA_ATTACK);
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
