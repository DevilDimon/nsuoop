package ru.nsu.fit.g14201.dserov.model;

/**
 * Created by dserov on 14/04/16.
 */
public class Tile {
    private String letter;
    private int value;

    public Tile(String letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    public String getLetter() {
        return letter;
    }

    public int getValue() {
        return value;
    }
}
