package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dserov on 03/05/16.
 */
public class BottomPanel extends JPanel {
    private Game game;

    private RackPanel rackPanel;
    private ControlPanel controlPanel;

    public BottomPanel(Game game) {
        rackPanel = new RackPanel(game);
        controlPanel = new ControlPanel(game);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBackground(new Color(7, 89, 79));
        add(rackPanel);
        add(controlPanel);
    }

    public void load() {
        rackPanel.load();
        controlPanel.load();
    }
}
