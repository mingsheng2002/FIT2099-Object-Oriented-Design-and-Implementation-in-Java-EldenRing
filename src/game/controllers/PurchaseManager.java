package game.controllers;

import game.Purchasable;
import game.Resettable;
import java.util.ArrayList;
import java.util.List;

public class PurchaseManager {

  private List<Purchasable> purchasables = new ArrayList<>();
  private static PurchaseManager purchaseManagerInstance = null;

  private PurchaseManager(){
  }

  public static PurchaseManager getInstance(){
    if(purchaseManagerInstance == null){
      purchaseManagerInstance = new PurchaseManager();
    }
    return purchaseManagerInstance;
  }

  public void registerPurchasable(Purchasable purchasable) {
    purchasables.add(purchasable);
  }

  public void removePurchasable(Purchasable purchasable) {
    purchasables.remove(purchasable);
  }

  public List<Purchasable> getPurchasables() {
    return purchasables;
  }
}
