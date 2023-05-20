package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public enum Status {
    /**
     * Provided for non-enemy typed actor(s) (in this case: player) that can perform attacks.
     */
    HOSTILE_TO_ENEMY,
    /**
     * Provided for actors that can possibly be revived with full health once defeated.
     */
    REVIVABLE,
    /**
     * Provided for NPC to indicate that they can follow a player.
     */
    FOLLOWING,
    /**
     * Provided for certain weapon items that allow actors to perform area attack.
     */
    AREA_ATTACK,
    /**
     * Provided for Canis Lupus type enemies, indicating their commonalities and characteristics such as cannot attack their same type.
     */
    FRIENDLY_TO_CANIS_LUPUS,
    /**
     * Provided for Crustacean type enemies, indicating their commonalities and characteristics such as cannot attack their same type.
     */
    FRIENDLY_TO_CRUSTACEAN,
    /**
     * Provided for Skeleton type enemies, indicating their commonalities and characteristics such as cannot attack their same type.
     */
    FRIENDLY_TO_SKELETON,
    /**
     * Provided for the inhabitant enemies of Stormveil Castle, indicating their commonalities and characteristics such as cannot attack their same type.
     */
    FRIENDLY_TO_STORMVEIL_CASTLE_INHABITANT,
    /**
     * TBC
     */
    FRIENDLY_TO_PLAYER,
    /**
     * TBC
     */
    FRIENDLY_TO_INVADER,
    /**
     * Provided for certain weapon items that allow actors to perform specific special skills (Quickstep and Unsheathe actions in this case).
     */
    SPECIAL_SKILL,
    /**
     * Provided for enemies that have chance of despawning from the game map, so that they cannot be attacked by the other actors
     * when they are currently despawning.
     */
    DESPAWNING,
    /**
     * Provided for actors that are to be protected permanently or temporarily from attacks.
     */
    PROTECTED,
    /**
     * Provided for traders that allow actor to purchase item or weapon item.
     */
    PROVIDE_PURCHASE_SERVICE,
    /**
     * Provided for traders that allow actor to sell item or weapon item to them.
     */
    PROVIDE_SELL_SERVICE,
    /**
     * Provided for traders that allow actor to exchange item or weapon item to them.
     */
    PROVIDE_EXCHANGE_SERVICE,
    /**
     * Provided for certain items or weapon items in the player's inventory when the player is near a trader.
     */
    READY_TO_BE_SOLD,
    /**
     * Provided for certain items or weapon items in the player's inventory when the player is near a trader.
     */
    READY_TO_BE_EXCHANGED,
    /**
     * Provided for the enemy-spawnable ground that can spawn enemies of type Canis Lupus.
     */
    SPAWN_CANIS_LUPUS,
    /**
     * Provided for the enemy-spawnable ground that can spawn enemies of type Crustacean.
     */
    SPAWN_CRUSTACEAN,
    /**
     * Provided for the enemy-spawnable ground that can spawn enemies of type Skeleton.
     */
    SPAWN_SKELETON,
    /**
     * Provided for the enemy-spawnable ground that can spawn Stormveil Castle dog.
     */
    SPAWN_STORMVEIL_CASTLE_DOG,
    /**
     * Provided for the enemy-spawnable ground that can spawn Stormveil Castile soldier.
     */
    SPAWN_STORMVEIL_CASTLE_SOLDIER,
    /**
     * Provided for the player to buff the player if the Golden Potion that the player consume can double the player's attack damage.
     */
    DOUBLE_ATTACK_DAMAGE,
    /**
     * Provided for the player to buff the player if the Golden Potion that the player consume can increase the number of usage of The Flask of Crimson Tears by one.
     */
    INCREASE_CRIMSON_TEARS,
    /**
     * Provided for the player to debuff the player if the Golden Potion that the player consume can decrease the number of usage of The Flask of Crimson Tears by one.
     */
    DECREASE_CRIMSON_TEARS

}
