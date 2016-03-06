package ru.nsu.fit.g14201.dserov.command;

import ru.nsu.fit.g14201.dserov.Context;

/**
 * Created by dserov on 04/03/16.
 */
public class Add implements Command {
    private double arg1;
    private double arg2;

    // TODO: Exception!!!
    public Add(Object[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception();
        }
        else {

        }
    }

    @Override
    public void exec(Context context) {

    }
}
