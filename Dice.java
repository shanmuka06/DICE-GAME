package Dice_Game_Simulation;

import java.util.Random;

// Dice class generates random number between 1-6
public class Dice {
    private Random rand;

    public Dice() {
        rand = new Random();
    }

    public int roll() {
        return rand.nextInt(6) + 1; // 1–6
    }
}
