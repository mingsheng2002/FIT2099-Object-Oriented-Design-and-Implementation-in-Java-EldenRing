package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.actors.enemies.Enemy;
import game.controllers.RunesManager;
import game.items.Runes;
import game.weapons.Club;
import game.Resettable;
import game.enums.Status;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();
	private static final int DAMAGE = 500;  //11
	private static final int HIT_RATE = 100;

	protected int playerMaxHitPoints;
	private Action playerLastAction;

	private Runes runes = new Runes();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.playerMaxHitPoints = hitPoints;
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.RESTING);
		this.addWeaponToInventory(new Club());
		this.addItemToInventory(runes);
		RunesManager.getInstance().incrementAmount(0, runes);
		//this.addWeaponToInventory(new Grossmesser()); /////////////////////////////////testing
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		System.out.println("Player " + this + " current hit points: " + this.hitPoints + "/" + this.maxHitPoints);
		System.out.println("Player " + this + " currently holding " + this.runes.getTotalAmount() + " of runes");


		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		// If otherActor is not Player, but an Enemy
		if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !otherActor.hasCapability(Status.RESTING)){
			((Enemy)otherActor).getBehaviours().put(1, new FollowBehaviour(this));
			otherActor.addCapability(Status.FOLLOWING);
			if (!otherActor.getWeaponInventory().isEmpty()) {
				((Enemy)otherActor).getBehaviours().put(0, new AttackBehaviour(this, direction, otherActor.getWeaponInventory().get(0)));
			}
			else {
				((Enemy)otherActor).getBehaviours().put(0, new AttackBehaviour(this, direction));
			}
			// HINT 1: The AttackAction above allows you to attak the enemy with your intrinsic weapon.
			// HINT 1: How would you attack the enemy with a weapon?
		}
		return actions;
	}

	public IntrinsicWeapon getIntrinsicWeapon(){
		return new IntrinsicWeapon(DAMAGE, "punches", HIT_RATE);
	}

	@Override
	public void reset() {}

	public Action getPlayerLastAction() {
		return playerLastAction;
	}

	public Runes getRunes(){
		return runes;
	}
}
