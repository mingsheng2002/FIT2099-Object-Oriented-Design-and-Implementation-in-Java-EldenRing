package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.Location;
import game.Resettable;
import game.actions.PickUpRunesAction;
import game.actors.Player;
import game.controllers.ResetManager;

public class Runes extends Item implements Resettable {

  private int totalAmount = 0;
  private Location dropLocation = null;
  private boolean hasPickUp ;




  /***
   * Constructor.
   */
  public Runes() {
    super("Runes", '$', false);
    ResetManager.getInstance().registerResettable(this);
    this.hasPickUp = false;

  }

  public int getTotalAmount(){
    return this.totalAmount;
  }

  public void setTotalAmount(int amount){totalAmount = amount;}

  public Location getDropLocation() {
    return dropLocation;
  }

  public void setDropLocation(Location dropLocation) {
    this.dropLocation = dropLocation;
  }

  public void setHasPickUp(boolean hasPickUp) {
    this.hasPickUp = hasPickUp;
  }




  /**
   * If portable ==T , reset caused by player death
   *
   */
  public void reset(){
    if (this.portable){
      if(!hasPickUp) {
        getDropLocation().removeItem(this);
        System.out.println("player death again runes remove ");

      }
    }
  }

  @Override
  public PickUpAction getPickUpAction(Actor actor) {
    if (portable)
      return new PickUpRunesAction(this,(Player) actor);
    return null;
  }

  public void dropAtLastLocation(Location location){
    location.addItem(this);
  }

  @Override
  public void togglePortability() {
    super.togglePortability();
  }
}
