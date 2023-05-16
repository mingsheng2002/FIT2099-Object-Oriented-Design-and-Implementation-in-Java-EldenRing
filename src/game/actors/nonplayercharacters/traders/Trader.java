package game.actors.nonplayercharacters.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.nonplayercharacters.NonPlayerCharacter;
import game.enums.Status;
import game.items.Exchangeable;
import game.items.Purchasable;
import game.items.Sellable;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract trader class
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see NonPlayerCharacter
 */
public abstract class Trader extends NonPlayerCharacter {

  /**
   * A list of purchasable items
   */
  private List<Purchasable> purchasables = new ArrayList<>();
  /**
   * A list of sellable items
   */
  private List<Sellable> sellables = new ArrayList<>();
  /**
   * A list of exchangeable items
   */
  private List<Exchangeable> exchangeables = new ArrayList<>();


  /**
   * Constructor for Trader.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   * @see Status#PROTECTED
   */
  public Trader(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    this.addCapability(Status.PROTECTED); // cannot be attacked
  }

  /**
   * This method return DoNothingAction that Trader can perform at each turn.
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return an action that does nothing
   * @see DoNothingAction
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

    Actor player = getPlayerInSurrounding(map.locationOf(this));

    // If player is in the surrounding
    if (player != null) {
      // If player's items/weapons can be sold to this trader, then the items/weapons are ready to be sold
      for (Sellable sellable : this.sellables) {
        // loop through player's item inventory
        for (Item item : player.getItemInventory()) {
          if (sellable.toString().equals(item.toString())) {
            item.addCapability(Status.READY_TO_BE_SOLD);
          }
        }
        // loop through player's weapon inventory
        for (WeaponItem weapon : player.getWeaponInventory()) {
          if (sellable.toString().equals(weapon.toString())) {
            weapon.addCapability(Status.READY_TO_BE_SOLD);
          }
        }
      }

      // If player's items/weapons can be exchanged with this trader, then the items/weapons are ready to be exchanged
      for (Exchangeable exchangeable : this.exchangeables) {
        // loop through player's item inventory
        for (Item item : player.getItemInventory()) {
          if (exchangeable.toString().equals(item.toString())) {
            item.addCapability(Status.READY_TO_BE_EXCHANGED);
          }
        }
        // loop through player's weapon inventory
        for (WeaponItem weapon : player.getWeaponInventory()) {
          if (exchangeable.toString().equals(weapon.toString())) {
            weapon.addCapability(Status.READY_TO_BE_EXCHANGED);
          }
        }
      }
    }

    return new DoNothingAction();
  }

  public ActionList provideActorPurchaseService(Actor otherActor) {
    ActionList actions = new ActionList();
    if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      // Check what can be purchased by player (what trader can sell)
      for (Purchasable purchaseItem : getPurchasables()) {
        actions.add(purchaseItem.getPurchaseAction(purchaseItem));
      }
    }
    return actions;
  }

  /**
   * This method add purchasable item into purchasables list.
   * @param purchasable the purchasable item to add
   * @see Purchasable
   */
  public void addNewPurchasable(Purchasable purchasable) {
    purchasables.add(purchasable);
  }

  /**
   * This method add sellable item into sellables list.
   * @param sellable the sellable item to add
   * @see Sellable
   */
  public void addNewSellable(Sellable sellable){
    sellables.add(sellable);
  }

  /**
   * This method add exchangeable item into exchangeables list.
   * @param exchangeable the exchangeable item to add
   * @see Exchangeable
   */
  public void addNewExchangeable(Exchangeable exchangeable) {
    this.exchangeables.add(exchangeable);
  }

  /**
   * Getter that return a list of purchasable items.
   * @return a list of purchasable items
   */
  public List<Purchasable> getPurchasables() {
    return purchasables;
  }

  /**
   * Getter that return a list of sellable items.
   * @return a list of sellable items
   */
  public List<Sellable> getSellables() {
    return sellables;
  }

  /**
   *
   * @return
   */
  public List<Exchangeable> getExchangeables() {
    return exchangeables;
  }

  private Actor getPlayerInSurrounding(Location location) {
    Actor player = null;
    for (Exit exit : location.getExits()) {
      Location destination = exit.getDestination();
      if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
        player = destination.getActor();
        break;
      }
    }
    return player;
  }
}
