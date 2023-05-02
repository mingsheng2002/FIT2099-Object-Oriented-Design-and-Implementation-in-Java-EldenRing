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

/**
 * A weapon that is carried by the Skeletal Bandit.
 * It deals 118 damage with 88% attack accuracy.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see CurvedSword
 * @see Purchasable
 * @see Sellable
 */

public class Scimitar extends CurvedSword implements Purchasable, Sellable {

  /**
   * Damage caused by Scimitar
   */
  private static final int DAMAGE = 118;
  /**
   * Attack accuracy of Scimitar
   */
  private static final int HIT_RATE = 88;
  /**
   * Purchase price of Scimitar
   */
  private static final int PURCHASE_PRICE = 600;
  /**
   * Sell price of Scimitar
   */
  private static final int SELL_PRICE = 100;

  /**
   * Constructor for Scimitar.
   * @see Status#AREA_ATTACK
   */
  public Scimitar() {
    super("Scimitar", 's', DAMAGE, "hits", HIT_RATE);
    this.addCapability(Status.AREA_ATTACK);
  }

  /**
   * Returns the purchase price of Scimitar
   * @return int that representing the purchase price of Scimitar.
   */
  @Override
  public int getPurchasePrice() {
    return PURCHASE_PRICE;
  }


  /**
   * Returns an instance of PurchaseAction when Scimitar is purchased by the player.
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
   * Returns current instance of Scimitar.
   * @return an instance of Scimitar.
   */
  @Override
  public WeaponItem getPurchasableInstance() {
    return this;
  }


  /**
   * Returns the sell price of Scimitar.
   * @return int that representing the sell price of Scimitar.
   */
  @Override
  public int getSellPrice() {
    return SELL_PRICE;
  }

  /**
   * Returns an instance of SellAction when the Scimitar is sold by the player.
   * @param customer The actor that wants to sell the weapon.
   * @param sellable The weapon that is being sold.
   * @return an instance of SellAction.
   * @see SellAction
   */
  @Override
  public Action getSellAction(Actor customer, Sellable sellable) {
    return new SellAction(customer, sellable);
  }

}
