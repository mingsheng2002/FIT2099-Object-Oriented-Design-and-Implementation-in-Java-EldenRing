package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public interface Purchasable {

  int getPurchasePrice();

  Action getPurchaseAction(Actor purchaser, Purchasable purchasable);

  WeaponItem getPurchasableInstance();
}
