package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.PurchaseAction;
import game.actions.playeractions.SellAction;
import game.enums.Status;
import game.weapons.Purchasable;
import game.weapons.Sellable;
import game.weapons.weapontypes.CurvedSword;

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
  public Action getPurchaseAction(Actor purchaser, Purchasable purchasable) {
    return new PurchaseAction(purchaser, purchasable);
  }

  @Override
  public WeaponItem getPurchasableInstance() {
    return this;
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
