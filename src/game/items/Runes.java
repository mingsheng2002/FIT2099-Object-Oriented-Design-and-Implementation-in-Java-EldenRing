package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Location;
import game.controllers.RunesManager;
import game.enums.Status;
import game.resets.Resettable;
import game.actions.playeractions.PickUpRunesAction;
import game.resets.ResetManager;

/**
 * A class representing money currency used in the Elden Ring game.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Item
 * @see Resettable
 *
 */
public class Runes extends Item implements Resettable {
  /**
   * Total Amount of the runes
   */
  private int totalAmount = 0;
  /**
   * Drop location of the runes
   */
  private Location dropLocation = null;
  /**
   * A flag that indicate whether the runes has dropped or not
   */
  private boolean hasDropped;

  /***
   * Constructor for Runes that initialised the instance variables for Runes
   * and register Runes as resettable through ResetManager.
   * @see ResetManager#getInstance()
   * @see ResetManager#registerResettable(Resettable)
   */
  public Runes() {
    super("Runes", '$', false);
    ResetManager.getInstance().registerResettable(this);
    this.hasDropped = false;
  }

  /**
   * Returns the total amount of the runes.
   * @return int that representing total amount of the runes.
   */
  public int getTotalAmount(){
    return this.totalAmount;
  }

  /**
   * A setter to set the totalAmount of Runes to the given amount.
   * @param amount int representing the amount to set.
   */
  public void setTotalAmount(int amount){
    totalAmount = amount;
  }

  /**
   * Returns the drop location of the Runes.
   * @return the location where the Runes are dropped.
   */
  public Location getDropLocation() {
    return dropLocation;
  }

  /**
   * A setter to set the drop location of Runes.
   * @param dropLocation the location where the runes are dropped.
   */
  public void setDropLocation(Location dropLocation) {
    this.dropLocation = dropLocation;
  }

  /**
   *  A method that reset the Runes when the game is reset.
   *  If the player is not resting (reset is caused by player death) and there are dropped runes on the ground
   *  the droppedRunes will be removed from their current location in the game map.
   *  Then the dropping runes will be register as droppedRunes.
   * @see RunesManager#getInstance()
   * @see RunesManager#getDroppedRunes()
   * @see RunesManager#getPlayerRunes()
   * @see RunesManager#getDroppingRunes()
   * @see RunesManager#removeDroppedRunes()
   * @see RunesManager#removeDroppedRunes()
   * @see Location#removeItem(Item)
   * @see RunesManager#registerDroppedRunes(Runes)
   * @see Status#RESTING
   */
  public void reset() {
    Runes dropppedRunes = RunesManager.getInstance().getDroppedRunes();
    Runes playerRunes = RunesManager.getInstance().getPlayerRunes();
    Runes droppingRunes = RunesManager.getInstance().getDroppingRunes();

    // player is not resting, but dying AND there is runes dropped on ground - drop the runes
    if (this == playerRunes && hasDropped && !playerRunes.hasCapability(Status.RESTING) && dropppedRunes != null) {
      dropppedRunes.getDropLocation().removeItem(dropppedRunes);
      RunesManager.getInstance().removeDroppedRunes();
      RunesManager.getInstance().registerDroppedRunes(droppingRunes);
    }
  }

  /**
   * This method sets the player's runes hasDropped instance variable to false when the player picks up the runes.
   * @see RunesManager#getInstance()
   * @see RunesManager#getPlayerRunes()
   * @see RunesManager#removeDroppedRunes()
   */
  public void pickedUp() {
    RunesManager.getInstance().getPlayerRunes().hasDropped = false;
    RunesManager.getInstance().removeDroppedRunes();
  }

  /**
   * This method sets the player's runes hasDropped instance variable to true when the player drops the runes.
   * @see RunesManager#getInstance()
   * @see RunesManager#getPlayerRunes()
   */
  public void dropped() {
    RunesManager.getInstance().getPlayerRunes().hasDropped = true;
  }

  /**
   * This method adds(drops) the dropped runes to the  location.
   * @param location location to add the dropped runes.
   * @see Location#addItem(Item)
   */
  public void dropAtLastLocation(Location location){
    location.addItem(this);
    dropped();
  }

  /**
   * Returns a PickUpRunesAction that allows the player to pick up the runes that have been dropped.
   * @param actor The actor that pick up the runes
   * @return an instance of PickUpRunesAction
   * @see PickUpRunesAction
   */
  public PickUpAction getPickUpAction(Actor actor) {
    return new PickUpRunesAction(actor);
  }

  /**
   * Returns string representation of Runes with its total amount.
   * @return string representation of Runes
   */
  @Override
  public String toString() {
    return "Runes (value: " + this.getTotalAmount() + ")";
  }
}
