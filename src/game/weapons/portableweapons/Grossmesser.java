package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.SellAction;
import game.enums.Status;
import game.weapons.Sellable;
import game.weapons.weapontypes.CurvedSword;

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
  public Action getSellAction(Actor customer, Sellable sellable) {
    return new SellAction(customer, sellable);
  }

}
