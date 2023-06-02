import javax.swing.*;
import java.awt.*;


public class Main {
    private static final int BOARD_SIZE = 100;

    private JFrame frame;
    private JPanel boardPanel;
    private JLabel[] cells;
    private JLabel diceValueLabel;
    private JLabel[] playerLabels;
    private JButton rollDiceButton;
    private JButton resetButton;

    private Board board;
    private Player[] players;
    private Game game;

    public Main() {
        initializeBoard();
        initializePlayers();
        initializeGame();
        createInterface();
    }

    private void initializeBoard() {

    }

    private void initializePlayers() {


    private void initializeGame() {

    }

    private void createInterface() {

        JPanel controlPanel = new JPanel();
        controlPanel.add(diceValueLabel);
        controlPanel.add(rollDiceButton);
        controlPanel.add(resetButton);

        frame.getContentPane().add(boardPanel, BorderLayout.CENTER);
        frame.getContentPane().add(infoPanel, BorderLayout.WEST);
        frame.getContentPane().add(controlPanel, BorderLayout.SOUTH);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    private void updatePlayerPositions() {

    }

    private void resetGame() {

    }

    public static void main(String[] args) {

            }
        });
    }
}
