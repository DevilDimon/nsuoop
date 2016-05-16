package ru.nsu.fit.g14201.dserov;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dserov on 23/04/16.
 */
public class TileButton extends JButton {
    private int index;

    public TileButton(int index) {
        this.index = index;
        setPreferredSize(new Dimension(50, 50));
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
    }

    public int getIndex() {
        return index;
    }
}
