package game.actors.enemies.harmlessenemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.enemies.RunesRewarder;
import game.controllers.RunesManager;
import game.enums.Status;
import game.resets.Resettable;
import game.resets.ResetManager;

public class PileOfBones extends HarmlessEnemy implements Resettable, RunesRewarder {

  private int count = 0;
  private static final int MAX_HIT = 3;
  private int minRunes;
  private int maxRunes;

  /**
   * Constructor.
   *
   */
  public PileOfBones(Actor actorToBeRevived, int minRewardRunes, int maxRewardRunes) {
    super("Pile of Bones", 'X', 1, actorToBeRevived);
    this.minRunes = minRewardRunes;
    this.maxRunes = maxRewardRunes;
    this.addCapability(Status.FRIENDLY_TO_SKELETON);
    ResetManager.getInstance().registerResettable(this);
    // if dies, then reward player with runes
    RunesManager.getInstance().registerRewardOwner(this);
  }

  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    count += 1;
    if(count == MAX_HIT){
      super.reviveActor(map);
    }
    return new DoNothingAction();
  }

  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    return super.allowableActions(otherActor, direction, map);
  }

  @Override
  public void reset() {
    if (this.getMap() != null){
      getMap().removeActor(this);
    }
  }

  @Override
  public int getMinRewardRunes() {
    return minRunes;
  }

  @Override
  public int getMaxRewardRunes() {
    return maxRunes;
  }
}
