package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;

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

    private ImageIcon[] images;
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
            ImageIcon[] imgs = new ImageIcon[32];
            for (int i = 0; i < 6; i++) {
                imgs[26 + i] = loadBoardImage(i + 1);
            }
            for (int i = 0; i < 26; i++) {
                imgs[i] = loadTileImage(i);
            }
            return imgs;
        }

        @Override
        protected void done() {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            try {
                images = get();
                updateBoard();
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



    private ImageIcon loadBoardImage(int imgNum) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/images/board/" + imgNum + ".png"));
            return new ImageIcon(img);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ImageIcon loadTileImage(int imgNum) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/images/tiles/"
                    + ScrabbleUtils.getNameByInt(imgNum) + ".png"));
            return new ImageIcon(img.getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ImageIcon getUnoccupiedImage(int i, int j) {
        switch (game.getUnoccupiedCellType(i, j)) {
            case BLANK: {
                return images[26];
            }
            case DOUBLELETTER: {
                return images[27];
            }
            case TRIPLELETTER: {
                return images[28];
            }
            case DOUBLEWORD: {
                return images[29];
            }
            case TRIPLEWORD: {
                return images[30];
            }
            case STAR: {
                return images[31];
            }
            default: {
                return images[26];
            }
        }
    }

    public void load() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        worker.execute();
    }

    public void updateBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cellButtons[i][j].setVisible(false);
                if (game.isOccupied(i, j)) {
                    int index = ScrabbleUtils.getIntByName(game.getCellChar(i, j));
                    cellButtons[i][j].setIcon(images[index]);
                } else {

                    cellButtons[i][j].setIcon(getUnoccupiedImage(i, j));
                }
                cellButtons[i][j].setVisible(true);
            }
        }
    }


}
