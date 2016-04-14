package ru.nsu.fit.g14201.dserov;

/**
 * Created by dserov on 14/04/16.
 */
public class Cell {
    private Tile tile;
    private int wordMult;
    private int letterMult;
    private int x;
    private int y;

    public Cell(int x, int y) {
        this(x, y, 1, 1);
    }

    public Cell(int x, int y, int wordMult, int letterMult) {
        this.x = x;
        this.y = y;
        this.wordMult = wordMult;
        this.letterMult = letterMult;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public int getWordMult() {
        return wordMult;
    }

    public int getLetterMult() {
        return letterMult;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupied() {
        return (tile == null);
    }

    @Override
    public String toString() {
        if (tile != null) {
            return tile.getLetter();
        }
        if (wordMult > 1) {
            return "w" + wordMult;
        }
        if (letterMult > 1) {
            return "l" + letterMult;
        }
        return "_";
    }
}
