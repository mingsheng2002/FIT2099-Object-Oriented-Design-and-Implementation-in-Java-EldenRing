package game.controllers;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.RunesRewarder;
import game.items.Runes;
import game.utils.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class RunesManager {

  private Runes playerRunes;
  private List<RunesRewarder> runesRewarderActor;
  private static RunesManager runesInstance = null;

  private RunesManager(){
    runesRewarderActor = new ArrayList<>();
  }

  public static RunesManager getInstance(){
    if(runesInstance == null){
      runesInstance = new RunesManager();
    }
    return runesInstance;
  }

  public String rewardRunes(Actor target, Actor attacker){
    int amount = 0;
    int i = 0;
    int totalEnemies = runesRewarderActor.size();
    boolean found = false;
    while (!found && i < totalEnemies) {
      RunesRewarder rewarder = runesRewarderActor.get(i);
      if (rewarder == target) {
        int minAward = rewarder.getMinRunes();
        int maxAward = rewarder.getMaxRunes();
        amount = RandomNumberGenerator.getRandomInt(minAward, maxAward);
        found = true;
      }
      i++;
    }

    incrementPlayerRunes(amount);

    return attacker + " is rewarded " + amount + " runes.";
  };


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

  public void registerRewardRunesActor(RunesRewarder actor){
    runesRewarderActor.add(actor);
  }

}
