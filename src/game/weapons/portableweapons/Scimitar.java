package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.PurchaseAction;
import game.actions.playeractions.SellAction;
import game.enums.Status;
import game.utils.SurroundingChecker;
import game.items.Purchasable;
import game.items.Sellable;
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
   * Sell action provided by Scimitar
   */
  private SellAction sellAction;

  /**
   * Constructor for Scimitar.
   * @see Status#AREA_ATTACK
   */
  public Scimitar() {
    super("Scimitar", 's', DAMAGE, "hits", HIT_RATE);
    this.sellAction = new SellAction(this);
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
   * @param purchasable The weapon that is being purchased.
   * @return an instance of PurchaseAction.
   * @see PurchaseAction
   */
  @Override
  public Action getPurchaseAction(Purchasable purchasable) {
    return new PurchaseAction(purchasable);
  }

  @Override
  public void addPurchasableToInventory(Actor actor) {
    actor.addWeaponToInventory(this);
  }

  /**
   * Returns the sell price of Scimitar.
   * @return int that representing the sell price of Scimitar.
   */
  @Override
  public int getSellPrice() {
    return SELL_PRICE;
  }

  @Override
  public void removeSellableFromInventory(Actor actor) {
    actor.removeWeaponFromInventory(this);
  }

  /**
   *
   * @param currentLocation The location of the actor carrying this Item.
   * @param actor The actor carrying this Item.
   */
  @Override
  public void tick(Location currentLocation, Actor actor) {
    this.removeAction(sellAction);
    if (this.hasCapability(Status.READY_TO_BE_SOLD) && SurroundingChecker.surroundingHasActorWithCapability(currentLocation, Status.PROVIDE_SELL_SERVICE)) {
      this.addAction(sellAction);
      this.removeCapability(Status.READY_TO_BE_SOLD);
    }
  }
}
