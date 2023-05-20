package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Sellable;
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
public class SellAction extends Action {

  /**
   * Item to be sold.
   */
  private Sellable item;

  /**
   * Constructor for SellAction.
   * @param item the item that can be sold.
   */
  public SellAction(Sellable item){
    this.item = item;
  }

  /**
   * When executed, this sellable item will be removed from player's inventory and
   * corresponding runes amount will be added to player's runes amount.
   * @param actor The actor performing the sell action.
   * @param map The game map the actor is on.
   * @return the result of actor selling the item.
   * @see RunesManager#getInstance()
   * @see RunesManager#incrementPlayerRunes(int)
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    // remove the item from player's inventory
    item.removeSellableFromInventory(actor);

    RunesManager.getInstance().incrementPlayerRunes(this.item.getSellPrice());

    return menuDescription(actor);
  }

  /**
   * Describes which actor sells which item.
   * @param actor The actor performing the action.
   * @return a description used for the menu UI.
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " sells " + item + " to Trader for " + item.getSellPrice() + " runes";
  }
}
