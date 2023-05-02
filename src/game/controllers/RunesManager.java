package game.controllers;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.RunesRewarder;
import game.items.Runes;
import game.utils.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class RunesManager {

  private Runes playerRunes;
  private Runes droppedRunes;
  private Runes droppingRunes;
  private List<RunesRewarder> runesRewarders;
  private static RunesManager instance = null;

  private RunesManager(){
    runesRewarders = new ArrayList<>();
  }

  public static RunesManager getInstance(){
    if(instance == null){
      instance = new RunesManager();
    }
    return instance;
  }

  public String rewardRunes(Actor target, Actor attacker){
    int[] targetMinMaxRewardRunes = getMinMaxRunesOf(target);
    int rewardRunesAmount = generateAmount(targetMinMaxRewardRunes[0], targetMinMaxRewardRunes[1]);
    incrementPlayerRunes(rewardRunesAmount);
    return target + " has dropped " + rewardRunesAmount + " runes and is rewarded to " + attacker;
  }

  public int generateAmount(int minReward, int maxReward){
    return RandomNumberGenerator.getRandomInt(minReward, maxReward);
  }

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

  public void incrementPlayerRunes(int amount){
    playerRunes.setTotalAmount(playerRunes.getTotalAmount() + amount);
  }

  public void decrementPlayerRunes(int amount){
    playerRunes.setTotalAmount(playerRunes.getTotalAmount() - amount);
  }

  public Runes getPlayerRunes() {
    return playerRunes;
  }

  public void registerPlayerRunes(Runes runes) {
    this.playerRunes = runes;
  }

  public void registerRunesRewarder(RunesRewarder actor){
    runesRewarders.add(actor);
  }

  public Runes getDroppedRunes() {
    return droppedRunes;
  }

  public void registerDroppedRunes(Runes runes) {
    this.droppedRunes = runes;
  }

  public void removeDroppedRunes() {
    this.droppedRunes = null;
  }

  public void clearPlayerRunes() {
    playerRunes.setTotalAmount(0);
  }

  public void registerDroppingRunes(Runes runes) {
    this.droppingRunes = runes;
  }

  public Runes getDroppingRunes() {
    return droppingRunes;
  }
}
