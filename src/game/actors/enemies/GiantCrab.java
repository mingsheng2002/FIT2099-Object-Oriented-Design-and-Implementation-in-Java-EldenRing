package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class GiantCrab extends Enemy{

  /**
   * Constructor.
   *
   */
  public GiantCrab(){
    super("Giant Crab", 'C', 407);
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return null;
  }

  public IntrinsicWeapon intrinsicWeapon(){
    return new IntrinsicWeapon(208, "slams", 90);
  }
}
