package game.actors.nonplayercharacters.traders;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.items.remembrances.RemembranceOfTheGrafted;
import game.weapons.portableweapons.*;

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
   * Constructor for MechantKale.
   * @see Status#PROVIDE_SELL_SERVICE
   * @see Status#PROVIDE_PURCHASE_SERVICE
   */
  public MerchantKale() {
    super("Merchant Kale", 'K', HIT_POINT);
    this.addCapability(Status.PROVIDE_PURCHASE_SERVICE);
    this.addCapability(Status.PROVIDE_SELL_SERVICE);
    this.addNewPurchasable(new GreatKnife());
    this.addNewPurchasable(new Uchigatana());
    this.addNewPurchasable(new Club());
    this.addNewPurchasable(new Scimitar());
    this.addNewPurchasable(new HeavyCrossbow());
    this.addNewSellable(new Grossmesser());
    this.addNewSellable(new GreatKnife());
    this.addNewSellable(new Uchigatana());
    this.addNewSellable(new Club());
    this.addNewSellable(new Scimitar());
    this.addNewSellable(new HeavyCrossbow());
    this.addNewSellable(new AxeOfGodrick());
    this.addNewSellable(new GraftedDragon());
    this.addNewSellable(new RemembranceOfTheGrafted());
  }

  /**
   * This method returns a list of actions that otherActor can perform on MerchantKale.
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return           a list of actions
   * @see ActionList
   * @see Status#HOSTILE_TO_ENEMY
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();

    if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      actions.add(this.provideActorPurchaseService(otherActor));
    }

    return actions;
  }

}
