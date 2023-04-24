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
import game.actions.ResetAction;
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
	private Location visitedSiteOfLostGrace;
	private GameMap map;
	private boolean hasTheFirstStepLocation = false;


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
		//this.addWeaponToInventory(new Club());
		RunesManager.getInstance().registerRunes(runes);
		this.addItemToInventory(runes);
		this.addItemToInventory(flaskOfCrimsonTears);
		ResetManager.getInstance().registerResettable(this);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		this.map = map;

		System.out.println("location of player: "+ map.locationOf(this).x() +" , "+ map.locationOf(this).y());

		if (!getHasTheFirstStepLocation()){
			getTheFirstStep(map);
		}
		if (map.locationOf(this).getGround().getDisplayChar()=='U'){
			setVisitedSiteOfLostGrace(map.locationOf(this));
			//return new ResetAction();
		}
		// set last location of player
		setLastLocation(map.locationOf(this));


		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		System.out.println("Player " + this + " current hit points: " + this.hitPoints + "/" + this.maxHitPoints);
		System.out.println("Player " + this + " currently holding " + this.runes.getTotalAmount() + " of runes");
		System.out.println("Player " + this + " is left with " + this.flaskOfCrimsonTears.getNumOfUsageLeft() + " usage of Flask Of Crimson Tears");

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
		// true only when reset is caused by player death
		if (!this.isConscious()){
			// create a new runes drop at last location
			if (RunesManager.getInstance().getRunes().getTotalAmount()!=0) {
				Runes runes = new Runes();
				runes.setTotalAmount(RunesManager.getInstance().getRunes().getTotalAmount());
				getLastLocation().addItem(runes);
				//change the runes to portable
				runes.togglePortability();
				runes.setHasDrop(true);
				//set last location to runes
				runes.setDropLocation(getLastLocation());

				// set the player inventory amount to 0
				RunesManager.getInstance().decrementAmount(RunesManager.getInstance().getRunes().getTotalAmount());

				System.out.println("The values of the runes dropped is " + runes.getTotalAmount() + " at location " + getLastLocation().x() + " , " + getLastLocation().y());
			}else{
				System.out.println(this+" no runes to drop");
			}
			map.moveActor(this,getVisitedSiteOfLostGrace());
		}
		this.resetMaxHp(getMaxHp());
		System.out.println(this+ " revive");
	}


	private void getTheFirstStep(GameMap map){
		int width =map.getXRange().max()-map.getXRange().min();
		int height = map.getYRange().max() - map.getYRange().min();
		System.out.println(width);
		System.out.println(height);

		for ( int y= 0;y<height+1;y++){
			for ( int x = 0; x<width+1 ;x++){

				if (map.at(x,y).getGround().getDisplayChar()=='U'){
					setVisitedSiteOfLostGrace(map.at(x,y));
					System.out.println("heree le");
					setHasTheFirstStepLocation(true);
					System.out.println(x);
					System.out.println(y);
				}
			}
		}
	}
	public Location getVisitedSiteOfLostGrace() {
		return visitedSiteOfLostGrace;
	}

	public void setVisitedSiteOfLostGrace(Location visitedSiteOfLostGraceGrace) {
		this.visitedSiteOfLostGrace = visitedSiteOfLostGraceGrace;
	}
	public Runes getRunes(){
		return runes;
	}

	public GameMap getMap() {
		return map;
	}
	public void setMap(GameMap map) {
		this.map = map;
	}

	public boolean getHasTheFirstStepLocation() {
		return hasTheFirstStepLocation;
	}

	public void setHasTheFirstStepLocation(boolean hasTheFirstStepLocation) {
		this.hasTheFirstStepLocation = hasTheFirstStepLocation;
	}
}
