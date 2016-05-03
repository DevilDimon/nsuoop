package ru.nsu.fit.g14201.dserov;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dserov on 02/05/16.
 */
public class ControlButton extends JButton {
    public ControlButton() {
        setPreferredSize(new Dimension(50, 50));
//        setBorder(BorderFactory.createEmptyBorder());
//        setContentAreaFilled(false);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
    }
}
