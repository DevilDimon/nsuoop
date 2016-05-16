package ru.nsu.fit.g14201.dserov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by dserov on 14/05/16.
 */
public class GameOverDialog extends JDialog implements ActionListener {

    private boolean doRestart;

    private JLabel title;
    private JLabel[] player;
    private JLabel[] score;
    private JButton restart;
    private JButton exit;

    public GameOverDialog(JFrame parent) {
        super(parent, "Scrabble", true);
        doRestart = true;
        title = new JLabel("", SwingConstants.CENTER);
        player = new JLabel[2];
        player[0] = new JLabel("Player 1", SwingConstants.CENTER);
        player[1] = new JLabel("Player 2", SwingConstants.CENTER);
        score = new JLabel[2];
        score[0] = new JLabel("", SwingConstants.CENTER);
        score[1] = new JLabel("", SwingConstants.CENTER);
        restart = new JButton("Restart");
        restart.addActionListener(this);
        exit = new JButton("Exit");
        exit.addActionListener(this);

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.weightx = 2;
        gc.insets = new Insets(0, 0, 10, 0);
        gc.anchor = GridBagConstraints.CENTER;
        gc.ipady = 10;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(title, gc);

        gc.gridy = 1;
        gc.weightx = 1;
        gc.gridwidth = 1;
        gc.insets = new Insets(0, 10, 0, 10);
        gc.anchor = GridBagConstraints.LINE_END;
        add(player[0], gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(player[1], gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(score[0], gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(score[1], gc);

        gc.gridy = 3;
        gc.gridx = 0;
        add(restart, gc);

        gc.gridx = 1;
        add(exit, gc);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                doRestart = false;
            }
        });
        setSize(400, 200);
        setLocationRelativeTo(parent);
    }

    public void showWithScores(int score1, int score2) {
        if (score1 == score2) {
            title.setText("It's a tie! Final scores:");
        } else {
            title.setText("Player " + ((score1 > score2) ? "1" : "2") + " won! Final scores:");
        }
        score[0].setText("" + score1);
        score[1].setText("" + score2);
        setVisible(true);
    }

    public boolean isRestart() {
        return doRestart;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if (b == restart) {
            doRestart = true;
        } else if (b == exit){
            doRestart = false;
        }
        setVisible(false);
    }
}
