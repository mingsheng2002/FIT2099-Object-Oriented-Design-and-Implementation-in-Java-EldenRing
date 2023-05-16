package game.controllers;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.nonplayercharacters.enemies.RunesRewarder;
import game.items.Runes;
import game.utils.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that manages Runes object like transaction of runes, increment or decrement of runes amount.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public class RunesManager {

  /**
   * Runes object that the player holds.
   */
  private Runes playerRunes;
  /**
   * Runes object that is dropped on the ground after game is reset.
   */
  private Runes droppedRunes;
  /**
   * Runes object that is dropping when the game is resetting.
   */
  private Runes droppingRunes;
  /**
   * List of RunesRewarder.
   */
  private List<RunesRewarder> runesRewarders;
  /**
   * Instance of RunesManager.
   */
  private static RunesManager instance = null;

  /**
   * Private constructor for RunesManager.
   */
  private RunesManager(){
    runesRewarders = new ArrayList<>();
  }

  /**
   * Obtain and return the instance of this class
   * @return the instance of RunesManger
   */
  public static RunesManager getInstance(){
    if(instance == null){
      instance = new RunesManager();
    }
    return instance;
  }

  /**
   * Award some runes amount to playerRunes by incrementing playerRunes
   * @param target the actor that reward runes based on its range of rewarding
   * @param attacker  the actor that receive rewarded runes
   * @return String of message that describe the reward of runes to Actor target
   */
  public String rewardRunes(Actor target, Actor attacker){
    int[] targetMinMaxRewardRunes = getMinMaxRunesOf(target);
    int rewardRunesAmount = generateAmount(targetMinMaxRewardRunes[0], targetMinMaxRewardRunes[1]);
    incrementPlayerRunes(rewardRunesAmount);
    return target + " has dropped " + rewardRunesAmount + " runes and is rewarded to " + attacker;
  }

  /**
   * Compute an exact amount of runes given a lower boundary and upper boundary
   * @param minReward lower boundary
   * @param maxReward upper boundary
   * @return an int value between lower boundary and upper boundary
   * @see RandomNumberGenerator
   */
  public int generateAmount(int minReward, int maxReward){
    return RandomNumberGenerator.getRandomInt(minReward, maxReward);
  }

  /**
   * Obtain the minimum and maximum runes that Actor target can reward
   * @param target the actor that reward runes
   * @return an array of size 2 where first element is an int value of the minimum runes amount and
   * second element is an int value of the maximum runes amount
   */
  public int[] getMinMaxRunesOf(Actor target) {
    int[] retArr = new int[2];
    int minRewardRunes;
    int maxRewardRunes;
    int i = 0;
    int totalRunesRewarders = runesRewarders.size();
    boolean found = false;
    while (!found && i < totalRunesRewarders) {
      RunesRewarder runesRewarder = runesRewarders.get(i);
      if (runesRewarder == target) {
        minRewardRunes = runesRewarder.getMinRewardRunes();
        maxRewardRunes = runesRewarder.getMaxRewardRunes();
        retArr[0] = minRewardRunes;
        retArr[1] = maxRewardRunes;
        found = true;
      }
      i++;
    }
    return retArr;
  }

  /**
   * Increase the amount of playerRunes.
   * @param amount amount to be added into playerRunes.
   */
  public void incrementPlayerRunes(int amount){
    playerRunes.setTotalAmount(playerRunes.getTotalAmount() + amount);
  }

  /**
   * Decrease the amount of playerRunes.
   * @param amount amount to be deducted from playerRunes.
   */
  public void decrementPlayerRunes(int amount){
    playerRunes.setTotalAmount(playerRunes.getTotalAmount() - amount);
  }

  /**
   * Obtain playerRunes.
   * @return Runes object of playerRunes.
   */
  public Runes getPlayerRunes() {
    return playerRunes;
  }

  /**
   * Initialize instance of playerRunes with Runes runes.
   * @param runes Player's Runes object.
   */
  public void registerPlayerRunes(Runes runes) {
    this.playerRunes = runes;
  }

  /**
   * Add a RunesRewarder into the List of runesRewarders.
   * @param actor the actor that implements RunesRewarder interface.
   */
  public void registerRunesRewarder(RunesRewarder actor){
    runesRewarders.add(actor);
  }

  /**
   * Obtain droppedRunes.
   * @return Runes object of droppedRunes.
   */
  public Runes getDroppedRunes() {
    return droppedRunes;
  }

  /**
   * Initialize instance of droppedRunes with Runes runes.
   * @param runes droppedRunes Runes object.
   */
  public void registerDroppedRunes(Runes runes) {
    this.droppedRunes = runes;
  }

  /**
   * Assign null value to the droppedRunes object.
   */
  public void removeDroppedRunes() {
    this.droppedRunes = null;
  }

  /**
   * Set the amount of playerRunes object to 0.
   */
  public void clearPlayerRunes() {
    playerRunes.setTotalAmount(0);
  }

  /**
   * Initialize instance of droppingRunes with Runes runes.
   * @param runes droppingRunes Runes object.
   */
  public void registerDroppingRunes(Runes runes) {
    this.droppingRunes = runes;
  }

  /**
   * Obtaib droppingRunes.
   * @return Runes object of droppingRunes.
   */
  public Runes getDroppingRunes() {
    return droppingRunes;
  }
}
