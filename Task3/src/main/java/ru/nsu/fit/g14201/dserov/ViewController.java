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
    private BottomPanel bottomPanel;

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
    }

    public void createAndShowGUI() {
        setTitle("Scrabble v0.1");
//        setSize(750, 770);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(7, 89, 79));
        GridBagConstraints gc = new GridBagConstraints();
        boardPanel = new BoardPanel(game);
        bottomPanel = new BottomPanel(game);

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        add(boardPanel, gc);

        gc.gridy = 1;
        add(bottomPanel, gc);


        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        toFront();
        pack();
        setVisible(true);
    }
}
