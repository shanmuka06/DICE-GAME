package Dice_Game_Simulation;

import java.util.Scanner;

public class Executer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🎲 Welcome to Dice Game Simulation 🎲");
        System.out.println("Choose Mode:");
        System.out.println("1. Player vs Computer");
        System.out.println("2. Multiplayer (Player vs Player)");
        int choice = sc.nextInt();
        sc.nextLine(); // clear buffer

        boolean vsComputer = (choice == 1);

        System.out.print("Enter Player 1 name: ");
        String name1 = sc.nextLine();

        String name2;
        if (vsComputer) {
            name2 = "Computer";
        } else {
            System.out.print("Enter Player 2 name: ");
            name2 = sc.nextLine();
        }

        System.out.print("Enter number of rounds: ");
        int rounds = sc.nextInt();

        Player player1 = new Player(name1);
        Player player2 = new Player(name2);

        GameEngine game = new GameEngine(player1, player2, rounds, vsComputer);
        game.start();
    }
}
