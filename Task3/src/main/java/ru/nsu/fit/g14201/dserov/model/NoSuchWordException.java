package ru.nsu.fit.g14201.dserov.model;

import ru.nsu.fit.g14201.dserov.model.ScrabbleException;

/**
 * Created by dserov on 21/04/16.
 */
public class NoSuchWordException extends ScrabbleException {
    public NoSuchWordException(String word) {
        super("The word \"" + word + "\" is not in the dictionary.");
    }
}
