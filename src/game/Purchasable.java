package game;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public interface Purchasable {

  int getPurchasePrice();

  WeaponItem getInstance();
}
