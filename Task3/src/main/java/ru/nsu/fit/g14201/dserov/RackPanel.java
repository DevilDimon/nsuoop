package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by dserov on 23/04/16.
 */
public class RackPanel extends JPanel implements ActionListener {

    private Game game;

    private ImageIcon[] tileIcons;
    private TileButton[] tileButtons;

    private RackListener listener;

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
            tileButtons[i] = new TileButton(i);
            tileButtons[i].addActionListener(this);
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
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
                    tileButtons[i].setBorder(BorderFactory.createEmptyBorder());
                }
            } else {
                tileButtons[i].setVisible(false);
            }
        }

    }

    public void setRackListener(RackListener listener) {
        this.listener = listener;
    }

    public void load() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        worker.execute();
    }

    public void update() {
        setVisible(false);
        assignImages();
        setVisible(true);
    }

    public void addBorder(int tile) {
        tileButtons[tile].setBorder(BorderFactory.createLineBorder(new Color(215, 65, 29), 2));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        TileButton b = (TileButton) e.getSource();
        listener.tileClicked(b.getIndex());
    }
}
