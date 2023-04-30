package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public interface Sellable {

  int getSellPrice();

  Action getSellAction(Actor customer, Sellable sellable);

}
