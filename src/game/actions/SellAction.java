package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Sellable;
import game.controllers.RunesManager;

public class SellAction extends Action {

  private Actor player;
  private Sellable item;

  public SellAction(Actor player, Sellable item){
    this.player = player;
    this.item = item;
  }

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
    RunesManager.getInstance().incrementAmount(item.getSellPrice());

    return menuDescription(player);
  }

  @Override
  public String menuDescription(Actor actor) {
    return actor + " sell " + item + " to Trader";
  }
}
