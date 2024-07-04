import java.util.Objects;
import java.util.Scanner;

public class Connect {

    /** Global variables */
    private String[][] board;
    private int winnerPlayer;
    private int playerTurn;

    /** Constructor */

    public Connect() {
        winnerPlayer = 0;
        playerTurn = 1;
        board = new String[6][7];
        newBoard();
        displayBoard();
    }

    /** Builds a new board */
    private void newBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = " - ";
            }
        }
    }

    /** Display the board */
    private void displayBoard() {
        System.out.println(" ");
        System.out.println("  <======= Connect 4 =====> ");
        System.out.println(" ");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /** Validates user input */
    private boolean validInput(String input) {
        return (Objects.equals(input, "1") ||
                Objects.equals(input, "2") ||
                Objects.equals(input, "3") ||
                Objects.equals(input, "4") ||
                Objects.equals(input, "5") ||
                Objects.equals(input, "6") ||
                Objects.equals(input, "7"));
    }

    /** Checks if a column is full */
    private boolean isColumnFull(int column) {
        return !board[0][column].equals(" - ");
    }

    /** Finds the next available slot in a column */
    private int getNextAvailableSlot(int column) {
        for (int row = 5; row >= 0; row--) {
            if (board[row][column].equals(" - ")) {
                return row;
            }
        }
        return -1; // Column is full, shouldn't reach here due to isColumnFull check
    }

    /** Swaps player turn */
    private void swapPlayerTurn() {
        playerTurn = (playerTurn == 1) ? 2 : 1;
    }

    /** Places a piece on the board */
    private void placePiece() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Player " + playerTurn + " - Please select a column to place your piece (1-7): ");
        String input = sc.nextLine().trim();
      
        while (!validInput(input) || isColumnFull(Integer.parseInt(input) - 1)) {
            System.out.println("Invalid input or column full. Please choose another column (1-7): ");
            input = sc.nextLine().trim();
        }
        int columnChoice = Integer.parseInt(input) - 1;
        int row = getNextAvailableSlot(columnChoice);
        board[row][columnChoice] = (playerTurn == 1) ? " X " : " 0 ";
        displayBoard();
        swapPlayerTurn();

        
    }

    /** Checks if the board is full */
    private boolean isBoardFull() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j].equals(" - ")) {
                    return false; // Found an empty slot, board is not full
                }
            }
        }
        return true; // All slots are filled
    }

    /** Checks for a vertical winner */
    private String checkVerticalWinner() {
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col].equals(board[row + 1][col]) &&
                    board[row][col].equals(board[row + 2][col]) &&
                    board[row][col].equals(board[row + 3][col]) &&
                    !board[row][col].equals(" - ")) {
                    return board[row][col];
                }
            }
        }
        return null; // No vertical winner
    }

    /** Checks for a horizontal winner */
    private String checkHorizontalWinner() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col <= 3; col++) {
                if (board[row][col].equals(board[row][col + 1]) &&
                    board[row][col].equals(board[row][col + 2]) &&
                    board[row][col].equals(board[row][col + 3]) &&
                    !board[row][col].equals(" - ")) {
                    return board[row][col];
                }
            }
        }
        return null; // No horizontal winner
    }

    /** Checks for a left diagonal winner */
    private String checkLeftDiagonalWinner() {
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 3; col++) {
                if (board[row][col].equals(board[row + 1][col + 1]) &&
                    board[row][col].equals(board[row + 2][col + 2]) &&
                    board[row][col].equals(board[row + 3][col + 3]) &&
                    !board[row][col].equals(" - ")) {
                    return board[row][col];
                }
            }
        }
        return null; // No left diagonal winner
    }

    /** Checks for a right diagonal winner */
    private String checkRightDiagonalWinner() {
        for (int row = 0; row <= 2; row++) {
            for (int col = 3; col < 7; col++) {
                if (board[row][col].equals(board[row + 1][col - 1]) &&
                    board[row][col].equals(board[row + 2][col - 2]) &&
                    board[row][col].equals(board[row + 3][col - 3]) &&
                    !board[row][col].equals(" - ")) {
                    return board[row][col];
                }
            }
        }
        return null; // No right diagonal winner
    }

    /** Checks for a winner */
    private int checkForWinner() {
        String winner = null;
        if ((winner = checkVerticalWinner()) != null ||
            (winner = checkHorizontalWinner()) != null ||
            (winner = checkLeftDiagonalWinner()) != null ||
            (winner = checkRightDiagonalWinner()) != null) {
            return (winner.equals(" X ")) ? 1 : 2;
        }
        return 0; // No winner
    }

    /** Checks for a draw */
    private boolean checkForDraw() {
        return isBoardFull() && checkForWinner() == 0;
    }

    /** Displays the winner */
    private void showWinner() {
        System.out.println(" ");
        System.out.println(" *************************** ");
        System.out.println("                             ");
        System.out.println("         Player " + winnerPlayer + " Wins!!!! ");
        System.out.println("   *                           *   ");
        System.out.println("        *   \\0/          *         ");
        System.out.println("   *                           *    ");
        System.out.println("      *       |    *      *         ");
        System.out.println("  *          /  \\    *            ");
        System.out.println(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
        System.out.println("*************************************");
    }
    /** Main method to start the game */
    public static void main(String[] args) {
        Connect c4 = new Connect();
        c4.playGame();
    }
    
    /** Plays one round of Connect 4 */
    public void playGame() {
        while (winnerPlayer == 0) {
            winnerPlayer = checkForWinner();
            if (winnerPlayer != 0) {
                showWinner();
                break;
            } else if (checkForDraw()) {
                System.out.println("It's a draw!");
                break;
            }
            placePiece();
        }
        

        
    }
}

