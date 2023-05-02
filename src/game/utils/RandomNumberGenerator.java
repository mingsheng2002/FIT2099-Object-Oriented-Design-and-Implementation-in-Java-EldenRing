package game.utils;

import java.util.Random;

/**
 * A random number generator
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Che'er Min Yi
 * @author Chong Ming Sheng
 * @author Lam Xin Yuan
 * @version 1.0.0
 *
 */
public class RandomNumberGenerator {

    /**
     * Returns an integer between 0 (inclusive) and the specified bound (exclusive).
     * @param bound int that representing the upper bound
     * @return  randomly generated int between 0 (inclusive ) and bound(exclusive).
     * @see Random#nextInt()
     */
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }

    /**
     * Returns an integer between lowerBound(inclusive) and the upperBound(exclusive).
     * @param lowerBound int that representing the lower bound
     * @param upperBound int that representing the upper bound
     * @return randomly generated int between lower bound (inclusive) and upper bound(exclusive).
     * @see Random#nextInt()
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }
}
