package ru.nsu.fit.g14201.dserov.model;

import ru.nsu.fit.g14201.dserov.model.ScrabbleException;

/**
 * Created by dserov on 21/04/16.
 */
public class WrongPlacementException extends ScrabbleException {
    public WrongPlacementException(String message) {
        super(message);
    }
}
