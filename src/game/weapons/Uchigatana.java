package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.weaponspecialskillactions.UnsheatheAction;
import game.enums.Status;


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
  public int getSellPrice() {
    return SELL_PRICE;
  }

  @Override
  public WeaponItem getInstance() {
    return this;
  }
//
  @Override
  public Action getSkill(Actor target, String direction) {
      return new UnsheatheAction(target, direction,this);
  }


}
