package game.controllers;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.RunesRewarder;
import game.items.Runes;
import game.utils.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class RunesManager {

  private Runes runes;
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

    incrementAmount(amount);

    return attacker + " is rewarded " + amount + " runes.";
  };


  public void incrementAmount(int amount){
    runes.setTotalAmount(runes.getTotalAmount() + amount);
  }

  public void decrementAmount(int amount){
    runes.setTotalAmount(runes.getTotalAmount() - amount);
  }

  public Runes getRunes() {
    return runes;
  }

  public void registerRunes(Runes runes) {
    this.runes = runes;
  }

  public void registerRewardRunesActor(RunesRewarder actor){
    runesRewarderActor.add(actor);
  }

}
