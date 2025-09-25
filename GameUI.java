package Dice_Game_Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameUI extends JFrame {
    private Player player1, player2;
    private Dice dice;
    private int rounds, currentRound;
    private boolean vsComputer;

    private JLabel statusLabel, scoreLabel;
    private JButton rollButton;

    // Main Game Constructor
    public GameUI(String name1, String name2, int rounds, boolean vsComputer) {
        this.player1 = new Player(name1);
        this.player2 = new Player(name2);
        this.rounds = rounds;
        this.vsComputer = vsComputer;
        this.dice = new Dice();
        this.currentRound = 1;

        setTitle("Dice Game Simulation 🎲");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Round 1 - " + player1.getName() + "'s turn", JLabel.CENTER);
        scoreLabel = new JLabel(player1.getName() + ": 0 | " + player2.getName() + ": 0", JLabel.CENTER);

        rollButton = new JButton("Roll Dice 🎲");
        rollButton.addActionListener(new ActionListener() {
            private boolean player1Turn = true;

            public void actionPerformed(ActionEvent e) {
                if (currentRound <= rounds) {
                    if (player1Turn) {
                        int roll = dice.roll();
                        player1.addScore(roll);
                        JOptionPane.showMessageDialog(null, player1.getName() + " rolled: " + roll);
                        player1Turn = false;

                        if (vsComputer) {
                            int compRoll = dice.roll();
                            player2.addScore(compRoll);
                            JOptionPane.showMessageDialog(null, "Computer rolled: " + compRoll);
                            updateRound();
                            player1Turn = true;
                        }
                    } else {
                        int roll = dice.roll();
                        player2.addScore(roll);
                        JOptionPane.showMessageDialog(null, player2.getName() + " rolled: " + roll);
                        updateRound();
                        player1Turn = true;
                    }
                } else {
                    endGame();
                }
            }
        });

        add(statusLabel, BorderLayout.NORTH);
        add(scoreLabel, BorderLayout.CENTER);
        add(rollButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateRound() {
        currentRound++;
        scoreLabel.setText(player1.getName() + ": " + player1.getScore() +
                " | " + player2.getName() + ": " + player2.getScore());

        if (currentRound <= rounds) {
            statusLabel.setText("Round " + currentRound + " - " + player1.getName() + "'s turn");
        } else {
            endGame();
        }
    }

    private void endGame() {
        rollButton.setEnabled(false);
        String winner;
        if (player1.getScore() > player2.getScore()) {
            winner = "🏆 " + player1.getName() + " WINS!";
        } else if (player2.getScore() > player1.getScore()) {
            winner = "🏆 " + player2.getName() + " WINS!";
        } else {
            winner = "🤝 It's a DRAW!";
        }
        JOptionPane.showMessageDialog(this, winner);
    }

    // ---- Mode Selection & Start Menu ----
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame menuFrame = new JFrame("Dice Game Menu");
            menuFrame.setSize(300, 200);
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuFrame.setLayout(new GridLayout(3, 1));

            JButton pvcButton = new JButton("Player vs Computer");
            JButton pvpButton = new JButton("Multiplayer (Player vs Player)");
            JButton exitButton = new JButton("Exit");

            pvcButton.addActionListener(e -> {
                String name1 = JOptionPane.showInputDialog("Enter Player 1 name:");
                String name2 = "Computer";
                int rounds = Integer.parseInt(JOptionPane.showInputDialog("Enter number of rounds:"));
                menuFrame.dispose(); // close menu
                new GameUI(name1, name2, rounds, true);
            });

            pvpButton.addActionListener(e -> {
                String name1 = JOptionPane.showInputDialog("Enter Player 1 name:");
                String name2 = JOptionPane.showInputDialog("Enter Player 2 name:");
                int rounds = Integer.parseInt(JOptionPane.showInputDialog("Enter number of rounds:"));
                menuFrame.dispose(); // close menu
                new GameUI(name1, name2, rounds, false);
            });

            exitButton.addActionListener(e -> System.exit(0));

            menuFrame.add(pvcButton);
            menuFrame.add(pvpButton);
            menuFrame.add(exitButton);

            menuFrame.setVisible(true);
        });
    }
}
