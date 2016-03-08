package ru.nsu.fit.g14201.dserov.exception;

import java.util.ArrayList;

/**
 * Created by dserov on 06/03/16.
 */
public class IllegalCommandArgumentException extends CompileCommandException {
    public IllegalCommandArgumentException(String commandName, String arg) {
        super("Illegal argument for command " + commandName + " : " + arg);
    }
}
