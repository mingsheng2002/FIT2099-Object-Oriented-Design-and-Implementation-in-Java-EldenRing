package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Purchasable;
import game.controllers.RunesManager;

/**
 * An Action for Actor to purchase certain items from the trader.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Action
 */
public class PurchaseAction extends Action {

  /**
   * Item to be purchased.
   */
  private Purchasable item;

  /**
   * Constructor for PurchaseAction.
   * @param item the item that can be purchased.
   */
  public PurchaseAction(Purchasable item){
    this.item = item;
  }

  /**
   * When executed, this purchasable item will be added into player's inventory and corresponding
   * runes amount will be deducted from player's runes amount.
   * @param actor The actor performing the purchase action.
   * @param map The game map the actor is on.
   * @return the result of actor purchasing the item.
   * @see RunesManager#getInstance()
   * @see RunesManager#getPlayerRunes()
   * @see RunesManager#decrementPlayerRunes(int)
   */
  @Override
  public String execute(Actor actor, GameMap map) {

    int playerRunes = RunesManager.getInstance().getPlayerRunes().getTotalAmount();
    int itemPrice = item.getPurchasePrice();

    // check if player has enough runes to purchase
    if (playerRunes < itemPrice){
      return actor + " has not enough runes to purchase " + item;
    }

    // add the purchase item into player's inventory
    // deduct player's runes
    else {
      item.addPurchasableToInventory(actor);
      RunesManager.getInstance().decrementPlayerRunes(itemPrice);
      return menuDescription(actor);
    }
  }

  /**
   * Describes which actor purchases which item.
   * @param actor The actor performing the action.
   * @return a description used for the menu UI.
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " purchases " + item + " from Trader for " + item.getPurchasePrice() + " runes";
  }
}
