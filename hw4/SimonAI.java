package hw4;

import java.util.Random;

public class SimonAI implements CFPlayer{

    private int nRows = 6;
    private int nCols = 7;

    //Some AI that's actually somewhat smart
    @Override
    public int nextMove(CFGame g) {
        long seed = java.lang.System.currentTimeMillis();
        Random rand = new Random(seed);
        int[][] g2 = new int[nRows][nCols];

        g2 = g.getState();

        //if center's not taken, take the center
        if (g2[0][3] == 0)
            return 4;
        if (g2[0][2] == 0)
            return 3;

        //checks to see if there are three in a row anywhere. If there are, plays the fourth in a row
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                //check horizontal--logic same as isGameOver in CFGame
                if (j < nCols - 3) {
                    if (java.lang.Math.abs(g2[i][j] + g2[i][j+1] + g2[i][j+2] + g2[i][j+3]) > 2 ) {
                        for (int k = j; k < j + 4; k++) {
                            if (i == 0 && g2[i][k] == 0) {
                                return k + 1;
                            } else if (i != 0 && g2[i-1][k] != 0) {
                                return k + 1;
                            }
                        }
                    }
                }

                if (i < nRows - 3 && j < nCols - 3) {
                    if (java.lang.Math.abs(g2[i][j] + g2[i+1][j+1] + g2[i+2][j+2] + g2[i+3][j+3]) > 2) {
                        for (int k = j; k < j + 4; k++) {
                            int l = i + k - j; //yup dis about to happen
                            if (g2[l][k] == 0)
                                return k + 1;
                        }
                    }
                }

                if (i < nRows - 3 && j > 2){
                    if (java.lang.Math.abs(g2[i][j] + g2[i+1][j-1] + g2[i+2][j-2] + g2[i+3][j-3]) > 2){
                        for (int k = j; k > j - 3; k--) {
                            int l = i + j - k;
                            if (g2[l][k] == 0)
                                return k + 1;
                        }
                    }
                }

                if (g2[i][j] != 0) {

                    //check vertical
                    if (i < nRows - 3) {
                        if (g2[i][j] == g2[i + 1][j] &&
                                g2[i + 1][j] == g2[i+2][j] && g2[i + 3][j] == 0)
                            return j + 1;
                    }
                }


            }
        }

        //if nothing, return random.
        while (true) {
            int turn = rand.nextInt(nCols);

            for (int i = 0; i < nRows; i++){
                if (g2[i][turn] == 0)
                    return turn + 1;
            }
        }

    }
    @Override
    public String getName() {
        return "Simon's AI";
    }

}