package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.Purchasable;
import game.weapons.Sellable;
import game.enums.Status;
import game.weapons.portableweapons.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Merchant Kale which is a type of Trader.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Trader
 */
public class MerchantKale extends Trader{

  /**
   * Starting hit point of MerchantKale
   */
  private static final int HIT_POINT = 100;
  /**
   * A list of purchasable items
   */
  private List<Purchasable> purchasables = new ArrayList<>();
  /**
   * A list of sellable items
   */
  private List<Sellable> sellables = new ArrayList<>();

  /**
   * Constructor for MechantKale.
   */
  public MerchantKale() {
    super("Merchant Kale", 'K', HIT_POINT);
    this.addNewPurchasable(new GreatKnife());
    this.addNewPurchasable(new Uchigatana());
    this.addNewPurchasable(new Club());
    this.addNewPurchasable(new Scimitar());
    this.addNewSellable(new Grossmesser());
    this.addNewSellable(new GreatKnife());
    this.addNewSellable(new Uchigatana());
    this.addNewSellable(new Club());
    this.addNewSellable(new Scimitar());
  }

  /**
   * This method return DoNothingAction that MerchantKale can perform at each turn.
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }

  /**
   * This method returns a list of actions that otherActor can perform on MerchantKale.
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return a list of actions
   * @see ActionList
   * @see Status#HOSTILE_TO_ENEMY
   * @see Purchasable#getPurchaseAction(Actor, Purchasable)
   * @see Sellable#getSellAction(Actor, Sellable)
   * @see WeaponItem
   * @see Action
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();

    if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      // Check what can be purchased by player (what trader can sell)
      for (Purchasable purchaseItem : purchasables) {
        actions.add(purchaseItem.getPurchaseAction(otherActor, purchaseItem));
      }

      // Check what can be sold by player
      for (WeaponItem item : otherActor.getWeaponInventory()) {
        int i = 0;
        int totalSellables = sellables.size();
        boolean found = false;
        while (!found && i < totalSellables) {
          if (sellables.get(i).toString().equals(item.toString())) {
            actions.add(sellables.get(i).getSellAction(otherActor, sellables.get(i)));
            found = true;
          }
          i++;
        }
      }
    }
    return actions;
  }

  /**
   * This method add purchasable item into purchasables list.
   * @param purchasable the purchasable item to add
   * @see Purchasable
   */
  @Override
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
}
