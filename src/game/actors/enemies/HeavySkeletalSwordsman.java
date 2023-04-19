package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.DeathAction;
import game.Status;
import game.weapons.Grossmesser;

public class HeavySkeletalSwordsman extends Enemy{

  /**
   * Constructor.
   *
   */
  public HeavySkeletalSwordsman(){
    super("Heavy Skeletal Swordsman", 'q', 153);
    this.addWeaponToInventory(new Grossmesser());
    this.addCapability(Status.RESPAWNABLE);
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return null;
  }
}
