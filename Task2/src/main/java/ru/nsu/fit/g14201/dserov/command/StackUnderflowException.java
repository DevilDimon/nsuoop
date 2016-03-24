package ru.nsu.fit.g14201.dserov.command;

import ru.nsu.fit.g14201.dserov.command.RuntimeCommandException;

/**
 * Created by dserov on 06/03/16.
 */
public class StackUnderflowException extends RuntimeCommandException {
    public StackUnderflowException() {
        super("Could not get items from stack.");
    }
}
