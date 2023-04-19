package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class PileOfBones extends HarmlessEnemy{

  private Enemy enemyToBeRevived;
  private int count = 0;
  public static final int MAX_HIT = 3;

  /**
   * Constructor.
   *
   */
  public PileOfBones(Enemy deathEnemy) {
    super("Pile of Bones", 'X', 0);
    this.enemyToBeRevived = deathEnemy;
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    count += 1;
    if(count == MAX_HIT){
      this.reviveEnemy(map);
    }
    return null;
  }

  public void reviveEnemy(GameMap map){
    enemyToBeRevived.addWeaponToInventory(this.getWeaponInventory().get(0));

    // enemyToBeRevived.heal(enemyToBeRevived.getMaxHp());

    map.locationOf(this).addActor(enemyToBeRevived);
    map.removeActor(this);
  }
}
