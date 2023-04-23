package game;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public interface Sellable {

  int getSellPrice();

  WeaponItem getInstance();
}
