package ru.nsu.fit.g14201.dserov.command;

import ru.nsu.fit.g14201.dserov.Context;

/**
 * Created by dserov on 04/03/16.
 */
public interface Command {
    void exec(Context context) throws RuntimeCommandException;
}
