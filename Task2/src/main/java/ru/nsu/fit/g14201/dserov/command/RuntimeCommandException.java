package ru.nsu.fit.g14201.dserov.command;

import ru.nsu.fit.g14201.dserov.command.CommandException;

/**
 * Created by dserov on 06/03/16.
 */
public class RuntimeCommandException extends CommandException {
    public RuntimeCommandException() {
        super();
    }
    public RuntimeCommandException(String message) {
        super(message);
    }
}
