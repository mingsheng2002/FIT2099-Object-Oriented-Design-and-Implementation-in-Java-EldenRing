package game.weapons.portableweapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.playeractions.SellAction;
import game.enums.Status;
import game.utils.SurroundingChecker;
import game.items.Sellable;
import game.weapons.weapontypes.CurvedSword;

/**
 * A weapon that is carried by the Heavy Skeletal Swordsman.
 * It deals 115 damage with 85% attack accuracy.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see CurvedSword
 * @see Sellable
 */
public class Grossmesser extends CurvedSword implements Sellable {

  /**
   * Damage caused by Grossmesser
   */
  private static final int DAMAGE = 115;
  /**
   * Attack accuracy of Grossmesser
   */
  private static final int HIT_RATE = 85;
  /**
   * Sell price of Grossmesser
   */
  private static final int SELL_PRICE = 100;
  /**
   * Sell action provided by Grossmesser
   */
  private SellAction sellAction;

  /**
   * Constructor of Grossmesser.
   * @see Status#AREA_ATTACK
   */
  public Grossmesser() {
    super("Grossmesser", '?', DAMAGE, "hits", HIT_RATE);
    this.sellAction = new SellAction(this);
    this.addCapability(Status.AREA_ATTACK);
  }

  /**
   * Returns the sell price of Grossmesser.
   * @return int that representing the sell price of Grossmesser.
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

  @Override
  public DropAction getDropAction(Actor actor) {
    this.removeAction(sellAction);
    return super.getDropAction(actor);
  }
}
