package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class PileOfBones extends HarmlessEnemy{

  private int count = 0;
  public static final int MAX_HIT = 3;

  /**
   * Constructor.
   *
   */
  public PileOfBones(Enemy deathEnemy) {
    super("Pile of Bones", 'X', 1, deathEnemy);
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    count += 1;
    if(count == MAX_HIT){
      this.reviveEnemy(map);
    }
    return new DoNothingAction();
  }



  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    return super.allowableActions(otherActor, direction, map);
  }
}