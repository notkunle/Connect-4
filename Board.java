public class Board {
    public static final int ROWS = 6; // Number of rows in the board
    public static final int COLS = 7; // Number of columns in the board
    private char[][] board; // 2D array representing the board

    public Board() {
        board = new char[ROWS][COLS];
        initializeBoard(); // Initialize the board with empty spaces
    }

    // Method to initialize the board with empty spaces
    public void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = ' ';
            }
        }
    }

    // Method to make a move on the board
    public boolean makeMove(int col, char currentPlayer) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    // Method to get the first empty row in a column
    public int getEmptyRow(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                return row;
            }
        }
        return -1;
    }

    // Method to check if the board is full
    public boolean isBoardFull() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == ' ') {
                return false;
            }
        }
        return true;
    }

    // Method to check if a player has won
    public boolean checkWin(char currentPlayer) {
        // Check horizontally
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == currentPlayer &&
                        board[row][col + 1] == currentPlayer &&
                        board[row][col + 2] == currentPlayer &&
                        board[row][col + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        // Check vertically
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == currentPlayer &&
                        board[row + 1][col] == currentPlayer &&
                        board[row + 2][col] == currentPlayer &&
                        board[row + 3][col] == currentPlayer) {
                    return true;
                }
            }
        }

        // Check diagonally (top left to bottom right)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (board[row][col] == currentPlayer &&
                        board[row + 1][col + 1] == currentPlayer &&
                        board[row + 2][col + 2] == currentPlayer &&
                        board[row + 3][col + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        // Check diagonally (top right to bottom left)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 3; col < COLS; col++) { // col has to start at three so thats baiscally the centre or else there wont be the top right to bottom left
                if (board[row][col] == currentPlayer &&
                        board[row + 1][col - 1] == currentPlayer &&
                        board[row + 2][col - 2] == currentPlayer &&
                        board[row + 3][col - 3] == currentPlayer) {
                    return true;
                }
            }
        }

        return false;
    }

    // Method to get the board array
    public char[][] getBoard() {
        return board;
    }
}
