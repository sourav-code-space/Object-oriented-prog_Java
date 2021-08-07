package ConnectFourGame;

/**
 * A basic implementation of the Connect Four game.
 */
public class ConnectFour {
    /** the number of rows */
    public final static int ROWS = 6;
    /** the number of columns */
    public final static int COLS = 7;
    /** how big a line one needs to win */
    public final static int WIN_LEN = 4;


    /**
     * Used to indicate a move that has been made on the board.
     */
    public enum Move {
        PLAYER_ONE('X'),
        PLAYER_TWO('O'),
        NONE('.');

        private char symbol;

        private Move(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }
    }

    /**
     * The number of rows in the board.
     */
    private int rows;

    /**
     * The number of columns in the board.
     */
    private int cols;

    /**
     * The board.
     */
    private Move[][] board;

    /**
     * Used to keep track of which player's turn it is; 0 for player 1, and 1
     * for player 2.
     */
    private int turn;

    /**
     *  The last column a piece was placed.  Used for win checking.
     */
    private int lastCol;

    /**
     * The row the last piece was placed.  Used for win checking.
     */
    private int lastRow;

    /**
     * Creates a Connect Four game using a board with the standard number of
     * rows (6) and columns (7).
     */
    public ConnectFour() {
        this(ROWS, COLS);
    }

    /**
     * Creates a Connect Four game using a board with the specified number of
     * rows and columns. Assumes that player 1 is the first to move.
     *
     * @param rows The number of rows in the board.
     * @param cols The number of columns in the board.
     */
    public ConnectFour(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        board = new Move[rows][cols];
        for(int row=0; row<rows; row++) {
            for(int col=0; col < cols; col++) {
                board[row][col] = Move.NONE;
            }
        }

        turn = 0;
    }
    public int getTurn(){
        return turn;
    }
    /**
     * Makes a move for the player whose turn it is. If the move is successful,
     * play automatically switches to the other player's turn.
     *
     * @param column The column in which the player is moving.
     *
     * @throws ConnectFourException If the move is invalid for any reason.
     */
    public void makeMove(int column) throws ConnectFourException {
        // TODO YOUR CODE HERE
        if(turn==0){
            if(lastRow >= 0) {
                for(int i=rows-1;i>=0;i--){
                    if(board[i][column]==Move.NONE) {
                        board[i][column] = Move.PLAYER_ONE;
                        lastRow=i;
                        break;
                    }
                }
                turn=1;
            }
            else {
                throw new OutOfBoundsException(OutOfBoundsException.ROW_PAST_EDGE);
            }
        }
        else{
            if(lastRow >= 0 &&column>=0 && column < COLS) {
                for(int i=rows-1;i>=0;i--){
                    if(board[i][column]==Move.NONE) {
                        board[i][column] = Move.PLAYER_TWO;
                        lastRow=i;
                        //System.out.println("LastRow chi value"+lastRow);
                        break;
                    }
                }
                turn=0;
            }
            else {
                throw new OutOfBoundsException(OutOfBoundsException.ROW_PAST_EDGE);
            }
        }
        lastCol=column;
    }
    /**
     * Look over the entire board for any N-in-a-row situations.
     * (By N we mean {@link #WIN_LEN}.)
     * @return true if one of the players has an N-in-a-row situation.
     */
    public boolean hasWonGame() {
        //TODO YOUR CODE HERE
        boolean flag = false;
        abc: for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // horizontal check for 4 times X
                boolean condition1 =
                        (board[i][j].equals(Move.PLAYER_ONE)) && ((j + 1) < board[i].length && board[i][j + 1].equals(Move.PLAYER_ONE))
                        && ((j + 2) < board[i].length && board[i][j + 2].equals(Move.PLAYER_ONE))
                        && ((j + 3) < board[i].length && board[i][j + 3].equals(Move.PLAYER_ONE));
                // vertical check for 4 times X
                boolean condition2 = (board[i][j].equals(Move.PLAYER_ONE)) && ((i + 1) < board.length && board[i + 1][j].equals(Move.PLAYER_ONE))
                        && ((i + 2) < board.length && board[i + 2][j].equals(Move.PLAYER_ONE))
                        && ((i + 3) < board.length && board[i + 3][j].equals(Move.PLAYER_ONE));
                // horizontal check for 4 times 0
                boolean condition3 = (board[i][j].equals(Move.PLAYER_TWO)) && ((j + 1) < board[i].length && board[i][j + 1].equals(Move.PLAYER_TWO))
                        && ((j + 2) < board[i].length && board[i][j + 2].equals(Move.PLAYER_TWO))
                        && ((j + 3) < board[i].length && board[i][j + 3].equals(Move.PLAYER_TWO));
                // vertical check for 4 times 0
                boolean condition4 = (board[i][j].equals(Move.PLAYER_TWO)) && ((i + 1) < board.length && board[i + 1][j].equals(Move.PLAYER_TWO))
                        && ((i + 2) < board.length && board[i + 2][j].equals(Move.PLAYER_TWO))
                        && ((i + 3) < board.length && board[i + 3][j].equals(Move.PLAYER_TWO));
                //diagonal check for 4  times X
                boolean condition5 = (board[i][j].equals(Move.PLAYER_ONE))
                        && ((i + 1) < board.length && (j + 1) < board[i].length && board[i + 1][j + 1].equals(Move.PLAYER_ONE))
                        && ((i + 2) < board.length && (j + 2) < board[i].length && board[i + 2][j + 2].equals(Move.PLAYER_ONE))
                        && ((i + 3) < board.length && (j + 3) < board[i].length && board[i + 3][j + 3].equals(Move.PLAYER_ONE));
                //diagonal check for 4 times X
                boolean condition6 = (board[i][j].equals(Move.PLAYER_ONE))
                        && ((i + 1) < board.length && (j - 1) < board[i].length && board[i + 1][j - 1].equals(Move.PLAYER_ONE))
                        && (i + 2 < board.length && j - 2 < board[i].length && board[i + 2][j - 2].equals(Move.PLAYER_ONE))
                        && (i + 3 < board.length && j - 3 < board[i].length && board[i + 3][j - 3].equals(Move.PLAYER_ONE));
                //diagonal check for 4 times 0
                boolean condition7 = (board[i][j].equals(Move.PLAYER_TWO))
                        && ((i + 1) < board.length && (j + 1) < board[i].length && board[i + 1][j + 1].equals(Move.PLAYER_TWO))
                        && ((i + 2) < board.length && (j + 2) < board[i].length && board[i + 2][j + 2].equals(Move.PLAYER_TWO))
                        && ((i + 3) < board.length && (j + 3) < board[i].length && board[i + 3][j + 3].equals(Move.PLAYER_TWO));
                //diagonal check for 4 times 0
                boolean condition8 = (board[i][j].equals("0"))
                        && ((i + 1) < board.length && (j - 1) < board[i].length && board[i + 1][j - 1].equals(Move.PLAYER_TWO))
                        && (i + 2 < board.length && j - 2 < board[i].length && board[i + 2][j - 2].equals(Move.PLAYER_TWO))
                        && (i + 3 < board.length && j - 3 < board[i].length && board[i + 3][j - 3].equals(Move.PLAYER_TWO));
                // TODO diagonal checking
                if (condition1 || condition2 || condition3 || condition4 || condition5 || condition6 || condition7
                        || condition8) {
                    flag = true;
                    break abc;
                } else {
                    flag = false;
                }

            }
        }
        return flag;
    }

    /**
     * Checks to see if the game is tied - no NONE moves left in board.  This
     * is called after hasGameWon.
     *
     * @return whether game is tied or not
     */
    public boolean hasTiedGame() {
        //TODO YOUR CODE HERE
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLS;j++){
                if(board[i][j]==Move.NONE)
                    return false;
            }
        }
        return true;
    }

    /**
     * Returns a {@link String} representation of the board, suitable for
     * printing.
     *
     * @return A {@link String} representation of the board.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(int r=0; r<rows; r++) {
            for(int c=0; c<cols; c++) {
                builder.append('[');
                builder.append(board[r][c].getSymbol());
                builder.append(']');
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
