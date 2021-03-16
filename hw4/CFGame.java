package hw4;

public class CFGame {
    //state[i][j]= 0 means the i,j slot is empty
    //state[i][j]= 1 means the i,j slot has red
    //state[i][j]=-1 means the i,j slot has black
    private final int[][] state;
    private int intWinner = 0;
    private boolean isRedTurn;
    public static int nRows = 6;
    public static int nCols = 7;

    {
        state = new int[nRows][nCols];
        for (int i=0; i<nRows; i++)
            for (int j=0; j<nCols; j++)
                state[i][j] = 0;
        isRedTurn = true; //red goes first
    }

    public int[][] getState() {
        int[][] ret_arr = new int[nRows][nCols];
        for (int i=0; i<nRows; i++)
            for (int j=0; j<nCols; j++)
                ret_arr[i][j] = state[i][j];
        return ret_arr;
    }

    public boolean isRedTurn() {
        return isRedTurn;
    }

    public boolean play(int c) {
        c -= 1; //just to make c now zero-indexed.
        if (c > nCols - 1)
            return false;

        int temp_row = 0;
        //make a while loop that will check if the column c has any empty spots.
        //if not, return false
        while (temp_row < nRows && state[temp_row][c] != 0) {
            temp_row++;
        }
        if (temp_row == nRows) //that is, if temp_row is out of range
            return false;
        else if (isRedTurn) {
            state[temp_row][c] = 1;
            isRedTurn = false;
        } else {
            state[temp_row][c] = -1;
            isRedTurn = true;
        }

        return true;
    }

    public boolean isGameOver() {

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (this.state[i][j] != 0) {
                    //check for horizontal --> we only need to check just one case
                    if (j < nCols - 3) {
                        if (state[i][j] == state[i][j + 1] &&
                                state[i][j + 1] == state[i][j + 2] &&
                                state[i][j + 2] == state[i][j + 3]) {
                            intWinner = state[i][j];
                            return true;
                        }
                    }
                    //check for vertical
                    if (i < nRows - 3) {
                        if (state[i][j] == state[i + 1][j] &&
                                state[i + 1][j] == state[i+2][j] &&
                                state[i+2][j] == state[i + 3][j]) {
                            intWinner = state[i][j];
                            return true;
                        }
                    }
                    //check for diagonals
                    if (i < nRows - 3 && j < nCols - 3) {
                        if (state[i][j] == state[i+1][j+1] &&
                                state[i+1][j+1] == state[i+2][j+2] &&
                                state[i+2][j+2] == state[i+3][j+3]) {
                            intWinner = state[i][j];
                            return true;
                        }
                    }
                    if (i < nRows - 3 && j > 2){

                        if (state[i][j] == state[i+1][j-1] &&
                                state[i+1][j-1] == state[i+2][j-2] &&
                                state[i+2][j-2] == state[i+3][j-3]) {
                            intWinner = state[i][j];
                            return true;
                        }
                    }

                }
            }
        }

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (state[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    public int winner() {
        //only works if called immediately after isGameOver() returns true
        if (isGameOver())
            return intWinner;

        return 0;
    }



}