package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dserov on 22/04/16.
 */
public class ViewController extends JFrame {

    private Game game;

    private BoardPanel boardPanel;
    private BottomPanel bottomPanel;
    private StatusPanel statusPanel;

    public ViewController(Game game) {
        this.game = game;
    }

    public void init() {
        try {
            SwingUtilities.invokeAndWait(this::createAndShowGUI);
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
        boardPanel.load();
        bottomPanel.load();
        statusPanel.update();
    }

    public void createAndShowGUI() {
        setTitle("Scrabble v0.1");
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(7, 89, 79));
        GridBagConstraints gc = new GridBagConstraints();
        boardPanel = new BoardPanel(game);
        bottomPanel = new BottomPanel(game);
        statusPanel = new StatusPanel(game);

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;

        gc.insets = new Insets(5, 0, 5, 0);
        add(statusPanel, gc);

        gc.insets = new Insets(0,0,0,0);
        gc.gridy = 1;
        add(boardPanel, gc);

        gc.gridy = 2;
        add(bottomPanel, gc);


        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        toFront();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setMinimumSize(new Dimension(600, 720));
    }
}
