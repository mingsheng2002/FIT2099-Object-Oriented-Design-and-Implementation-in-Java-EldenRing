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

public class Runes extends Item implements Resettable {

  private int totalAmount = 0;
  private Location dropLocation = null;
  private boolean hasDropped;

  /***
   * Constructor.
   */
  public Runes() {
    super("Runes", '$', false);
    ResetManager.getInstance().registerResettable(this);
    this.hasDropped = false;
  }

  public int getTotalAmount(){
    return this.totalAmount;
  }

  public void setTotalAmount(int amount){
    totalAmount = amount;
  }

  public Location getDropLocation() {
    return dropLocation;
  }

  public void setDropLocation(Location dropLocation) {
    this.dropLocation = dropLocation;
  }

  /**
   * If portable ==T , reset caused by player death
   *
   */

  public void reset(){
    Runes dropppedRunes = RunesManager.getInstance().getDroppedRunes();
    Runes playerRunes = RunesManager.getInstance().getPlayerRunes();

    if (!playerRunes.hasCapability(Status.TEMPORARILY_UNDROPPABLE) && dropppedRunes != null) {
      dropppedRunes.getDropLocation().removeItem(dropppedRunes);
      RunesManager.getInstance().removeDroppedRunes();
      System.out.println("Player dies again. Runes is removed from map");
    }
  }
  public void pickedUp() {
    RunesManager.getInstance().getPlayerRunes().hasDropped = false;
  }

  public void dropped() {
    RunesManager.getInstance().getPlayerRunes().hasDropped = true;
  }

  public boolean hasDropped() {
    return hasDropped;
  }

  public void dropAtLastLocation(Location location){
    location.addItem(this);
    dropped();
  }

  public PickUpAction getPickUpAction(Actor actor) {
    return new PickUpRunesAction(actor);
  }

  @Override
  public String toString() {
    return "Runes (value: " + this.getTotalAmount() + ")";
  }
}
