package ru.nsu.fit.g14201.dserov.command;

/**
 * Created by dserov on 06/03/16.
 */
public class NoAliasException extends RuntimeCommandException {
    public NoAliasException() {
        super();
    }
    public NoAliasException(String value) {
        super("Could not find alias for \"" + value + "\"");
    }
}
