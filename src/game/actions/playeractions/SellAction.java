package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.Sellable;
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
   * Actor to sell something.
   */
  private Actor player;
  /**
   * Item to be sold.
   */
  private Sellable item;

  /**
   * Constructor that initialises the customer (player) to sell the sellable item.
   * @param player the player who wants to sell.
   * @param item the item that can be sold.
   */
  public SellAction(Actor player, Sellable item){
    this.player = player;
    this.item = item;
  }

  /**
   * When executed, this sellable item will be removed from player's inventory and corresponding runes amount will be added to player's runes amount.
   * @param actor The actor performing the sell action.
   * @param map The game map the actor is on.
   * @return the result of actor selling the item.
   * @see RunesManager#getInstance()
   * @see RunesManager#incrementPlayerRunes(int)
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    // remove the item from player's inventory
    int i = 0;
    int totalWeapon = player.getWeaponInventory().size();
    boolean found = false;
    while (!found && i < totalWeapon) {
      WeaponItem weaponItem = player.getWeaponInventory().get(i);
      if (weaponItem.toString().equals(item.toString())) {
        player.removeWeaponFromInventory(weaponItem);
        found = true;
      }
      i++;
    }
    RunesManager.getInstance().incrementPlayerRunes(item.getSellPrice());

    return menuDescription(player);
  }

  /**
   * Describes which actor sells which item.
   * @param actor The actor performing the action.
   * @return a description used for the menu UI.
   */
  @Override
  public String menuDescription(Actor actor) {
    return actor + " sells " + item + " to Trader";
  }
}
