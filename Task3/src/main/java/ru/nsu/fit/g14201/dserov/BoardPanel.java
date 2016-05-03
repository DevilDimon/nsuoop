package ru.nsu.fit.g14201.dserov;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by dserov on 22/04/16.
 */
public class BoardPanel extends JPanel {

    private Game game;

    private ImageIcon[] cellImages;
    private CellButton[][] cellButtons;

    public BoardPanel(Game game) {
        this.game = game;
        cellButtons = new CellButton[15][15];
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                gc.gridx = i;
                gc.gridy = j;
                cellButtons[i][j] = new CellButton(i, j);
                add(cellButtons[i][j], gc);

            }
        }
    }

    SwingWorker<ImageIcon[], Void> worker = new SwingWorker<ImageIcon[], Void>() {
        @Override
        protected ImageIcon[] doInBackground() throws Exception {
            ImageIcon[] boardImgs = new ImageIcon[6];
            for (int i = 0; i < 6; i++) {
                boardImgs[i] = loadImage(i + 1);
            }
            return boardImgs;
        }

        @Override
        protected void done() {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            try {
                cellImages = get();
                assignImages();
            } catch (InterruptedException e) {}
            catch (ExecutionException e) {
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
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/images/board/" + imgNum + ".png"));
            return new ImageIcon(img);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void assignImages() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cellButtons[i][j].setVisible(false);
                switch (game.getUnoccupiedCellType(i, j)) {
                    case BLANK: {
                        cellButtons[i][j].setIcon(cellImages[0]);
                        break;
                    }
                    case DOUBLELETTER: {
                        cellButtons[i][j].setIcon(cellImages[1]);
                        break;
                    }
                    case TRIPLELETTER: {
                        cellButtons[i][j].setIcon(cellImages[2]);
                        break;
                    }
                    case DOUBLEWORD: {
                        cellButtons[i][j].setIcon(cellImages[3]);
                        break;
                    }
                    case TRIPLEWORD: {
                        cellButtons[i][j].setIcon(cellImages[4]);
                        break;
                    }
                    case STAR: {
                        cellButtons[i][j].setIcon(cellImages[5]);
                        break;
                    }
                    default: {
                        cellButtons[i][j].setIcon(cellImages[0]);
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cellButtons[i][j].setVisible(true);
            }
        }
    }

    public void load() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        worker.execute();
    }


}
