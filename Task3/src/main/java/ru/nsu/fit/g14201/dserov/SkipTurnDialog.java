package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by dserov on 12/05/16.
 */
public class SkipTurnDialog extends JDialog implements ActionListener {
    private Game game;
    private boolean doSkip;

    private JLabel label;
    private JButton yesButton;
    private JButton noButton;

    public SkipTurnDialog(JFrame parent, Game game) {
        super(parent, "Scrabble", true);
        this.game = game;
        label = new JLabel("Are you sure you want to skip the current turn?");
        yesButton = new JButton("Skip");
        yesButton.addActionListener(this);
        noButton = new JButton("Cancel");
        noButton.addActionListener(this);
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(label, gc);

        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        add(yesButton, gc);

        gc.gridx = 1;
        add(noButton, gc);

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                doSkip = false;
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                doSkip = false;
            }
        });
    }

    public boolean isSkip() {
        return doSkip;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == noButton) {
            doSkip = false;
        }
        if (clicked == yesButton) {
            doSkip = true;
        }
        setVisible(false);
    }
}
