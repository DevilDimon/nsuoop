package ru.nsu.fit.g14201.dserov;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by dserov on 23/04/16.
 */
public class RackPanel extends JPanel {

    private Game game;

    private ImageIcon[] tileIcons;
    private TileButton[] tileButtons;

    public RackPanel(Game game) {
        this.game = game;
        tileButtons = new TileButton[7];

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        for (int i = 0; i < 7; i++) {
            gc.gridx = i;
            tileButtons[i] = new TileButton();
            add(tileButtons[i]);
        }
    }

    SwingWorker<ImageIcon[], Void> worker = new SwingWorker<ImageIcon[], Void>() {
        @Override
        protected ImageIcon[] doInBackground() throws Exception {
            ImageIcon[] tileImgs = new ImageIcon[26];
            for(int i = 0; i < 26; i++) {
                tileImgs[i] = loadImage(i);
            }
            return tileImgs;
        }

        @Override
        protected void done() {
            try {
                tileIcons = get();
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
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/images/tiles/"
                    + ScrabbleUtils.getNameByInt(imgNum) + ".png"));
            return new ImageIcon(img);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void assignImages() {
        for (int i = 0; i < 7; i++) {
            if (i < game.getRackSize()) {
                if (game.inMove() && game.isTilePlayed(i)) {
                    tileButtons[i].setVisible(false);
                } else {
                    tileButtons[i].setVisible(true);
                    String name = game.getTileChar(i);
                    tileButtons[i].setIcon(tileIcons[ScrabbleUtils.getIntByName(name)]);
                }
            } else {
                tileButtons[i].setVisible(false);
            }
        }

    }

    public void load() {
        worker.execute();
    }

    public void update() {
        assignImages();
    }


}