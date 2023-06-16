import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

class Board {
      private int size;

      private Map<Integer, Integer> ladderPositions;
      private Map<Integer, Integer> snakePositions;

      public Board(int size) {
        this.size = size;
        this.ladderPositions = new HashMap<>();
        this.snakePositions = new HashMap<>();
    }

      public int getSize() {
        return size;
    }

      public void addLadder(int start, int end) {
        ladderPositions.put(start, end);
    }

      public void addSnake(int start, int end) {
        snakePositions.put(start, end);
    }



    public int getNewPosition(int currentPosition) {
         int newPosition = currentPosition;

         if (ladderPositions.containsKey(currentPosition))
         newPosition = ladderPositions.get(currentPosition);
         else if (snakePositions.containsKey(currentPosition))
         newPosition = snakePositions.get(currentPosition);

         return newPosition;
        }

     public Map<Integer, Integer> getLadderPositions() {
         return ladderPositions;
        }

     public Map<Integer, Integer> getSnakePositions() {
         return snakePositions;
         }
         }


class Game {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;

    public Game(Board board, Player[] players) {
        this.board = board;
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    public void play() {
        Player currentPlayer = players[currentPlayerIndex];
        int diceRoll = rollDice();

        JOptionPane.showMessageDialog(null, currentPlayer.getName() + " rolls the dice and gets: " + diceRoll);
        
	int newPosition = currentPlayer.getPosition() + diceRoll;

        if (newPosition > board.getSize()) {
            JOptionPane.showMessageDialog(null, currentPlayer.getName() + " cannot move. Try again in the next turn.");
            return;
        }

        int finalPosition = board.getNewPosition(newPosition);
        currentPlayer.setPosition(finalPosition);

        System.out.println(currentPlayer.getName() + " moves to position: " + finalPosition);

        if (finalPosition == board.getSize()) {
            JOptionPane.showMessageDialog(null, currentPlayer.getName() + " wins the game!");
            return;
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }

    int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}

class Player {
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

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
	board.addLadder(5,70);
	board.addLadder(7,85);
	board.addLadder(9,90);


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

        frame = new JFrame("Snake and Ladder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel(new GridLayout(10, 10));
        cells = new JLabel[BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            JLabel cell = new JLabel(Integer.toString(i + 1));
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cell.setHorizontalAlignment(SwingConstants.CENTER);
            cells[i] = cell;
            boardPanel.add(cell);
        }


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
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            int position = player.getPosition();
            cells[position - 1].setText(player.getName());
            playerLabels[i].setText(player.getName() + ": " + position);
        }
    }

    private void resetGame() {
        for (Player player : players) {
            player.setPosition(0);
        }
        diceValueLabel.setText("Dice Value: ");
        for (int i = 0; i < cells.length; i++) {
            cells[i].setText(Integer.toString(i + 1));
        }
        updatePlayerPositions();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
