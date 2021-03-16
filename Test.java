import java.util.Scanner;

import hw4.*;


public class Test {
    public static void main(String[] args) {
        System.out.print("Enter the mode: ");
        Scanner reader = new Scanner (System.in);
        int gameMode = reader.nextInt();

        if (gameMode==1) {
            new GUICF(new RandomAI(), new SimonAI());
        } else if (gameMode==2) {
            CFPlayer ai1 = new SimonAI();
            CFPlayer ai2 = new RandomAI();
            int n = 10000;
            int winCount = 0;
            for (int i =0; i < n ; i++) {
                ConsoleCF game = new ConsoleCF(ai1, ai2);
                game.playOut();
                if(game.getWinner() == ai1.getName())
                    winCount++;
            }
            System.out.println(((double) winCount)/n);
        } else {
            ConsoleCF game = new ConsoleCF(new SimonAI());
            game.playOut();
            System.out.println(game.getWinner() + " has won.");
        }
    }
}