package ru.nsu.fit.g14201.dserov;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by dserov on 02/05/16.
 */
public class ControlPanel extends JPanel {
    private Game game;

    private ImageIcon[] controlIcons;
    private ControlButton[] controlButtons;

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
        // revamp after debug
        for (ControlButton i : controlButtons) {
            i.setIcon(controlIcons[0]);
            i.setToolTipText("Next turn");
        }
    }

    public void load() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        worker.execute();
    }


}