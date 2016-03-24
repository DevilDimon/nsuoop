package ru.nsu.fit.g14201.dserov.command;

/**
 * Created by dserov on 06/03/16.
 */
public class IllegalOperationException extends RuntimeCommandException {
    public IllegalOperationException(String message) {
        super(message);
    }
}
