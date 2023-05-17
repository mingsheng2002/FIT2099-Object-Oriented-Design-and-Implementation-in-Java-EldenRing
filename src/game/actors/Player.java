package game.actors;

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
import game.actors.archetypes.Archetype;
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
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see Actor
 * @see Resettable
 */
public class Player extends Actor implements Resettable {

	/**
	 * Menu item used for printing out the meaningful descriptions in the console I/O.
	 */
	private final Menu menu = new Menu();
	/**
	 * Damage dealt by the player's intrinsic weapon.
	 */
	private static final int DAMAGE = 11;
	/**
	 * Attack accuracy of the player's intrinsic weapon.
	 */
	private static final int HIT_RATE = 100;
	/**
	 * Money currency object held by the player.
	 */
	private Runes runes = new Runes();
	/**
	 * Special item that can heal player's hitpoints, with a maximum usage of 2.
	 */
	private FlaskOfCrimsonTears flaskOfCrimsonTears;
	/**
	 * Keep track of player's last location on the game map.
	 */
	private Location lastLocation;
	/**
	 * Keep track of the location of the Site Of Lost Grace that is last visited by the player.
	 */
	private Location visitedSiteOfLostGrace;
	/**
	 * The Game Map that the player is currently on.
	 */
	private GameMap map;
	/**
	 * True if we have already store the location of The First Step on the game map.
	 */
	private boolean hasTheFirstStepLocation = false;
	/**
	 * The archetype that the player is currently playing with.
	 */
	private Archetype archetype;

	/**
	 * Constructor for Player.
	 * @see Status#HOSTILE_TO_ENEMY
	 * @see RunesManager#getInstance()
	 * @see RunesManager#registerPlayerRunes(Runes)
	 * @see FlaskOfCrimsonTears
	 * @see ResetManager#getInstance()
	 * @see ResetManager#registerResettable(Resettable)
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

	/**
	 * Select and return an action for the Player to perform on the current turn.
	 * Print out some descriptive messages in the console I/O showing the player's current hitpoints, runes amount,
	 * and remaining usage of Flask Of Crimson Tears.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 * @see Status#PROTECTED
	 * @see Status#AREA_ATTACK
	 * @see AreaAttackAction
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		this.map = map;

		// PROTECTED if Quickstep Action is executed by Player, remove this capability at start of every round
		if (this.hasCapability(Status.PROTECTED)){
			this.removeCapability(Status.PROTECTED);
		}

		// set the location of the Site Of Lost Grace during the first round of game
		if (!hasTheFirstStepLocation){
			getTheFirstStep(map);
		}
		if (map.locationOf(this).getGround().getDisplayChar()=='U'){
			setVisitedSiteOfLostGrace(map.locationOf(this));
			//return new ResetAction();
		}
		// set last location of player
		this.lastLocation = map.locationOf(this);

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

	/**
	 * Get the intrinsic weapon for Player.
	 * @return An instance of IntrinsicWeapon.
	 * @see IntrinsicWeapon
	 */
	public IntrinsicWeapon getIntrinsicWeapon(){
		return new IntrinsicWeapon(DAMAGE, "punches", HIT_RATE);
	}

	/**
	 * When the game is reset, this method will be executed.
	 *
	 * If the player is not conscious, meaning if he is defeated by the other actors (including enemies), then player's
	 * runes will be dropped onto the last location just before he died. Descriptive message is printed out. Then, the
	 * player will be moved to the location of his last visited Site of Lost Grace, so that he can be revived with full
	 * health at that particular location. Lastly, the player's hitpoints will be reset to the maximum.
	 *
	 * On the other hand, if the player is still conscious, meaning if he chooses to reset the game but not defeated by
	 * other actors, then he will not drop any runes on the ground, but just simply his hitpoints will be reset to the
	 * maximum. Descriptive message is printed out.
	 *
	 * @see RunesManager#getInstance()
	 * @see RunesManager#getPlayerRunes()
	 * @see RunesManager#registerDroppingRunes(Runes)
	 * @see RunesManager#clearPlayerRunes()
	 * @see RunesManager#getDroppedRunes()
	 * @see RunesManager#registerDroppedRunes(Runes)
	 * @see Runes
	 */
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

				// register droppedRunes
				Runes droppedRunes = RunesManager.getInstance().getDroppedRunes();
				if (droppedRunes == null) {
					RunesManager.getInstance().registerDroppedRunes(droppingRunes);
					RunesManager.getInstance().registerDroppingRunes(null);
				}
			}
			else {
				System.out.println(this + " has no runes to drop");
			}
			map.removeActor(this);
			map.moveActor(this, getVisitedSiteOfLostGrace());

			// When the player dies, remove the double attack damage capability that Player got from Golden Potion by chance
			if(this.hasCapability(Status.DOUBLE_ATTACK_DAMAGE)){
				this.removeCapability(Status.DOUBLE_ATTACK_DAMAGE);
			}
		}
		this.resetMaxHp(this.archetype.getHitPoints());
		System.out.println(this + " revives with full health");
	}

	/**
	 * Find and set the location of The First Step during the first round of the game. So that if the player dies and
	 * the game resets but the player has not yet visited any Site Of Lost Grace, then the player can be revived at the
	 * location of The First Step.
	 *
	 * @param map the Game Map that the player is currently on.
	 */
	private void getTheFirstStep(GameMap map){
		int width = map.getXRange().max() - map.getXRange().min();
		int height = map.getYRange().max() - map.getYRange().min();

		for (int y = 0; y < height+1; y++) {
			for (int x = 0; x < width+1; x++) {
				if (map.at(x,y).getGround().getDisplayChar() == 'U'){
					setVisitedSiteOfLostGrace(map.at(x,y));
					// we have set the location of the Site Of Lost Grace
					this.hasTheFirstStepLocation = true;
				}
			}
		}
	}

	/**
	 * Getter to get the location of the player's last visited Site Of Lost Grace.
	 * @return the location of the player's last visited Site Of Lost Grace.
	 */
	public Location getVisitedSiteOfLostGrace() {
		return visitedSiteOfLostGrace;
	}

	/**
	 * Setter to set the location of the Site Of Lost Grace that the player is currently visiting.
	 * @param visitedSiteOfLostGraceGrace location of the Site Of Lost Grace that the player is currently visiting.
	 */
	public void setVisitedSiteOfLostGrace(Location visitedSiteOfLostGraceGrace) {
		this.visitedSiteOfLostGrace = visitedSiteOfLostGraceGrace;
	}

	/**
	 * Getter to get the location of the player in the previous turn of game.
	 * @return the last location of the player.
	 */
	public Location getLastLocation() {
		return lastLocation;
	}

	/**
	 * Helper method to check if the surroundings of the player has an actor that he can attack.
	 * @param actorLocation the location that the player is currently on.
	 * @return true if there is an player-attackable actor in his surroundings, false otherwise.
	 * @see Status#PROTECTED
	 */
	private boolean surroundingHasAttackableActor(Location actorLocation){
		for (Exit exit : actorLocation.getExits()){
			if (exit.getDestination().containsAnActor() && !exit.getDestination().getActor().hasCapability(Status.PROTECTED)){
				return true;
			}
		}
		return false;
	}
}
