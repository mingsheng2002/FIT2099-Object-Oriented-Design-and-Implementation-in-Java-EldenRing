package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class MerchantKale extends Trader{

  // What's the hit point for MerchantKale?
  private static final int HIT_POINT = 100;

  /**
   * Constructor.
   *
   */
  public MerchantKale() {
    super("Merchant Kale", 'K', HIT_POINT);
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return super.playTurn(actions, lastAction, map, display);
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    return super.allowableActions(otherActor, direction, map);
  }
}
