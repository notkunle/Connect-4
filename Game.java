import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Game {
    private Board board; // Board instance
    private ScoreKeeper scoreKeeper; // ScoreKeeper instance
    private char currentPlayer; // Current player ('R' for Red, 'B' for Blue)
    private GridPane gridPane; // GridPane for the game board GUI
    private Circle[][] circles; // 2D array to keep track of Circle nodes
    private Label turnLabel; // Label to display whose turn it is
    private boolean isSinglePlayer; // Flag to indicate single-player mode

    public Game(boolean isSinglePlayer) {
        this.isSinglePlayer = isSinglePlayer;
        board = new Board();
        scoreKeeper = new ScoreKeeper();
        currentPlayer = 'R'; // Start with Red
        turnLabel = new Label("Red's turn");
        circles = new Circle[Board.ROWS][Board.COLS];
    }

    // Method to get the game board GUI
    public BorderPane getBoardGUI() {
        BorderPane borderPane = new BorderPane();
        gridPane = new GridPane();

        // Initialize the board GUI with empty circles
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                Circle circle = createCircle(row, col);
                circles[row][col] = circle; // Store the circle in the 2D array
                gridPane.add(circle, col, row);
            }
        }

        borderPane.setCenter(gridPane);
        borderPane.setTop(turnLabel);
        return borderPane;
    }

    // Method to create a circle representing a cell on the board
    private Circle createCircle(int row, int col) {
        Circle circle = new Circle(35, Color.WHITE); // Adjusted size to fit within the cell
        circle.setStroke(Color.BLACK); // the circumference of th cirlce to black 
        circle.setOnMouseClicked(event -> {
            if (currentPlayer == 'R' || !isSinglePlayer) {
                makeMove(col);
            }
        });
        return circle;
    }

    // Method to handle a move by the current player
    private void makeMove(int col) {
        int row = board.getEmptyRow(col);
        if (row != -1) { // Check if the move is valid
            if (board.makeMove(col, currentPlayer)) {
                updateBoardGUI(); // Update the board GUI
                if (board.checkWin(currentPlayer)) {
                    showAlert(currentPlayer + " wins!");
                    if (currentPlayer == 'R') {
                        scoreKeeper.incrementRedWins();
                    } else {
                        scoreKeeper.incrementBlueWins();
                    }
                    scoreKeeper.saveScores();
                    resetGame(); // Reset the game after a win
                } else if (board.isBoardFull()) {
                    showAlert("It's a tie!");
                    scoreKeeper.incrementTies();
                    scoreKeeper.saveScores();
                    resetGame(); // Reset the game after a tie
                } else {
                    switchPlayer();
                    updateTurnLabel();
                    if (isSinglePlayer && currentPlayer == 'B') {
                        makeRandomMove();
                    }
                }
            } else {
                showAlert("Invalid move. Try again.");
            }
        }
    }

    // Method to make a random move for the computer
    private void makeRandomMove() {
        Random random = new Random();
        int col;
        do {
            col = random.nextInt(Board.COLS);
        } while (board.getEmptyRow(col) == -1);

        makeMove(col);
    }

    // Method to update the board GUI after a move
    private void updateBoardGUI() {
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                Circle circle = circles[row][col];
                if (circle != null) {
                    if (board.getBoard()[row][col] == 'R') {
                        circle.setFill(Color.RED);
                    } else if (board.getBoard()[row][col] == 'B') {
                        circle.setFill(Color.BLUE);
                    } else {
                        circle.setFill(Color.WHITE);
                    }
                }
            }
        }
    }

    // Method to show an alert dialog
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to switch the current player
    public void switchPlayer() {
        if (currentPlayer == 'R') {
            currentPlayer = 'B'; // Switch to Blue
        } else {
            currentPlayer = 'R'; // Switch to Red
        }
    }

    // Method to update the turn label at the top left 
    // To notify 
    private void updateTurnLabel() {
        if (currentPlayer == 'R') {
            turnLabel.setText("Red's turn");
        } else {
            turnLabel.setText("Blue's turn");
        }
    }

    // Method to reset the game
    private void resetGame() {
        board.initializeBoard(); // Reset the board
        currentPlayer = 'R'; // Set the current player to Red
        updateBoardGUI(); // Update the board to show the reset board
        updateTurnLabel(); // Update the turn label at the top

        // Make all circles white to reset the board
        for (int row = 0; row < Board.ROWS; row++) {
            for (int col = 0; col < Board.COLS; col++) {
                Circle circle = circles[row][col];
                if (circle != null) {
                    circle.setFill(Color.WHITE);
                }
            }
        }
    }
}
