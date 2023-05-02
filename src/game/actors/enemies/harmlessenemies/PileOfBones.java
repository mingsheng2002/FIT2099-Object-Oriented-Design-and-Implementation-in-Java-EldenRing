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

/**
 * A Harmless Enemy called Pile Of Bones, who will not attack any other actors in the game, and is formed due to the
 * death of Heavy Skeletal Swordsman and Skeletal Bandit.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see HarmlessEnemy
 * @see Resettable
 * @see RunesRewarder
 */
public class PileOfBones extends HarmlessEnemy implements Resettable, RunesRewarder {

  /**
   * Number of count the PileOfBones is hit.
   */
  private int count = 0;
  /**
   * Maximum number of hit that a PileOfBones can receive before reviving into another enemy.
   */
  private static final int MAX_HIT = 3;
  /**
   * Minimum amount of Runes that PileOfBones hold on behalf of the skeleton enemy that dies.
   */
  private int minRunes;
  /**
   * Maximum amount of Runes that PileOfBones hold on behalf of the skeleton enemy that dies.
   */
  private int maxRunes;

  /**
   * Constructor for Pile Of Bones.
   * @see ResetManager#getInstance()
   * @see ResetManager#registerResettable(Resettable)
   * @see RunesManager#getInstance()
   * @see RunesManager#registerRunesRewarder(RunesRewarder)
   * @see Status#FRIENDLY_TO_SKELETON
   */
  public PileOfBones(Actor actorToBeRevived, int minRewardRunes, int maxRewardRunes) {
    super("Pile of Bones", 'X', 1, actorToBeRevived);
    this.minRunes = minRewardRunes;
    this.maxRunes = maxRewardRunes;
    this.addCapability(Status.FRIENDLY_TO_SKELETON);
    ResetManager.getInstance().registerResettable(this);
    // if dies, then reward player with runes
    RunesManager.getInstance().registerRunesRewarder(this);
  }

  /**
   * If three turns of the game has passed and this Pile Of Bones is still not yet defeated by other actors, then it will
   * revive the previously dead Enemy on its current location, which is either Heavy Skeletal Swordsman ot Skeletal Bandit.
   *
   * However, if this Pile Of Bones is hit by other actors within three turns, then it will execute the normal Death Action.
   *
   * @param actions    collection of possible Actions for Pile Of Bones
   * @param lastAction The Action Pile Of Bones took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Pile Of Bones
   * @param display    the I/O object to which messages may be written
   * @return the Action to be performed, either DoNothingAction (if not getting hit in 3 rounds) or DeathAction (once getting hit).
   * @see game.actions.DeathAction
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    count += 1;
    if(count == MAX_HIT){
      super.reviveActor(map);
    }
    return new DoNothingAction();
  }

  /**
   * When the game is reset, this method will be executed and Pile Of Bones will be removed from the game map.
   */
  @Override
  public void reset() {
    if (this.getMap() != null){
      getMap().removeActor(this);
    }
  }

  /**
   * Getter to get the minimum droppable runes of the Enemy.
   * @return amount of minimum droppable runes of the Enemy.
   */
  @Override
  public int getMinRewardRunes() {
    return minRunes;
  }

  /**
   * Getter to get the maximum droppable runes of the Enemy.
   * @return amount of maximum droppable runes of the Enemy.
   */
  @Override
  public int getMaxRewardRunes() {
    return maxRunes;
  }
}
