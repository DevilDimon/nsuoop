package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dserov on 03/05/16.
 */
public class BottomPanel extends JPanel implements ControlListener {
    private Game game;

    private RackPanel rackPanel;
    private ControlPanel controlPanel;

    private ControlListener controlListener;
    private RackListener rackListener;

    public BottomPanel(Game game) {
        rackPanel = new RackPanel(game);
        controlPanel = new ControlPanel(game);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBackground(new Color(7, 89, 79));
        add(rackPanel);
        add(controlPanel);
    }

    public void updateRack() {
        rackPanel.update();
    }

    public void load() {
        rackPanel.load();
        controlPanel.load();
    }

    public void setControlListener(ControlListener listener) {
        this.controlListener = listener;
        controlPanel.setControlListener(listener);
    }

    public void setRackListener(RackListener listener) {
        this.rackListener = listener;
    }

    @Override
    public void controlClicked(int item) {
        controlListener.controlClicked(item);
    }
}
