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

  /**
   * Removes the current Grossmesser from actor's inventory.
   * @param actor actor that the Grossmesser will be removed from
   */
  @Override
  public void removeSellableFromInventory(Actor actor) {
    actor.removeWeaponFromInventory(this);
  }

  /**
   * Performs an action on each tick of the game loop.
   * Checks if the Grossmesser is ready to be sold and if there is an actor nearby with the capability to provide a sell service.
   * Adds a sell action to the Grossmesser if the conditions are met and removes the "READY_TO_BE_SOLD" capability.
   * @param currentLocation the location of the actor carrying this item
   * @param actor           the actor carrying this item
   * @see Status#READY_TO_BE_SOLD
   * @see Status#PROVIDE_SELL_SERVICE
   * @see SurroundingChecker#surroundingHasActorWithCapability(Location, Status)
   */
  @Override
  public void tick(Location currentLocation, Actor actor) {
    this.removeAction(sellAction);
    if (this.hasCapability(Status.READY_TO_BE_SOLD) && SurroundingChecker.surroundingHasActorWithCapability(currentLocation, Status.PROVIDE_SELL_SERVICE)) {
      this.addAction(sellAction);
      this.removeCapability(Status.READY_TO_BE_SOLD);
    }
  }

  /**
   * Removes SellAction from weapon allowableActions.Create and return an action to drop this WeaponItem.
   * If this WeaponItem is not portable, returns null.
   * @param actor actor that performs drop action
   * @return a new DropWeaponAction if this WeaponItem is portable, null otherwise.
   */
  @Override
  public DropAction getDropAction(Actor actor) {
    this.removeAction(sellAction);
    return super.getDropAction(actor);
  }
}
