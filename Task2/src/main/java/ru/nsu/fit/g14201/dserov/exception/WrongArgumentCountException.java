package ru.nsu.fit.g14201.dserov.exception;

/**
 * Created by dserov on 06/03/16.
 */
public class WrongArgumentCountException extends CompileCommandException {
    public WrongArgumentCountException(int actual, int expected) {
        super("Wrong argument count. Expected: " + expected + " Found: " + actual);
    }
}
