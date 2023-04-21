package game.controllers;

import game.actors.Player;
import game.actors.enemies.Enemy;
import game.items.Runes;
import game.utils.RandomNumberGenerator;

public class RunesManager {
  private static RunesManager runesInstance = null;

  private RunesManager(){

  }

  public static RunesManager getInstance(){
    if(runesInstance == null){
      runesInstance = new RunesManager();
    }
    return runesInstance;
  }

  public String awardRunes(Enemy target, Player attacker){
    int amount = RandomNumberGenerator.getRandomInt(target.getMinRune(), target.getMaxRune());
    incrementAmount(amount, attacker.getRunes());
    return attacker + " is rewarded " + amount + " runes.";
  };


  public void incrementAmount(int amount, Runes runes){
    runes.setTotalAmount(runes.getTotalAmount() + amount);
  }

  public void decrementAmount(int amount, Runes runes){
    runes.setTotalAmount(runes.getTotalAmount() - amount);
  }




}
