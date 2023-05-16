package game.actors.nonplayercharacters.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.enemyactions.DespawnAction;
import game.actors.nonplayercharacters.NonPlayerCharacter;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.controllers.RunesManager;
import game.enums.Status;
import game.utils.RandomNumberGenerator;

/**
 * A class representing the Enemy in the game.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see NonPlayerCharacter
 * @see RunesRewarder
 */
public abstract class Enemy extends NonPlayerCharacter implements RunesRewarder {

  /**
   * The spawn chance of Enemy from its specific ground.
   */
  private int spawnChance;
  /**
   * The chance of Enemy despawning from the game map.
   */
  private int despawnChance;
  /**
   * Health of Enemy.
   */
  private int enemyHitPoints;
  /**
   * Minimum amount of runes dropped by Enemy if defeated by player.
   */
  private int minRunes;
  /**
   * Maximum amount of runes dropped by Enemy if defeated by player.
   */
  private int maxRunes;
//  /**
//   * The Game Map that the Enemy is currently on.
//   */
//  private GameMap map;

  /**
   * Constructor for Enemy.
   * @param name the name of the Enemy.
   * @param displayChar the character that will represent the Enemy in the display
   * @param hitPoints the Enemy's starting hit points.
   * @param spawnChance the Enemy's spawning chance from its specific ground.
   * @param despawnChance the Enemy's despawning chance from the game map.
   * @param minRunes the minimum runes dropped by Enemy if defeated by player.
   * @param maxRunes the maximum runes dropped by Enemy if defeated by player.
   * @see WanderBehaviour
   * @see RunesManager#getInstance()
   * @see RunesManager#registerRunesRewarder(RunesRewarder)
   */
  public Enemy(String name, char displayChar, int hitPoints, int spawnChance, int despawnChance, int minRunes, int maxRunes) {
    super(name, displayChar, hitPoints);
    this.enemyHitPoints = hitPoints;
    this.spawnChance = spawnChance;
    this.despawnChance = despawnChance;
    this.minRunes = minRunes;
    this.maxRunes = maxRunes;
    this.getBehaviours().put(2, new WanderBehaviour());
    // if dies, then reward player with runes
    RunesManager.getInstance().registerRunesRewarder(this);
  }

  /**
   * If the game is being reset during this round, return DoNothingAction for every Enemy; else
   * Select and return an action to perform for the Enemy on the current turn. If Enemy meets their
   * specific despawn chance in any round of the game, Despawn Action will be returned.
   *
   * @param actions    collection of possible Actions for this Enemy
   * @param lastAction The Action this Enemy took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Enemy
   * @param display    the I/O object to which messages may be written
   * @return the Action to be performed
   * @see RandomNumberGenerator#getRandomInt(int)
   * @see DespawnAction
   * @see DoNothingAction
   * @see Status#FOLLOWING
   */
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
//    this.map = map;
    setMap(map);

    if (!this.hasCapability(Status.FOLLOWING) && RandomNumberGenerator.getRandomInt(100) < this.despawnChance) {
      return (new DespawnAction(this));
    }

    for (int key : getBehaviours().keySet()) {
      Behaviour behaviour = getBehaviours().get(key);
      Action action = behaviour.getAction(this, map);
      if (action != null) {
        // If it is an Attack Action
        if (key == 0) {
          getBehaviours().remove(0);
        }
        return action;
      }
    }
    return new DoNothingAction();
  }

  /**
   * Returns a new collection of the Actions that the otherActor can do to the current Enemy.
   * Also, Attack Behaviour will be put into the behaviours HashMap of this enemy, if it can attack the otherActor,
   * e.g. the otherActor is not of the same type of this enemy and otherActor is not having capability of PROTECTED.
   *
   * @param otherActor the Actor that might be performing attack on this Enemy.
   * @param direction  String representing the direction of the other Actor.
   * @param map        current GameMap
   * @return A collection of Actions.
   * @see ActionList
   * @see FollowBehaviour
   * @see AttackAction
   * @see Status#HOSTILE_TO_ENEMY
   * @see Status#FOLLOWING
   * @see Status#SPECIAL_SKILL
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();

    // If adjacent actor is Player, gives Enemy a Follow Behaviour & gives Player an Attack Action
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
      getBehaviours().put(1, new FollowBehaviour(otherActor));
      this.addCapability(Status.FOLLOWING);

      actions.add(new AttackAction(this, direction));
      if (!otherActor.getWeaponInventory().isEmpty()) {
        for (WeaponItem weapon : otherActor.getWeaponInventory()) {
          actions.add(new AttackAction(this, direction, weapon));
          if(weapon.hasCapability(Status.SPECIAL_SKILL)){
            actions.add(weapon.getSkill(this,direction));
          }
        }
      }
    }
    return actions;
  }

  /**
   * Increases maximum hit points and set current hit points to that new maximum hit points. Remove the Follow Behaviour
   * from the Enemy before increasing its health.
   *
   * @param points number of points that is added to maximum health
   */
  @Override
  public void increaseMaxHp(int points) {
    // remove Follow Behaviour before reviving
    if (this.getBehaviours().get(1) != null) {
      this.getBehaviours().remove(1);
    }
    super.increaseMaxHp(points);
  }

//  /**
//   * Getter to get the Game Map that the Enemy is currently on.
//   * @return the Game Map that the Enemy is currently on.
//   */
//  public GameMap getMap() {
//    return map;
//  }

  /**
   * Getter to get the minimum droppable runes of the Enemy.
   * @return amount of minimum droppable runes of the Enemy.
   */
  @Override
  public int getMinRewardRunes(){
    return minRunes;
  }

  /**
   * Getter to get the maximum droppable runes of the Enemy.
   * @return amount of maximum droppable runes of the Enemy.
   */
  @Override
  public int getMaxRewardRunes(){
    return maxRunes;
  }

  /**
   * Getter to get the spawning chance of the Enemy from its specific ground.
   * @return the spawning chance of the Enemy.
   */
  public int getSpawnChance() {
    return spawnChance;
  }
}
