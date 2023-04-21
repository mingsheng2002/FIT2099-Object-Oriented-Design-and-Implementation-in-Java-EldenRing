package game.controllers;

import game.Purchasable;
import game.Sellable;
import java.util.ArrayList;
import java.util.List;

public class SellManager {
  private List<Sellable> sellables = new ArrayList<>();
  private static SellManager sellManagerInstance = null;

  private SellManager(){
  }

  public static SellManager getInstance(){
    if(sellManagerInstance == null){
      sellManagerInstance = new SellManager();
    }
    return sellManagerInstance;
  }

  public void registerSellable(Sellable sellable) {
    sellables.add(sellable);
  }

  public void removeSellable(Sellable sellable) {
    sellables.remove(sellable);
  }

  public List<Sellable> getSellables() {
    return sellables;
  }
}
