package game.controllers;

import edu.monash.fit2099.engine.actors.Actor;
import game.RewardRunes;
import game.items.Runes;
import game.utils.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class RunesManager {

  private Runes runes;
  private List<RewardRunes> rewardRunesActor = new ArrayList<>();
  private static RunesManager runesInstance = null;

  private RunesManager(){
  }

  public static RunesManager getInstance(){
    if(runesInstance == null){
      runesInstance = new RunesManager();
    }
    return runesInstance;
  }

  public String awardRunes(Actor target, Actor attacker){
    int amount = 0;
    int i = 0;
    int totalEnemies = rewardRunesActor.size();
    boolean found = false;
    while (!found && i < totalEnemies) {
      if (rewardRunesActor.get(i).toString().equals(target.toString())) {
        int minAward = rewardRunesActor.get(i).getMinRunes();
        int maxAward = rewardRunesActor.get(i).getMaxRunes();
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

  public void registerRewardRunesActor(RewardRunes actor){
    rewardRunesActor.add(actor);
  }

}
