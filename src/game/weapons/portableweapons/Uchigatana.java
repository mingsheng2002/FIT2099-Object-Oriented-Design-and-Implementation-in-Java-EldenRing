package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.PurchaseAction;
import game.actions.playeractions.SellAction;
import game.actions.weaponspecialskillactions.UnsheatheAction;
import game.enums.Status;
import game.weapons.Purchasable;
import game.weapons.Sellable;
import game.weapons.weapontypes.Katana;

public class Uchigatana extends Katana implements Purchasable, Sellable {

  private static final int DAMAGE = 115;
  private static final int HIT_RATE = 80;
  private static final int PURCHASE_PRICE = 5000;
  private static final int SELL_PRICE = 500;

  /**
   * Constructor.
   */
  public Uchigatana() {
    super("Uchigatana", ')', DAMAGE, "hits", HIT_RATE);
    this.addCapability(Status.SPECIAL_SKILL);
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

  public Action getSellAction(Actor customer, Sellable sellable) {
    return new SellAction(customer, sellable);
  }

  @Override
  public Action getSkill(Actor target, String direction) {
      return new UnsheatheAction(target, direction,this);
  }
}
