package hw4;

import java.util.Random;

public class RandomAI implements CFPlayer {

    private int nRows = 6;
    private int nCols = 7;

    @Override
    public int nextMove(CFGame g){
        long seed = java.lang.System.currentTimeMillis();
        Random rand = new Random(seed);
        int[][] g2 = new int[nRows][nCols];

        g2 = g.getState();

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
        return "Random Player";
    }

}