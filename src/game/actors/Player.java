package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.actors.enemies.Enemy;
import game.controllers.ResetManager;
import game.controllers.RunesManager;
import game.items.FlaskOfCrimsonTears;
import game.items.Runes;
import game.weapons.Club;
import game.Resettable;
import game.enums.Status;

import java.util.ArrayList;
import java.util.List;

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
	private static final int DAMAGE = 11;
	private static final int HIT_RATE = 100;
	protected int playerMaxHitPoints;
	private Runes runes = new Runes();
	private FlaskOfCrimsonTears flaskOfCrimsonTears = new FlaskOfCrimsonTears();
	//store player last location
	private Location lastLocation;
	private boolean hasDeath;
	private List<Location> visitedSiteOfGrace = new ArrayList<>();
	private GameMap map;


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
		RunesManager.getInstance().registerRunes(runes);
		this.addItemToInventory(runes);
		this.addItemToInventory(flaskOfCrimsonTears);
		this.hasDeath = false;
		ResetManager.getInstance().registerResettable(this);



		//RunesManager.getInstance().incrementAmount(0, runes);
		//this.addWeaponToInventory(new Grossmesser()); /////////////////////////////////testing
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		this.map = map;
		//TBC how to get the first site of lost grace location?
		visitedSiteOfGrace.add(new Location(map,21,19));
		if (map.locationOf(this).getGround().getDisplayChar()=='U'){
			visitedSiteOfGrace.add(map.locationOf(this));

		}
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		String hotKey = lastAction.hotkey();
		//set player last location
		if (hotKey != null) {
			setLastLocation(calcLastLocation(hotKey, map.locationOf(this), map));
		}
		System.out.println("Player " + this + " current hit points: " + this.hitPoints + "/" + this.maxHitPoints);
		System.out.println("Player " + this + " currently holding " + this.runes.getTotalAmount() + " of runes");
		System.out.println("Player " + this + " left  " + this.flaskOfCrimsonTears.getNumOfUsageLeft() + " usage of Flask Of Crimson Tears");

		boolean found = false;
		int i = 0;
		while (!found && i < this.getWeaponInventory().size()) {
			WeaponItem weaponItem = this.getWeaponInventory().get(i);
			if (weaponItem.hasCapability(Status.AREA_ATTACK)) {
				actions.add(new AreaAttackAction(weaponItem));
				found = true;
			}
			i++;
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}


	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		// actions are only meant for player, so in this case, otherActor == Enemy won't have actions
		return super.allowableActions(otherActor, direction, map);
	}

	public IntrinsicWeapon getIntrinsicWeapon(){
		return new IntrinsicWeapon(DAMAGE, "punches", HIT_RATE);
	}


	public Location getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(Location lastLocation) {
		this.lastLocation = lastLocation;
	}

	@Override
	public void reset() {
		if (hasDeath ){
			System.out.println("Location before: "+map.locationOf(this).x() +" "+ map.locationOf(this).y());
			Location location =  visitedSiteOfGrace.get(visitedSiteOfGrace.size()-1);
			System.out.println("here"+ location.x() +location.y());

			//map.moveActor(this,location);

		}
		System.out.println("Location after: "+map.locationOf(this).x() +" "+ map.locationOf(this).y());
		this.resetMaxHp(getMaxHp());
		System.out.println(this+ " revive");
	}

	public Runes getRunes(){
		return runes;
	}

	public boolean isHasDeath() {
		return hasDeath;
	}

	public void setHasDeath(boolean hasDeath) {
		this.hasDeath = hasDeath;
	}

	public FlaskOfCrimsonTears getFlaskOfCrimsonTears() {
		return flaskOfCrimsonTears;
	}

	public GameMap getMap() {
		return map;
	}

	private Location calcLastLocation (String hotkey, Location currentLocation, GameMap map){

		int x = currentLocation.x();
		int y = currentLocation.y();

		switch (hotkey) {
			case "1":
				x+=1;
				y-=1;
				break;

			case "2":
				y-=1;
				break;

			case "3":
				x-=1;
				y-=1;
				break;

			case "4":
				x+=1;
				break;

			case "6":
				x-=1;
				break;

			case "7":
				x+=1;
				y+=1;
				break;

			case "8":
				y+=1;
				break;

			case "9":
				x-=1;
				y+=1;
				break;

			case "5":
				break;
		}
		return new Location(map,x,y);



	}
}
