package ru.nsu.fit.g14201.dserov.core;

/**
 * Created by dserov on 04/03/16.
 */
public interface Command {
    void exec(Context context) throws RuntimeCommandException;
}
