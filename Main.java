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
        board.addLadder(45,70);


        board.addSnake(17, 7);
        board.addSnake(54, 34);
        board.addSnake(62, 19);
        board.addSnake(88,61);

    }

    private void initializePlayers() {
        players = new Player[2];
        players[0] = new Player("Player 1");
        players[1] = new Player("Player 2");
    }
        private void initializeGame() {
            game = new Game(board, players);
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


            JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            playerLabels = new JLabel[players.length];
            for (int i = 0; i < players.length; i++) {
                JLabel playerLabel = new JLabel(players[i].getName() + ": 0");
                playerLabels[i] = playerLabel;
                infoPanel.add(playerLabel);
            }

            diceValueLabel = new JLabel("Dice Value: ");
            rollDiceButton = new JButton("Roll Dice");
            rollDiceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int diceRoll = game.rollDice();
                    diceValueLabel.setText("Dice Value: " + diceRoll);
                    game.play();
                    updatePlayerPositions();
                }
            });


            resetButton = new JButton("Reset");
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetGame();
                }
            });
        }



    private void updatePlayerPositions() {
            for (int i = 0; i < players.length; i++) {
                Player player = players[i];
                int position = player.getPosition();
                cells[position - 1].setText(player.getName());
                playerLabels[i].setText(player.getName() + ": " + position);
            }


        }

        private void resetGame() {

        }

        public static void main(String[] args) {

        }
    });
}
}
