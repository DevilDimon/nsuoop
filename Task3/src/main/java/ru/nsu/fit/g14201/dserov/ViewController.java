package ru.nsu.fit.g14201.dserov;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by dserov on 22/04/16.
 */
public class ViewController extends JFrame {

    private Game game;

    private BoardPanel boardPanel;
    private RackPanel rackPanel;

    public ViewController(Game game) {
        this.game = game;
    }

    public void init() {
        try {
            SwingUtilities.invokeAndWait(this::createAndShowGUI);
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
        rackPanel.load();
        boardPanel.load();

    }

    public void createAndShowGUI() {
        setTitle("Scrabble v0.1");
//        setSize(750, 770);
        setLayout(new BorderLayout());
        boardPanel = new BoardPanel(game);
        rackPanel = new RackPanel(game);
        add(boardPanel, BorderLayout.CENTER);
        add(rackPanel, BorderLayout.SOUTH);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        toFront();
        pack();
        setVisible(true);
    }
}
