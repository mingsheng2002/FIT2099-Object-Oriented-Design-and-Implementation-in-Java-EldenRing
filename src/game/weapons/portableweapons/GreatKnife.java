package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.PurchaseAction;
import game.actions.playeractions.SellAction;
import game.actions.weaponspecialskillactions.QuickStepAction;
import game.enums.Status;
import game.weapons.Purchasable;
import game.weapons.Sellable;
import game.weapons.weapontypes.Dagger;

public class GreatKnife extends Dagger implements Purchasable, Sellable {

  private static final int DAMAGE = 75;
  private static final int HIT_RATE = 70;
  private static final int PURCHASE_PRICE = 3500;
  private static final int SELL_PRICE = 350;

  /**
   * Constructor.
   *
   */
  public GreatKnife() {
    super("Great Knife", '/', DAMAGE, "hits", HIT_RATE);
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
  public int getSellPrice() {
    return SELL_PRICE;
  }

  @Override
  public Action getSellAction(Actor customer, Sellable sellable) {
    return new SellAction(customer, sellable);
  }

  @Override
  public Action getSkill(Actor target, String direction) {
      return new QuickStepAction(target, direction,this);
  }

  @Override
  public WeaponItem getPurchasableInstance() {
    return this;
  }
}
