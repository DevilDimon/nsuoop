package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;
import ru.nsu.fit.g14201.dserov.model.WrongPlacementException;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by dserov on 22/04/16.
 */
public class ViewController extends JFrame {

    private Game game;

    private BoardPanel boardPanel;
    private BottomPanel bottomPanel;
    private StatusPanel statusPanel;

    private SkipTurnDialog skipTurnDialog;
    private GameOverDialog gameOverDialog;
    private Timer timer;
    private int sec;

    private int curRackTile = -1;


    public ViewController(Game game) {
        this.game = game;
    }

    public void init() {
        try {
            SwingUtilities.invokeAndWait(this::createAndShowGUI);
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
        boardPanel.load();
        bottomPanel.load();
        timer.start();
        statusPanel.update();
    }

    public void createAndShowGUI() {
        setTitle("Scrabble v0.1");
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(7, 89, 79));
        GridBagConstraints gc = new GridBagConstraints();
        boardPanel = new BoardPanel(game);

        boardPanel.setBoardListener(this::boardClicked);

        bottomPanel = new BottomPanel(game);

        bottomPanel.setControlListener((item) -> {
            switch (item) {
                case 2 : {
                    skipTurnButton();
                    break;
                }
            }
        });
        bottomPanel.setRackListener(this::rackClicked);

        statusPanel = new StatusPanel(game);
        skipTurnDialog = new SkipTurnDialog(this);
        gameOverDialog = new GameOverDialog(this);

        timer = new Timer(1000, e -> {
            sec++;
            setTitle(String.format("%02d:%02d", sec / 60, sec % 60));
        });

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;

        gc.insets = new Insets(5, 0, 5, 0);
        add(statusPanel, gc);

        gc.insets = new Insets(0,0,0,0);
        gc.gridy = 1;
        add(boardPanel, gc);

        gc.gridy = 2;
        add(bottomPanel, gc);


        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        toFront();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setMinimumSize(new Dimension(600, 730));
    }

    private void boardClicked(int x, int y) {
        if (curRackTile != -1) {
            try {
                game.addTileToMove(curRackTile, x, y);
                String tileChar = game.getTileChar(curRackTile);
                boardPanel.assignTileToCell(ScrabbleUtils.getIntByName(tileChar), x, y);
                curRackTile = -1;
            } catch (WrongPlacementException e) {
                // TODO: dialog
            }
        } // TODO: finish
    }

    private void rackClicked(int tile) {
        if (curRackTile == -1) {
            curRackTile = tile;
            bottomPanel.addBorder(tile);
        } else {
            curRackTile = -1;
            // TODO: add remove border
            bottomPanel.update();
        }
    }

    private void skipTurnButton() {
        skipTurnDialog.setVisible(true);
        if (skipTurnDialog.isSkip()) {
            if (game.inMove()) {
                game.clearMove();
                boardPanel.updateBoard();
            }
            if (game.inExchange()) {
                game.clearExchange();
            }
            nextPlayer();
        }
    }

    // TODO: nextTurnButton

    private void nextPlayer() {
        game.nextPlayer();

        if (game.isGameOver()) {
            game.endGame();
            gameOverDialog.showWithScores(game.getScore(0), game.getScore(1));
            if (gameOverDialog.isRestart()) {
                game.reset();
                boardPanel.updateBoard();
            } else {
                timer.stop();
                dispose();
            }
        }
        bottomPanel.update();
        statusPanel.update();
        curRackTile = -1;
    }
}
