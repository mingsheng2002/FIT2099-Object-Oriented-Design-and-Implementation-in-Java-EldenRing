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

/**
 * A weapon that is carried by Bandit.
 * It deals 75 damage with 70% attack accuracy.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Dagger
 * @see Purchasable
 * @see Sellable
 */
public class GreatKnife extends Dagger implements Purchasable, Sellable {

  /**
   * Damage caused by GreatKnife
   */
  private static final int DAMAGE = 75;
  /**
   * Attack accuracy of GreatKnife
   */
  private static final int HIT_RATE = 70;
  /**
   * Purchase price of GreatKnife
   */
  private static final int PURCHASE_PRICE = 3500;
  /**
   * Sell price of GreatKnife
   */
  private static final int SELL_PRICE = 350;

  /**
   * Constructor for GreatKnife.
   * @see Status#SPECIAL_SKILL
   */
  public GreatKnife() {
    super("Great Knife", '/', DAMAGE, "hits", HIT_RATE);
    this.addCapability(Status.SPECIAL_SKILL);
  }

  /**
   * Returns the purchase price of GreatKnife.
   * @return int that representing the purchase price of GreatKnife.
   */
  @Override
  public int getPurchasePrice() {
    return PURCHASE_PRICE;
  }

  /**
   * Returns an instance of PurchaseAction when GreatKnife is purchased by the player.
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
   * Returns the sell price of GreatKnife.
   * @return int that representing the sell price of GreatKnife.
   */
  @Override
  public int getSellPrice() {
    return SELL_PRICE;
  }

  /**
   * Returns an instance of SellAction when the GreatKnife is sold by the player.
   * @param customer The actor that wants to sell the weapon.
   * @param sellable The weapon that is being sold.
   * @return an instance of SellAction.
   * @see SellAction
   */
  @Override
  public Action getSellAction(Actor customer, Sellable sellable) {
    return new SellAction(customer, sellable);
  }


  /**
   *  Returns QuickStepAction which is a unique skill of GreatKnife.
   * @param target target actor.
   * @param direction direction of attack.
   * @return an instance of QuickStepAction.
   * @see QuickStepAction
   */
  @Override
  public Action getSkill(Actor target, String direction) {
    return new QuickStepAction(target, direction,this);
  }

  /**
   * Returns current instance of GreatKnife.
   * @return an instance of GreatKnife.
   */
  @Override
  public WeaponItem getPurchasableInstance() {
    return this;
  }
}
