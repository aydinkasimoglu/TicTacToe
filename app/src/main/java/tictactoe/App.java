package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class App {
    private static int turn = 1;

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        final JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // Label indicating whose turn it is
        final JLabel lblTurn = new JLabel("X's turn");
        lblTurn.setBorder(BorderFactory.createEmptyBorder(30, 250, 0, 0));
        lblTurn.setFont(new Font("Serif", Font.PLAIN, 30));

        // Panel containing the 3x3 grid of buttons
        JPanel pnlButton = new JPanel();
        pnlButton.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        pnlButton.setLayout(new GridLayout(3, 3));

        // Create a 3x3 grid of buttons
        final JButton[][] buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Check if the button's text is empty
                        if (((JButton) e.getSource()).getText() == "") {
                            // On click, change the text of the button to X or O depending on whose turn it
                            // is. Also change the label to whose turn it is
                            if (turn % 2 == 1) {
                                ((JButton) e.getSource()).setText("X");
                                lblTurn.setText("O's turn");

                            } else {
                                ((JButton) e.getSource()).setText("O");
                                lblTurn.setText("X's turn");
                            }
                            // Check if there is a winner or if the game is a draw after each turn
                            checkWin(buttons, frame);
                            if (turn == 9) {
                                win("No one");
                            }
                            turn++;
                        }
                    }
                });

                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setFont(new Font("Serif", Font.PLAIN, 30));
                pnlButton.add(buttons[i][j]);
            }
        }

        // Add all components to the frame
        frame.add(lblTurn, BorderLayout.NORTH);
        frame.add(pnlButton, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void checkWin(JButton[][] buttons, JFrame frame) {
        // Check if there are 3 X's or O's in a row
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText() == buttons[i][1].getText() && buttons[i][0].getText() == buttons[i][2].getText()
                    && buttons[i][0].getText() != "") {
                // Create a new frame to display the winner
                frame.dispose();
                win(buttons[i][0].getText());
            }
        }

        // Check if there are 3 X's or O's in a column
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText() == buttons[1][i].getText() && buttons[0][i].getText() == buttons[2][i].getText()
                    && buttons[0][i].getText() != "") {
                frame.dispose();
                win(buttons[0][i].getText());
            }
        }

        // Check if there are 3 X's or O's in a diagonal
        if (buttons[0][0].getText() == buttons[1][1].getText() && buttons[0][0].getText() == buttons[2][2].getText()
                && buttons[0][0].getText() != "") {
            frame.dispose();
            win(buttons[0][0].getText());
        }

        if (buttons[0][2].getText() == buttons[1][1].getText() && buttons[0][2].getText() == buttons[2][0].getText()
                && buttons[0][2].getText() != "") {
            frame.dispose();
            win(buttons[0][2].getText());
        }
    }

    private static void win(String winner) {
        JFrame frame = new JFrame("Winner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JLabel lblWinner = new JLabel(winner + " wins!");
        lblWinner.setFont(new Font("Serif", Font.PLAIN, 30));
        lblWinner.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 0));

        JButton btnRestart = new JButton("Restart");
        btnRestart.setFont(new Font("Serif", Font.PLAIN, 14));
        btnRestart.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Restart the game
                frame.dispose();
                turn = 1;
                startGame();
            }
        });

        frame.add(lblWinner, BorderLayout.CENTER);
        frame.add(btnRestart, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}