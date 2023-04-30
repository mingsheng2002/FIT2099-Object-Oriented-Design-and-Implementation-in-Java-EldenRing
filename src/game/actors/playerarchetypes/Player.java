package game.actors.playerarchetypes;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.resets.ResetManager;
import game.controllers.RunesManager;
import game.items.FlaskOfCrimsonTears;
import game.items.Runes;
import game.resets.Resettable;
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
	private static final int DAMAGE = 11;
	private static final int HIT_RATE = 100;
	protected int playerMaxHitPoints;
	private Runes runes = new Runes();
	private FlaskOfCrimsonTears flaskOfCrimsonTears;
	//store player last location
	private Location lastLocation;
	private Location visitedSiteOfLostGrace;
	private GameMap map;
	private boolean hasTheFirstStepLocation = false;
	private Archetype archetype;

	/**
	 * Constructor.
	 *
	 */
	public Player(String name, char displayChar, Archetype archetype) {
		super(name, displayChar, archetype.getHitPoints());
		this.archetype = archetype;
		this.addWeaponToInventory(archetype.getWeaponItem());
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		// register player's runes
		RunesManager.getInstance().registerPlayerRunes(runes);
		this.addItemToInventory(runes);
		// add Flask of Crimson Tears
		flaskOfCrimsonTears = new FlaskOfCrimsonTears();
		this.addItemToInventory(flaskOfCrimsonTears);
		// register to resettable
		ResetManager.getInstance().registerResettable(this);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		this.map = map;

		// PROTECTED if Quickstep Action is executed by Player, remove this capability at start of every round
		if (this.hasCapability(Status.PROTECTED)){
			this.removeCapability(Status.PROTECTED);
		}

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

		// add Area Attack action to player only once - if surrounding has at least one actor that can be attacked by player
		Location actorLocation = map.locationOf(this);
		if (surroundingHasAttackableActor(actorLocation)){
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


	@Override
	public void reset() {
		Runes playerRunes = RunesManager.getInstance().getPlayerRunes();

		// true only when reset is caused by player death
		// enter this block if player has not dropped runes before / has picked up their dropped runes
		if (!this.isConscious()) {
			// create a new runes drop at player's last location
			if (playerRunes.getTotalAmount() != 0) {
				Runes droppingRunes = new Runes();

				droppingRunes.setTotalAmount(playerRunes.getTotalAmount());
				//register dropping runes
				RunesManager.getInstance().registerDroppingRunes(droppingRunes);
				//drop at player's last location
				droppingRunes.dropAtLastLocation(this.getLastLocation());
				//set drop location to dropping runes
				droppingRunes.setDropLocation(this.getLastLocation());
				// clear the player's runes amount
				RunesManager.getInstance().clearPlayerRunes();

				System.out.println(droppingRunes + " is dropped");

				//
				Runes droppedRunes = RunesManager.getInstance().getDroppedRunes();
				if (droppedRunes == null) {
					RunesManager.getInstance().registerDroppedRunes(droppingRunes);
				}
			}
			else {
				System.out.println(this + " has no runes to drop");
			}
			map.moveActor(this,getVisitedSiteOfLostGrace());
		}
		this.resetMaxHp(getMaxHp());
		System.out.println(this + " revives");
	}

	private void getTheFirstStep(GameMap map){
		int width = map.getXRange().max() - map.getXRange().min();
		int height = map.getYRange().max() - map.getYRange().min();

		for ( int y = 0; y < height+1; y++){
			for ( int x = 0; x < width+1; x++){

				if (map.at(x,y).getGround().getDisplayChar() == 'U'){
					setVisitedSiteOfLostGrace(map.at(x,y));
					setHasTheFirstStepLocation(true);
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

	public Location getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(Location lastLocation) {
		this.lastLocation = lastLocation;
	}

	public boolean surroundingHasAttackableActor(Location actorLocation){
		for (Exit exit : actorLocation.getExits()){
			if (exit.getDestination().containsAnActor() && !exit.getDestination().getActor().hasCapability(Status.PROTECTED)){
				return true;
			}
		}
		return false;
	}
}
