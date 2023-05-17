package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.playeractions.ConsumeAction;
import game.actions.playeractions.SummonAction;
import game.actors.nonplayercharacters.summonables.*;
import game.controllers.RunesManager;
import game.enums.Status;
import game.utils.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.List;

public class GoldenPotion extends Item implements Consumable {

  ConsumeAction consumeAction;
  Location here;

  /***
   * Constructor.
   */
  public GoldenPotion() {
    super("Golden Portion", '"', true);
    consumeAction = new ConsumeAction(this);
  }

  @Override
  public void tick(Location currentLocation, Actor actor) {
    here = currentLocation;
    if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
      this.addAction(consumeAction);
    }
  }

  @Override
  public DropAction getDropAction(Actor actor) {
    this.removeAction(consumeAction);
    return super.getDropAction(actor);
  }

  @Override
  public void consumedBy(Actor actor) {
    int x = RandomNumberGenerator.getRandomInt(1,12);

    switch(x){
      case 1:
        // Increase the player's maximum hit point
        System.out.println("This potion will increase " + actor + " maximum hit point");
        int amount = 1000;
        actor.resetMaxHp(amount);
        System.out.println(actor + "'s maximum hit point is set to " + amount);
        break;

      case 2:
        // Double the player's runes amount
        System.out.println("This potion will generate more Runes for " + actor);
        int runesAmount = RunesManager.getInstance().getPlayerRunes().getTotalAmount();
        RunesManager.getInstance().incrementPlayerRunes(runesAmount);
        System.out.println(actor + "'s Runes amount is set to " + RunesManager.getInstance().getPlayerRunes().getTotalAmount());
        break;

      case 3:
        // Double the attack damage from this point onwards
        System.out.println("This potion will strengthen " + actor + "'s attack");
        actor.addCapability(Status.DOUBLE_ATTACK_DAMAGE);
        System.out.println(actor + "'s attack damage is doubled from this turn onwards");
        break;

      case 4:
        // Increase the number of usage for Flask of Crimson Tears
        System.out.println("This potion is a Flask of Crimson Tears");
        actor.addCapability(Status.INCREASE_CRIMSON_TEARS);
        System.out.println("The number of uses of Flask of Crimson Tears increases by one");
        break;

      case 5:
        // Add RemembranceOfTheGrafted into actor's inventory
        actor.addItemToInventory(new RemembranceOfTheGrafted());
        System.out.println("A Remembrance of The Grafter is added to " + actor + "'s inventory");
        break;

      case 6:
        // Add another GoldenPotion at Player's surrounding
        System.out.println("This potion will generate a new Golden Potion at " + actor + "'s surrounding");
        int i = RandomNumberGenerator.getRandomInt(here.getExits().size() - 1);
        Location location = here.getExits().get(i).getDestination();
        location.addItem(new GoldenPotion());
        System.out.println("A new Golden Potion is added to (" + location.x() + ", " + location.y() + ")");
        break;

      case 7:
        // Summon a guest from another realm
        System.out.println("This potion will summon a guest from another realm");
        List<Summonable> summonables = new ArrayList<>();
        summonables.add(new Ally());
        summonables.add(new Invader());
        String result = new SummonAction(here, summonables).execute(actor, here.map());
        System.out.println(result);
        break;

      case 8:
        // Poison the player and causes the decrease of hit point
        System.out.println("This potion is poisonous");
        int hurtPoint = RandomNumberGenerator.getRandomInt(100, 200);
        actor.hurt(hurtPoint);
        System.out.println(actor + "'s hit point decreases by " + hurtPoint);
        break;

      case 9:
        // Set player's Runes to 0
        System.out.println("This potion will melt all the Runes that " + actor + " holds");
        RunesManager.getInstance().clearPlayerRunes();
        System.out.println(actor + "'s Runes is set to 0");
        break;

      case 10:
        // Reduce area attack and special attack of player's weapon
        // Other weapon that the player pick up again afterwards will not be affected
        System.out.println("This potion will degrade some weapon's skill in " + actor + "'s inventory");
        for (WeaponItem weapon : actor.getWeaponInventory()){
          if (weapon.hasCapability(Status.AREA_ATTACK)) {
            weapon.removeCapability(Status.AREA_ATTACK);
          }
          else if (weapon.hasCapability(Status.SPECIAL_SKILL)){
            weapon.removeCapability(Status.SPECIAL_SKILL);
          }
        }
        System.out.println("The Area Attack and Special skill of all weapons in " + actor + "'s weapon inventory has been removed");
        break;

      case 11:
        // Decrease the number of usage for Flask of Crimson Tears
        System.out.println("This potion will neutralize the Flask of Crimson Tears");
        actor.addCapability(Status.DECREASE_CRIMSON_TEARS);
        System.out.println("Number of uses of Flask of Crimson Tears decreases by one");
        break;

      case 12:
        // Clear actor's weapon inventory
        System.out.println("This potion will melt all the weapons in " + actor + "'s inventory");
        List<WeaponItem> weapons = actor.getWeaponInventory();
        for (WeaponItem weapon : new ArrayList<WeaponItem>(weapons)){
          actor.removeWeaponFromInventory(weapon);
        }
        System.out.println(actor + "'s weapon inventory is cleared");
        break;

    }
    actor.removeItemFromInventory(this);
  }
}
