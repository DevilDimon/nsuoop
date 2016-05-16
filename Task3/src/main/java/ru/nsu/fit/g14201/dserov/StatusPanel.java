package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dserov on 03/05/16.
 */
public class StatusPanel extends JPanel {
    private Game game;

    private JLabel[] scoreLabels;
    private JLabel turnInfo;

    public StatusPanel(Game game) {
        this.game = game;
        setBackground(new Color(7, 89, 79));
        setPreferredSize(new Dimension(570, 20));
        scoreLabels = new JLabel[2];
        scoreLabels[0] = new JLabel("", SwingConstants.LEFT);
        scoreLabels[1] = new JLabel("", SwingConstants.RIGHT);
        scoreLabels[0].setForeground(new Color(0xe5daa5, false));
        scoreLabels[1].setForeground(new Color(0xe5daa5, false));

        turnInfo = new JLabel("", SwingConstants.CENTER);
        turnInfo.setForeground(new Color(0xe5daa5, false));
        setLayout(new BorderLayout(80, 10));
        add(scoreLabels[0], BorderLayout.WEST);
        add(scoreLabels[1], BorderLayout.EAST);
        add(turnInfo, BorderLayout.CENTER);
    }

    public void update() {
        scoreLabels[0].setText("Player 1: " + game.getScore(0) + " points");
        scoreLabels[1].setText("Player 2: " + game.getScore(1) + " points");
        turnInfo.setText("Turn " + game.getTurn() +
                ". Player " + game.getCurPlayer() + "'s turn.");
    }
}
