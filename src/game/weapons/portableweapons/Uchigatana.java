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
/**
 * A weapon that is carried by Samurai.
 * It deals 115 damage with 80% attack accuracy.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Katana
 * @see Purchasable
 * @see Sellable
 */
public class Uchigatana extends Katana implements Purchasable, Sellable {

  /**
   * Damage caused by Uchigatana
   */
  private static final int DAMAGE = 115;
  /**
   * Attack accuracy of Uchigatana
   */
  private static final int HIT_RATE = 80;
  /**
   * Purchase price of Uchigatana
   */
  private static final int PURCHASE_PRICE = 5000;
  /**
   * Sell price of Uchigatana
   */
  private static final int SELL_PRICE = 500;

  /**
   * Constructor for Uchigatana.
   * @see Status#SPECIAL_SKILL
   */
  public Uchigatana() {
    super("Uchigatana", ')', DAMAGE, "hits", HIT_RATE);
    this.addCapability(Status.SPECIAL_SKILL);
  }

  /**
   * Returns the purchase price of Uchigatana.
   * @return int that representing the purchase price of Uchigatana.
   */
  @Override
  public int getPurchasePrice() {
    return PURCHASE_PRICE;
  }

  /**
   * Returns an instance of PurchaseAction when Uchigatana is purchased by the player.
   * @param purchaser The actor that is purchasing the weapon.
   * @param purchasable The weapon that is being purchased.
   * @return an instance of PurchaseAction.
   * @see PurchaseAction
   */
  @Override
  public Action getPurchaseAction(Actor purchaser, Purchasable purchasable) {
    return new PurchaseAction(purchaser, purchasable);
  }

  /**
   * Returns current instance of Uchigatana.
   * @return an instance of Uchigatana.
   */
  @Override
  public WeaponItem getPurchasableInstance() {
    return this;
  }

  /**
   * Returns the sell price of Uchigatana.
   * @return int that representing the sell price of Uchigatana.
   */
  @Override
  public int getSellPrice() {
    return SELL_PRICE;
  }

  /**
   * Returns an instance of SellAction when the Uchigatana is sold by the player.
   * @param customer The actor that wants to sell the weapon.
   * @param sellable The weapon that is being sold.
   * @return an instance of SellAction.
   * @see SellAction
   */
  public Action getSellAction(Actor customer, Sellable sellable) {
    return new SellAction(customer, sellable);
  }

  /**
   * Returns UnsheatheAction which is a unique skill of GreatKnife.
   * @param target target actor.
   * @param direction direction of attack.
   * @return an instance of UnsheatheAction.
   * @see UnsheatheAction
   */
  @Override
  public Action getSkill(Actor target, String direction) {
    return new UnsheatheAction(target, direction,this);
  }
}
