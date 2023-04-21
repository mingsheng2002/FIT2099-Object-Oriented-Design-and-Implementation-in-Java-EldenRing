package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Sellable;
import game.actors.Player;
import game.controllers.RunesManager;

public class SellAction extends Action {

  private Player player;
  private Sellable item;

  public SellAction(Player player, Sellable item){
    this.player = player;
    this.item = item;
  }

  @Override
  public String execute(Actor actor, GameMap map) {
    // remove the item from player's inventory
    player.removeWeaponFromInventory((WeaponItem) item);
    RunesManager.getInstance().incrementAmount(item.getSellPrice(), player.getRunes());
    return menuDescription(player);
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " sell " + item + " to Trader.";
  }
}
