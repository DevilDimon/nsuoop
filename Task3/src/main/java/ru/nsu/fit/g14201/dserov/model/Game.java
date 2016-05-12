package ru.nsu.fit.g14201.dserov.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dserov on 21/04/16.
 */
public class Game {
    private Bag bag;
    private Dictionary dict;
    private Move curMove;
    private List<Tile> exchangeBuffer;
    private Board board;
    private Player[] players;
    private int curPlayer;
    private int[] scores;
    private int turn;
    private int skipCount;

    public Game(Player player1, Player player2) {
        players = new Player[]{player1, player2};
        bag = new Bag();
        dict = new Dictionary();
        curMove = new Move();
        exchangeBuffer = new ArrayList<>();
        board = new Board();
        scores = new int[2];
        for (Player p: players) {
            for (int i = 0; i < 7; i++) {
                p.addToRack(bag.nextTile());
            }
        }
    }

    // game control methods

    public void nextPlayer() {
        if (curPlayer == 1) {
            turn++;
        }
        if (curMove.getSize() == 0) {
            skipCount++;
            curPlayer = 1 - curPlayer;
            return;
        }
        while (players[curPlayer].getRackSize() < 7 && bag.getSize() > 0) {
            players[curPlayer].addToRack(bag.nextTile());
        }
        curMove.clear();
        curPlayer = 1 - curPlayer;
    }

    public boolean isCurHuman() {
        return players[curPlayer].isHuman();
    }

    public boolean canExchange() {
        return (players[curPlayer].getRackSize() == 7 && bag.getSize() > 0 && curMove.getSize() == 0);
    }

    public boolean isExchanged(int tileIndex) {
        return exchangeBuffer.contains(players[curPlayer].getFromRack(tileIndex));
    }

    public boolean isGameOver() {
        if (skipCount == 6 || (bag.getSize() == 0 && (players[0].getRackSize() == 0 || players[1].getRackSize() == 0))) {
            return true;
        }
        else return false;
    }

    public void endGame() {
        for (int i = 0; i < 2; i++) {
            scores[i] -= players[i].getRackSum();
        }
    }

    public int getScore() {
        return getScore(curPlayer);
    }

    public int getScore(int player) {
        return scores[player];
    }

    public void addTileToMove(int tileIndex, int x, int y) throws WrongPlacementException {
        if (board.isOccupied(x, y) || curMove.covers(x, y)) throw new WrongPlacementException("The tiles cannot overlap.");
        curMove.add(board.getCell(x, y), players[curPlayer].getFromRack(tileIndex));
    }

    public void removeTileFromMove(int x, int y) {
        if (!board.isOccupied(x, y)) {
            curMove.remove(board.getCell(x, y));
        }
    }

    public void clearMove() {
        curMove.clear();
    }

    public void commitMove() throws ScrabbleException {
        scores[curPlayer] += curMove.evalMove(board, dict, (turn == 0));
        for (Tile t : curMove.tiles()) {
            players[curPlayer].removeFromRack(t);
        }
        curMove.makeMove();
        skipCount = 0;
    }

    public void recallTiles() {
        curMove.clear();
    }

    public void addTileToExchange(int tileIndex) {
        exchangeBuffer.add(players[curPlayer].getFromRack(tileIndex));
    }

    public void removeTileFromExchange(int tileIndex) {
        exchangeBuffer.remove(players[curPlayer].getFromRack(tileIndex));
    }

    public void commitExchange() {
        while (exchangeBuffer.size() > 0 && bag.getSize() > 0) {
            players[curPlayer].removeFromRack(exchangeBuffer.remove(exchangeBuffer.size() - 1));
            players[curPlayer].addToRack(bag.nextTile());
        }
        exchangeBuffer.clear();
    }

    public void clearExchange() {
        exchangeBuffer.clear();
    }

    // display methods

    public enum CellType { BLANK, DOUBLELETTER, TRIPLELETTER, DOUBLEWORD, TRIPLEWORD, STAR }

    public CellType getUnoccupiedCellType(int x, int y) {
        Cell cell = board.getCell(x, y);
        if (cell.getY() == 7 && cell.getX() == 7) {
            return CellType.STAR;
        }
        if (cell.getLetterMult() > 1) {
            return cell.getLetterMult() == 2 ? CellType.DOUBLELETTER : CellType.TRIPLELETTER;
        }
        if (cell.getWordMult() > 1) {
            return cell.getWordMult() == 2 ? CellType.DOUBLEWORD : CellType.TRIPLEWORD;
        }
        return CellType.BLANK;
    }

    public int getRackSize() {
        return players[curPlayer].getRackSize();
    }

    public boolean inMove() {
        return curMove.getSize() > 0;
    }

    public boolean inExchange() {
        return exchangeBuffer.isEmpty();
    }

    public boolean isTilePlayed(int tileIndex) {
        return curMove.tiles().contains(players[curPlayer].getFromRack(tileIndex));
    }

    public String getTileChar(int tileIndex) {
        return players[curPlayer].getLetterFromRack(tileIndex);
    }

    public int getTurn() {
        return turn + 1;
    }

    public int getCurPlayer() {
        return curPlayer + 1;
    }


    @Override
    public String toString() {
        return players[curPlayer].toString() + "\n" + board.toString();
    }
}
