
public class TicTacToe {
    private final int ROW = 3;
    private final int COL = 3;
    private String[][] board = new String[ROW][COL];
    private String player = "X";
    private int moveCnt = 0;
    private final int MOVES_FOR_TIE = 9;

    public TicTacToe() {
        clearBoard();
    }

    private void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    public boolean makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = player;
            moveCnt++;
            if (isWin(player) || (moveCnt >= MOVES_FOR_TIE && isTie())) {
                return true; // Game Over
            }
            togglePlayer();
            return false; // Game continues
        }
        return false; // Invalid move
    }

    private void togglePlayer() {
        player = player.equals("X") ? "O" : "X";
    }

    public boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }


    public String getCurrentPlayer() {
        return player;
    }

    public boolean isGameOver() {
        return isWin(player) || (moveCnt == MOVES_FOR_TIE);
    }

    public String getWinner() {
        if (isWin(player)) {
            return player;
        } else if (moveCnt == MOVES_FOR_TIE) {
            return "Tie";
        }
        return "";
    }

    private boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private boolean isTie() {
        return moveCnt == MOVES_FOR_TIE;
    }
}
