package ru.nsu.fit.g14201.dserov.command;

import ru.nsu.fit.g14201.dserov.command.CommandException;

/**
 * Created by dserov on 06/03/16.
 */
public class CompileCommandException extends CommandException {
    public CompileCommandException() {
        super();
    }

    public CompileCommandException(String message) {
        super(message);
    }
}
