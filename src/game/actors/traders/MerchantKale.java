package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Purchasable;
import game.Sellable;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.enums.Status;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Grossmesser;
import game.weapons.Uchigatana;
import java.util.ArrayList;
import java.util.List;

public class MerchantKale extends Trader{

  // What's the hit point for MerchantKale?
  private static final int HIT_POINT = 100;
  private List<Purchasable> purchasables = new ArrayList<>();
  private List<Sellable> sellables = new ArrayList<>();

  /**
   * Constructor.
   *
   */
  public MerchantKale() {
    super("Merchant Kale", 'K', HIT_POINT);
    this.addNewPurchasable(new GreatKnife());
    this.addNewPurchasable(new Uchigatana());
    this.addNewPurchasable(new Club());
    this.addNewSellable(new Grossmesser());
    this.addNewSellable(new GreatKnife());
    this.addNewSellable(new Uchigatana());
    this.addNewSellable(new Club());

  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();

    if(otherActor.hasCapability(Status.RESTING)) {
      // Check what can be purchased by player (what trader can sell)
      for (Purchasable purchaseItem : purchasables) {
        actions.add(new PurchaseAction(otherActor, purchaseItem));
      }

      // Check what can be sold by player
      for (WeaponItem item : otherActor.getWeaponInventory()) {
        int i = 0;
        int totalSellables = sellables.size();
        boolean found = false;
        while (!found && i < totalSellables) {
          if (sellables.get(i).toString().equals(item.toString())) {
            actions.add(new SellAction(otherActor, sellables.get(i)));
            found = true;
          }
          i++;
        }
      }
    }
    return actions;
  }

  @Override
  public void addNewPurchasable(Purchasable purchasable) {
    purchasables.add(purchasable);
  }

  public void addNewSellable(Sellable sellable){
    sellables.add(sellable);
  }
}
