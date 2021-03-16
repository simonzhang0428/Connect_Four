package hw4;
import java.util.Random;
import java.util.Scanner;


public class ConsoleCF extends CFGame {
    private CFPlayer p1;
    private CFPlayer p2;
    private int turn;

    public ConsoleCF(CFPlayer ai) {
        super();
        Random rand = new Random();

        HumanPlayer human = new HumanPlayer();
        int turn_choice = rand.nextInt(2);
        turn = 0;

        if (turn_choice == 0) {
            this.p1 = ai;
            this.p2 = human;
        } else {
            this.p2 = ai;
            this.p1 = human;
        }

    }

    public ConsoleCF(CFPlayer ai1, CFPlayer ai2) {
        super();
        Random rand = new Random();
        int turn_choice = rand.nextInt(2);
        turn = 0;

        //randomize
        if (turn_choice == 0) {
            this.p1 = ai1;
            this.p2 = ai2;
        } else {
            this.p2 = ai1;
            this.p1 = ai2;
        }
    }
    //play til game over
    public void playOut() {


        while (!super.isGameOver()) {
            turn++;
            //don't take dis out doe
            if (turn % 2 == 1) {
                this.play(p1.nextMove(this));
            } else {
                this.play(p2.nextMove(this));
            }
            //above ends here


        }
    }

    public String getWinner() {
        if (super.winner() == 1) {
            return (p1.getName());
        }
        else if (super.winner() == -1) {
            return (p2.getName());
        }
        return "draw";
    }

    private class HumanPlayer implements CFPlayer {

        @Override
        public String getName() {
            return "Human Player";
        }

        @Override
        public int nextMove(CFGame g) {

            int[][] game2 = new int[nRows][nCols];
            game2 = g.getState();

            Scanner reader = new Scanner (System.in);

            while (6 != 7) {
                for (int i = 0; i < nRows; i++) {
                    for (int j = 0; j < nCols; j++) {
                        if (game2[i][j] == -1)
                            System.out.print('X');
                        else if (game2[i][j] == 0)
                            System.out.print('0');
                        else if (game2[i][j] == 1)
                            System.out.print('R');
                    }
                    System.out.print("\n");
                }


                System.out.println("Make a move.");
                int player_turn = reader.nextInt();

                if (player_turn > 0 && player_turn < nRows) {
                    for (int i = 0; i < nRows; i++) {
                        if (game2[i][player_turn - 1] == 0)
                            return player_turn;
                    }
                    //prints if out of vertical
                    System.out.println("Please enter a valid turn.");
                } else //this else statement prints this if the thing is out of horizontal conditions
                    System.out.println("Please enter a valid turn.");
            }


        }

    }


}