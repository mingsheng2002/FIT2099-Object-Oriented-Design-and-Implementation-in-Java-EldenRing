package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Purchasable;
import game.actors.Player;
import game.controllers.RunesManager;
import game.utils.RandomNumberGenerator;

public class PurchaseAction extends Action {

  private Player player;
  private Purchasable item;

  public PurchaseAction(Player player, Purchasable item){
    this.player = player;
    this.item = item;
  }

  @Override
  public String execute(Actor actor, GameMap map) {

//    player.addWeaponToInventory(weapon);
//    RunesManager.getInstance().decrementAmount(100, player.getRunes());
//    return menuDescription(player);

    int playerRunes = player.getRunes().getTotalAmount();
    int itemPrice = item.getPurchasePrice();

    // check if player has enough runes to purchase
    if(playerRunes < itemPrice){
      return player + " has not enough runes to purchase " + item;
    }

    // add the purchase item into player's inventory
    // deduct player's runes
    else{
      player.addWeaponToInventory((WeaponItem) item);
      RunesManager.getInstance().decrementAmount(itemPrice, player.getRunes());
      return menuDescription(player);
    }
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " purchases " + item + " from Trader.";
  }
}
