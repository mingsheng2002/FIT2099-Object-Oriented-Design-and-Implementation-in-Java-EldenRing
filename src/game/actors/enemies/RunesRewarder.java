package game.actors.enemies;

/**
 * A RunesRewarder interface represents a kind of objective that a source of runes in the Elden Ring game can have.
 * It gives promises to every source of runes such that their classes implementing this interface should have also
 * implemented these two methods in this interface.
 *
 * This interface is crucial so that if a source of runes is killed by the player, then the amount of their dropped runes
 * can be rewarded to the player. Their minimum and maximum amount of runes to be rewarded could be access via the
 * implementation of these two specific methods.
 *
 * Created by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 */
public interface RunesRewarder {
  /**
   * Retrieve the minimum runes amount that can be rewarded to the player from the runes source.
   * @return minimum runes amount that can be rewarded to the player.
   */
  int getMinRewardRunes();

  /**
   * Retrieve the maximum runes amount that can be rewarded to the player from the runes source.
   * @return maximum runes amount that can be rewarded to the player.
   */
  int getMaxRewardRunes();
}
