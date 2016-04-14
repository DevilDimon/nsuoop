package ru.nsu.fit.g14201.dserov;

import java.util.ArrayList;

/**
 * Created by dserov on 14/04/16.
 */
public class Rack {
    private ArrayList<Tile> rack;

    public Rack(Bag bag) {
        rack = new ArrayList<>();
    }

    public int getSize() {
        return rack.size();
    }

    public void add(Tile tile) {
        rack.add(tile);
    }

    public Tile remove(int index) {
        return rack.remove(index);
    }
}
