package Dice_Game_Simulation;

import java.util.Scanner;

public class GameEngine {
    private Player player1;
    private Player player2;
    private Dice dice;
    private int rounds;
    private boolean vsComputer;

    public GameEngine(Player player1, Player player2, int rounds, boolean vsComputer) {
        this.player1 = player1;
        this.player2 = player2;
        this.rounds = rounds;
        this.dice = new Dice();
        this.vsComputer = vsComputer;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= rounds; i++) {
            System.out.println("\n--- Round " + i + " ---");

            // Player 1 roll
            System.out.println(player1.getName() + ", press Enter to roll dice...");
            sc.nextLine();
            int roll1 = dice.roll();
            player1.addScore(roll1);
            System.out.println(player1.getName() + " rolled: " + roll1);

            // Player 2 roll (either Computer or Player 2)
            int roll2;
            if (vsComputer) {
                roll2 = dice.roll();
                player2.addScore(roll2);
                System.out.println("Computer rolled: " + roll2);
            } else {
                System.out.println(player2.getName() + ", press Enter to roll dice...");
                sc.nextLine();
                roll2 = dice.roll();
                player2.addScore(roll2);
                System.out.println(player2.getName() + " rolled: " + roll2);
            }
        }

        declareWinner();
    }

    private void declareWinner() {
        System.out.println("\n=== Final Scores ===");
        System.out.println(player1.getName() + ": " + player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());

        if (player1.getScore() > player2.getScore()) {
            System.out.println("🏆 " + player1.getName() + " WINS!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println("🏆 " + player2.getName() + " WINS!");
        } else {
            System.out.println("🤝 It's a DRAW!");
        }
    }
}
