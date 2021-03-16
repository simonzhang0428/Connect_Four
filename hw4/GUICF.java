package hw4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GUICF extends CFGame{
    private GameBoard this_board;
    private int turn_no = 1;
    private CFPlayer p1;
    private CFPlayer p2;
    private final int nRows = 6;
    private final int nCols = 7;

    //person vs AI, random start

    public GUICF(CFPlayer ai) {
        super();
        this_board = new GameBoard();
        Random rand = new Random();
        int turn_choice = rand.nextInt(2);
        p1 = ai;
        p2 = ai;

        //button array for buttons. then implement every one
        JButton b1 = new JButton("\u2193");
        JButton b2 = new JButton("\u2193");
        JButton b3 = new JButton("\u2193");
        JButton b4 = new JButton("\u2193");
        JButton b5 = new JButton("\u2193");
        JButton b6 = new JButton("\u2193");
        JButton b7 = new JButton("\u2193");

        b1.addActionListener(new b1_played());
        b2.addActionListener(new b2_played());
        b3.addActionListener(new b3_played());
        b4.addActionListener(new b4_played());
        b5.addActionListener(new b5_played());
        b6.addActionListener(new b6_played());
        b7.addActionListener(new b7_played());


        this_board.frame = new JFrame("Connect Four");

        this_board.panel = new JPanel(new BorderLayout());
        this_board.frame.setSize(400,300);
        this_board.labels = new JLabel[nRows][nCols];

        this_board.pane = this_board.frame.getContentPane();
        this_board.panel.setLayout(new GridLayout(0,7));

        for (int i = nRows; i >= 0 ; i--) {
            for (int j = 0; j < nCols; j++) {
                if (i == nRows) {
                    this_board.panel.add(b1);
                    this_board.panel.add(b2);
                    this_board.panel.add(b3);
                    this_board.panel.add(b4);
                    this_board.panel.add(b5);
                    this_board.panel.add(b6);
                    this_board.panel.add(b7);
                } else {
                    this_board.labels[i][j] = new JLabel(" ");
                    this_board.pane.add(this_board.panel, BorderLayout.SOUTH);
                    this_board.labels[i][j].setBackground(Color.white);
                    this_board.labels[i][j].setOpaque(true);
                    this_board.labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                    this_board.panel.add(this_board.labels[i][j]);
                }
            }

        }
        if (turn_choice == 0) {
            this.playGUI(p1.nextMove(this));
        }

        this_board.frame.setVisible(true);
        this_board.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class b1_played implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (GUICF.this.playGUI(1))
                playGUI(GUICF.this.p1.nextMove(GUICF.this));
        }
    }
    class b2_played implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (GUICF.this.playGUI(2))
                playGUI(GUICF.this.p1.nextMove(GUICF.this));
        }
    }
    class b3_played implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (GUICF.this.playGUI(3))
                playGUI(GUICF.this.p1.nextMove(GUICF.this));
        }
    }
    class b4_played implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (GUICF.this.playGUI(4))
                playGUI(GUICF.this.p1.nextMove(GUICF.this));
        }
    }
    class b5_played implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (GUICF.this.playGUI(5))
                playGUI(GUICF.this.p1.nextMove(GUICF.this));
        }
    }
    class b6_played implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (GUICF.this.playGUI(6))
                playGUI(GUICF.this.p1.nextMove(GUICF.this));
        }
    }
    class b7_played implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (GUICF.this.playGUI(7))
                playGUI(GUICF.this.p1.nextMove(GUICF.this));
        }
    }

    //ai vs AI game, random start
    public GUICF(CFPlayer ai1, CFPlayer ai2) {
        super();
        this_board = new GameBoard();
        JButton ai_play = new JButton("Play");

        this_board.frame.getContentPane().add(ai_play,BorderLayout.NORTH);
        this_board.frame.setVisible(true);
        Random rand = new Random();
        int turn_choice = rand.nextInt(2);

        if (turn_choice == 1) {
            p1 = ai1;
            p2 = ai2;
        } else {
            p1 = ai2;
            p2 = ai1;
        }

        ai_play.addActionListener(new ai_played());
    }

    class ai_played implements ActionListener {
        public void actionPerformed (ActionEvent event) {
            if (GUICF.this.turn_no % 2 == 1) {
                playGUI(GUICF.this.p1.nextMove(GUICF.this));
            } else
                playGUI(GUICF.this.p2.nextMove(GUICF.this));
        }
    }

    private boolean playGUI(int c) {
        //takes a turn indexed at 1 and plays it if possible, returns false if not

        c -= 1;
        int[][] game2;
        game2 = this.getState();

        for (int i = 0; i < nRows; i++) {
            if (game2[i][c] == 0 && turn_no % 2 == 1) {
                this_board.paint(i,c,1);
                this.play(c + 1 ); //cause CF.game is 1 index
                turn_no++;
                //check if that turn ended the game. if so take action
                if (this.isGameOver() && winner() == 1) {
                    //this_board.frame.dispatchEvent(new WindowEvent(this_board.frame, WindowEvent.WINDOW_CLOSING));
                    //above line closes the window
                    this_board.frame = new JFrame("Victory");
                    this_board.frame.getContentPane().add(new JLabel("Red wins!"), BorderLayout.CENTER);
                    this_board.frame.setSize(300,200);
                    this_board.frame.setVisible(true);

                } else if (this.isGameOver() && winner() == -1){

                    this_board.frame = new JFrame("Victory");
                    this_board.frame.getContentPane().add(new JLabel("Black wins!"), BorderLayout.CENTER);

                    this_board.frame.setSize(300,200);
                    this_board.frame.setVisible(true);
                } else if (this.isGameOver() && winner() == 0) {
                    this_board.frame = new JFrame("Draw");
                    this_board.frame.getContentPane().add(new JLabel("It's a tie."), BorderLayout.CENTER);
                    this_board.frame.setSize(300,200);
                    this_board.frame.setVisible(true);
                }
                return true;
            } else if (game2[i][c] == 0 && turn_no % 2 == 0) {
                this_board.paint(i,c,-1);
                this.play(c+1);
                turn_no++;
                //check if that turn ended the game. if so take action
                if (this.isGameOver() && winner() == 1) {

                    this_board.frame = new JFrame("Victory");
                    this_board.frame.getContentPane().add(new JLabel("Red wins!"), BorderLayout.CENTER);

                    this_board.frame.setSize(300,200);
                    this_board.frame.setVisible(true);

                } else if (this.isGameOver() && winner() == -1){
                    this_board.frame = new JFrame("Victory");
                    this_board.frame.getContentPane().add(new JLabel("Black wins!"), BorderLayout.CENTER);

                    this_board.frame.setSize(300,200);
                    this_board.frame.setVisible(true);
                } else if (this.isGameOver() && winner() == 0) {
                    this_board.frame = new JFrame("Draw");
                    this_board.frame.getContentPane().add(new JLabel("It's a tie."), BorderLayout.CENTER);
                    this_board.frame.setSize(300,200);
                    this_board.frame.setVisible(true);       }

                return true;
            }
        }

        return false;
    }


    //GameBoard implementation
    private class GameBoard extends javax.swing.JPanel {
        JLabel[][] labels;
        JFrame frame;
        Container pane;
        JPanel panel;
        private GameBoard() {
            frame = new JFrame("Connect Four");
            panel = new JPanel(new BorderLayout());
            frame.setSize(300,200);
            labels = new JLabel[nRows][nCols];
            //panel.add(new JLabel("Label text"));
            pane = frame.getContentPane();
            panel.setLayout(new GridLayout(0,7));

            for (int i = nRows - 1; i >= 0 ; i--) {
                for (int j = 0; j < nCols; j++) {
                    labels[i][j] = new JLabel(" ");
                    pane.add(panel, BorderLayout.SOUTH);
                    labels[i][j].setBackground(Color.white);
                    labels[i][j].setOpaque(true);
                    labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                    panel.add(labels[i][j]);
                }

            }
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        private void paint(int x, int y, int color) {
            //takes the 0 indexed coordinate of the grid and changes it to the right color
            if (color == 1)
                labels[x][y].setBackground(Color.red);
            else if (color == -1)
                labels[x][y].setBackground(Color.black);
        }
    }
    //END GameBoard Implementation
    public static void main(String[] args){
        RandomAI ai1 = new RandomAI();
        SimonAI ai2 = new SimonAI();
        GUICF game = new GUICF(ai1, ai2);
    }

}