package game.utils;

import java.util.Random;

/**
 * A Utilities class
 *
 * @author FIT2099 Lab 7 Team 6
 * @version 1.0
 */
public class Utils {

    /**
     * @param chance The percentage change of something occuring
     * @return True if chance generated is less than the probability, false otherwise
     */
    public static boolean randomBooleanGenerator(int chance) {
        Random rand = new Random();
        int prob = rand.nextInt(100);
        return (100 - chance) <= prob;

    }

    /**
     * Generate number from min<= n < max
     * not inclusive max;
     *
     * @param min The minimum
     * @param max The maximum
     * @return The randomly generated number
     */
    public static int randomNumberGenerator(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    /**
     * Simulate the random boolean generator function to ensure that it's working as intended
     *
     * @param args
     */
    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            if (Utils.randomBooleanGenerator(100)) {
                count++;
            }
        }
        System.out.println(count);
        System.out.println(count / 1000.0);
    }
}
