package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dserov on 03/05/16.
 */
public class BottomPanel extends JPanel implements ControlListener, RackListener {

    private RackPanel rackPanel;
    private ControlPanel controlPanel;

    private ControlListener controlListener;
    private RackListener rackListener;

    public BottomPanel(Game game) {
        rackPanel = new RackPanel(game);
        controlPanel = new ControlPanel(game);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setBackground(new Color(7, 89, 79));
        // TODO: a Table to prevent rearranging
        add(rackPanel);
        add(controlPanel);
    }

    public void update() {
        rackPanel.update();
        controlPanel.update();
    }

    public void load() {
        rackPanel.load();
        controlPanel.load();
        controlPanel.update();
    }

    public void toggleExchange() {
        controlPanel.toggleExchange();
    }

    public void addBorder(int tile) {
        rackPanel.addBorder(tile);
    }

    public void removeBorder(int tile) {
        rackPanel.removeBorder(tile);
    }

    public void setControlListener(ControlListener listener) {
        this.controlListener = listener;
        controlPanel.setControlListener(listener);
    }

    public void setRackListener(RackListener listener) {
        this.rackListener = listener;
        rackPanel.setRackListener(listener);
    }


    @Override
    public void controlClicked(int item) {
        controlListener.controlClicked(item);
    }

    @Override
    public void tileClicked(int tile) {
        rackListener.tileClicked(tile);
    }
}
