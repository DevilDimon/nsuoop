package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.model.Game;
import ru.nsu.fit.g14201.dserov.model.ScrabbleException;
import ru.nsu.fit.g14201.dserov.model.WrongPlacementException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    private boolean inExchange = false;

    private final String welcomeMessage = "Scrabble v0.1 build 05200121alpha\n" +
            "If it is your first time playing Scrabble, " +
            "you are encouraged to read the rules.\n" +
            "Warning: this version does not support blank tiles, challenges, and overtime, " +
            "running a slightly modified casual rule set.";

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
        statusPanel.update();
        JOptionPane.showMessageDialog(this, welcomeMessage, "Welcome to Scrabble!",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/images/tiles/S.png")));
        timer.start();
    }

    public void createAndShowGUI() {
        setTitle("Scrabble v0.1");
        setIconImage((new ImageIcon(getClass().getResource("/images/tiles/S.png")).getImage()));
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(7, 89, 79));
        GridBagConstraints gc = new GridBagConstraints();
        boardPanel = new BoardPanel(game);

        boardPanel.setBoardListener(this::boardClicked);

        bottomPanel = new BottomPanel(game);

        bottomPanel.setControlListener((item) -> {
            switch (item) {
                case 0 : {
                    exchangeButton();
                    break;
                }
                case 1 : {
                    recallButton();
                    break;
                }
                case 2 : {
                    skipTurnButton();
                    break;
                }
                case 3 : {
                    nextTurnButton();
                    break;
                }
                default: {
                    break;
                }
            }
        });
        bottomPanel.setRackListener(this::rackClicked);

        statusPanel = new StatusPanel(game);
        skipTurnDialog = new SkipTurnDialog(this);
        gameOverDialog = new GameOverDialog(this);

        timer = new Timer(1000, this::countTime);

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
                bottomPanel.update();
            } catch (WrongPlacementException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Wrong placement!", JOptionPane.ERROR_MESSAGE);
            }
        } else if (!inExchange) {
            game.removeBufferedTileFromMove(x, y);
            if (!game.isOccupied(x, y)) {
                boardPanel.removeTileFromCell(x, y);
            }
            bottomPanel.update();
        }
    }

    private void rackClicked(int tile) {
        if (!inExchange) {
            if (curRackTile == -1) {
                curRackTile = tile;
                bottomPanel.addBorder(tile);
            } else {
                curRackTile = -1;
                bottomPanel.update();
            }
        } else {
            if (game.isExchanged(tile)) {
                game.removeTileFromExchange(tile);
                bottomPanel.removeBorder(tile);
            } else {
                game.addTileToExchange(tile);
                bottomPanel.addBorder(tile);
            }
        }
    }

    private void exchangeButton() {
        curRackTile = -1;
        toggleExchange();
        if (game.inExchange()) {
            game.commitExchange();
            bottomPanel.update();
            nextPlayer();
        }
    }

    private void recallButton() {
        clearBuffers();
        bottomPanel.update();
    }

    private void skipTurnButton() {
        skipTurnDialog.setVisible(true);
        if (skipTurnDialog.isSkip()) {
            clearBuffers();
            nextPlayer();
        }
    }

    private void nextTurnButton() {
        try {
            game.commitMove();
            nextPlayer();
        } catch (ScrabbleException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid turn!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void nextPlayer() {
        game.nextPlayer();

        if (game.isGameOver()) {
            finishGame();
        } else {
            JOptionPane.showMessageDialog(this, "Player " + game.getCurPlayer() + ": it's your turn!",
                    "Change player", JOptionPane.INFORMATION_MESSAGE);
        }
        bottomPanel.update();
        statusPanel.update();
        curRackTile = -1;
        inExchange = false;
    }

    private void finishGame() {
        game.endGame();
        timer.stop();
        gameOverDialog.showWithScores(game.getScore(0), game.getScore(1));
        if (gameOverDialog.isRestart()) {
            game.reset();
            sec = 0;
            timer.restart();
            boardPanel.updateBoard();
        } else {
            dispose();
        }
    }

    private void clearBuffers() {
        if (game.inMove()) {
            game.clearMove();
            boardPanel.updateBoard();
        }
        if (game.inExchange()) {
            game.clearExchange();
        }
    }

    private void toggleExchange() {
        inExchange = !inExchange;
        bottomPanel.update();
        bottomPanel.toggleExchange();
    }

    private void countTime(ActionEvent e) {
        sec++;
        setTitle(String.format("%02d:%02d", sec / 60, sec % 60));
    }
}
