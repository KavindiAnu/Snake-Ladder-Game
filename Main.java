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
        board = new Board(BOARD_SIZE);
        board.addLadder(10, 25);
        board.addLadder(16, 33);
        board.addLadder(30, 55);


        board.addSnake(17, 7);
        board.addSnake(54, 34);
        board.addSnake(62, 19);

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
