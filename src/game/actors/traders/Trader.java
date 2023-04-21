package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Purchasable;
import game.Resettable;
import game.Sellable;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.actors.Player;
import game.controllers.PurchaseManager;
import game.controllers.SellManager;
import game.enums.Status;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Grossmesser;
import game.weapons.Uchigatana;
import java.util.ArrayList;
import java.util.List;

public abstract class Trader extends Actor {

  /**
   * Constructor.
   *
   * @param name        the name of the Actor
   * @param displayChar the character that will represent the Actor in the display
   * @param hitPoints   the Actor's starting hit points
   */
  public Trader(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
  }

  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
    return new DoNothingAction();
  };

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();

    if(otherActor.hasCapability(Status.RESTING)){
      // Check what can be purchased by player (what trader can sell)
      for(Purchasable purchaseItem : PurchaseManager.getInstance().getPurchasables()) {
          actions.add(new PurchaseAction((Player) otherActor, purchaseItem));
      }

      // Check what can be sold by player
      for(WeaponItem item : otherActor.getWeaponInventory()){
        int i = 0;
        int totalSellables = SellManager.getInstance().getSellables().size();
        boolean found = false;
        while (!found && i < totalSellables) {
          if (SellManager.getInstance().getSellables().get(i).toString().equals(item.toString())) {
            actions.add(new SellAction((Player) otherActor, (Sellable) item));
            found = true;
          }
          i++;
        }
      }
    }


    return actions;
  }
}
