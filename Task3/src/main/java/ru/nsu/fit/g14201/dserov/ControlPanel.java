package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by dserov on 02/05/16.
 */
public class ControlPanel extends JPanel implements ActionListener {
    private Game game;

    private ImageIcon[] controlIcons;
    private ControlButton[] controlButtons;

    private ControlListener listener;

    public ControlPanel(Game game) {
        this.game = game;
        controlButtons = new ControlButton[4];
        setBackground(new Color(7, 89, 79));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        for (int i = 0; i < 4; i++) {
            gc.gridx = i;
            controlButtons[i] = new ControlButton();
            controlButtons[i].setHorizontalTextPosition(JButton.CENTER);
            controlButtons[i].setVerticalTextPosition(JButton.CENTER);
            add(controlButtons[i]);
        }

    }

    SwingWorker<ImageIcon[], Void> worker = new SwingWorker<ImageIcon[], Void>() {
        @Override
        protected ImageIcon[] doInBackground() throws Exception {
            ImageIcon[] icons = new ImageIcon[4];
            for (int i = 0; i < 4; i++) {
                icons[i] = loadImage(i + 1);
            }
            return icons;
        }

        @Override
        protected void done() {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            try {
                controlIcons = get();
                assignImages();
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
                String why;
                Throwable cause = e.getCause();
                if (cause != null) {
                    why = cause.getLocalizedMessage();
                } else {
                    why = e.getLocalizedMessage();
                }
                System.err.println("Error while loading resources: " + why);
            }
        }
    };

    private ImageIcon loadImage(int imgNum) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/images/controls/" + imgNum + ".png"));
            return new ImageIcon(img);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void assignImages() {

        for (int i = 0; i < 4; i++) {
            controlButtons[i].setIcon(controlIcons[i]);
            controlButtons[i].addActionListener(this);
        }
        controlButtons[0].setToolTipText("Exchange tiles");
        controlButtons[1].setToolTipText("Recall tiles");
        controlButtons[2].setToolTipText("Skip turn");
        controlButtons[3].setToolTipText("Accept tile placement");
    }

    public void load() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        worker.execute();
    }

    public void setControlListener(ControlListener listener) {
        this.listener = listener;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ControlButton clicked = (ControlButton) e.getSource();
        for (int i = 0; i < 4; i++) {
            if (clicked == controlButtons[i]) {
                listener.controlClicked(i);
                break;
            }
        }
    }

    public void update() {
        controlButtons[0].setText("" + game.getBagSize());
        controlButtons[0].setEnabled(game.canExchange());
        controlButtons[3].setEnabled(game.inMove());
    }

    public void toggleExchange() {
        boolean prevState = controlButtons[1].isEnabled();
        for (int i = 1; i < 4; i++) {
            controlButtons[i].setEnabled(!prevState);
        }
        if (prevState == false) {
            update();
        }
    }
}
