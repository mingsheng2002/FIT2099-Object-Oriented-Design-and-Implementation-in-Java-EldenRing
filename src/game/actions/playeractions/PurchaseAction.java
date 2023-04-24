package game.actions.playeractions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.Purchasable;
import game.controllers.RunesManager;

public class PurchaseAction extends Action {

  private Actor player;
  private Purchasable item;

  public PurchaseAction(Actor player, Purchasable item){
    this.player = player;
    this.item = item;
  }

  @Override
  public String execute(Actor actor, GameMap map) {

    int playerRunes = RunesManager.getInstance().getRunes().getTotalAmount();
    int itemPrice = item.getPurchasePrice();

    // check if player has enough runes to purchase
    if(playerRunes < itemPrice){
      return player + " has not enough runes to purchase " + item + ".";
    }

    // add the purchase item into player's inventory
    // deduct player's runes
    else{
      player.addWeaponToInventory(item.getInstance());
      RunesManager.getInstance().decrementAmount(itemPrice);
      return menuDescription(player);
    }
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " purchases " + item + " from Trader.";
  }
}
