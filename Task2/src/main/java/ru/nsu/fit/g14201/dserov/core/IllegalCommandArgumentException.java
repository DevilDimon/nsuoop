package ru.nsu.fit.g14201.dserov.core;

/**
 * Created by dserov on 06/03/16.
 */
public class IllegalCommandArgumentException extends CompileCommandException {
    public IllegalCommandArgumentException(String arg) {
        super("Illegal argument: " + arg);
    }
}
