package ru.nsu.fit.g14201.dserov.command;

import ru.nsu.fit.g14201.dserov.Context;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by dserov on 04/03/16.
 */
public class Define implements Command {

    private String alias;
    private String value;

    public Define(ArrayList<String> args) {
        if (args.size() != 2) {
            // TODO: throw compile-time exception
        }
        if (Command.isDouble(args.get(0))) {
            // TODO: throw compile-time exception
        }
        alias = args.get(0);
        value = args.get(1);
    }



    @Override
    public void exec(Context context) {
        Map<String, Double> aliases = context.getAliases();
        Double realValue = null;
        if (Command.isDouble(value)) {
            realValue = Double.valueOf(value);
        }
        else if (aliases.containsKey(value)) {
            realValue = aliases.get(value);
        } else {
            // TODO: throw runtime exception
        }
        aliases.put(alias, realValue);
    }
}
