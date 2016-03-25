package ru.nsu.fit.g14201.dserov.core;

/**
 * Created by dserov on 06/03/16.
 */
public class StackUnderflowException extends RuntimeCommandException {
    public StackUnderflowException() {
        super("Could not get items from stack.");
    }
}
