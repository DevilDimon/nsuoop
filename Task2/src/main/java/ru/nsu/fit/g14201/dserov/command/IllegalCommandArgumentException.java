package ru.nsu.fit.g14201.dserov.command;

import ru.nsu.fit.g14201.dserov.command.CompileCommandException;

/**
 * Created by dserov on 06/03/16.
 */
public class IllegalCommandArgumentException extends CompileCommandException {
    public IllegalCommandArgumentException(String arg) {
        super("Illegal argument: " + arg);
    }
}
