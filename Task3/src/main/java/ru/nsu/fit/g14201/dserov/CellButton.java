package ru.nsu.fit.g14201.dserov;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dserov on 23/04/16.
 */
public class CellButton extends JButton {
    private int gridx;
    private int gridy;

    public CellButton(int x, int y) {
        gridx = x;
        gridy = y;
        setPreferredSize(new Dimension(40, 40));
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
    }

    public int getGridX() {
        return gridx;
    }

    public int getGridY() {
        return gridy;
    }
}
