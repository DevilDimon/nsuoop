package ru.nsu.fit.g14201.dserov.command;

import ru.nsu.fit.g14201.dserov.Context;
import ru.nsu.fit.g14201.dserov.exception.CompileCommandException;
import ru.nsu.fit.g14201.dserov.exception.IllegalCommandArgumentException;
import ru.nsu.fit.g14201.dserov.exception.NoAliasException;
import ru.nsu.fit.g14201.dserov.exception.WrongArgumentCountException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by dserov on 04/03/16.
 */
public class Define implements Command {

    public static final String TAG = Define.class.getSimpleName();

    private String alias;
    private String value;

    public Define(ArrayList<String> args) throws CompileCommandException {
        if (args.size() != 2) {
            throw new WrongArgumentCountException(args.size(), 2);
        }
        if (Command.isDouble(args.get(0))) {
            throw new IllegalCommandArgumentException(TAG, args.get(0));
        }
        alias = args.get(0);
        value = args.get(1);
    }



    @Override
    public void exec(Context context) throws NoAliasException {
        Map<String, Double> aliases = context.getAliases();
        Double realValue;
        if (Command.isDouble(value)) {
            realValue = Double.valueOf(value);
        }
        else if (aliases.containsKey(value)) {
            realValue = aliases.get(value);
        } else {
            throw new NoAliasException(value);
        }
        aliases.put(alias, realValue);
    }
}
