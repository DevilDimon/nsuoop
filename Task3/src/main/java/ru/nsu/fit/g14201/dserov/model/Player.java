package ru.nsu.fit.g14201.dserov.model;

import java.util.ArrayList;

/**
 * Created by dserov on 14/04/16.
 */
public abstract class Player {
    private ArrayList<Tile> rack;
    private boolean isHuman;

    public Player(boolean isHuman) {
        rack = new ArrayList<>();
        this.isHuman = isHuman;
    }

    public int getRackSize() {
        return rack.size();
    }

    public void addToRack(Tile tile) {
        rack.add(tile);
    }

    public void removeFromRack(Tile tile) {
        rack.remove(tile);
    }

    public void clearRack() {
        rack.clear();
    }

    public Tile getFromRack(int index) {
        return rack.get(index);
    }

    public int getRackSum() {
        return rack.stream().mapToInt(Tile::getValue).sum();
    }

    public boolean isHuman() {
        return isHuman;
    }

    public abstract void makeMove();

    // display methods

    public String getLetterFromRack(int index) {
        return rack.get(index).getLetter();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Tile t : rack) {
            builder.append(t.getLetter());
            builder.append(" ");
        }
        return builder.toString();
    }
}
