package game.actors.nonplayercharacters.enemies.stormveilcastleinhabitants;


import game.resets.ResetManager;
import game.resets.Resettable;
import game.weapons.portableweapons.HeavyCrossbow;


/**
 * A Stormveil Castle inhabitant enemy called Godrick Soldier.
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 * @see StormveilCastleInhabitant
 * @see Resettable
 */
public class GodrickSoldier extends StormveilCastleInhabitant implements Resettable {

    /**
     * The spawn chance of Godrick Soldier from its specific ground.
     */
    private final static int SPAWN_CHANCE = 45;
    /**
     * The chance of Godrick Soldier despawning from the game map.
     */
    private static final int DESPAWN_CHANCE = 10;
    /**
     * Health of Godrick Soldier.
     */
    private static final int HIT_POINTS = 198;
    /**
     * Minimum amount of runes dropped by Godrick Soldier if defeated by player.
     */
    private static final int MIN_RUNES_AWARD = 38;
    /**
     * Maximum amount of runes dropped by Godrick Soldier if defeated by player.
     */
    private static final int MAX_RUNES_AWARD = 70;

    /**
     * Constructor for Godrick Soldier.
     * @see ResetManager#getInstance()
     * @see ResetManager#registerResettable(Resettable)
     * @see HeavyCrossbow
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p',HIT_POINTS, SPAWN_CHANCE, DESPAWN_CHANCE, MIN_RUNES_AWARD, MAX_RUNES_AWARD);
        ResetManager.getInstance().registerResettable(this);
        this.addWeaponToInventory(new HeavyCrossbow());
    }

    /**
     * When the game is reset, this method will be executed and Godrick Soldier will be removed from the game map.
     */
    @Override
    public void reset() {
        if (this.getMap() != null){
            getMap().removeActor(this);
        }
    }
}
