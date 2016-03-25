package ru.nsu.fit.g14201.dserov.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.g14201.dserov.core.Context;
import ru.nsu.fit.g14201.dserov.core.CommandUtil;
import ru.nsu.fit.g14201.dserov.core.Command;
import ru.nsu.fit.g14201.dserov.core.NoAliasException;
import ru.nsu.fit.g14201.dserov.core.WrongArgumentCountException;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by dserov on 06/03/16.
 */
public class Push implements Command {
    private static final Logger logger = LogManager.getLogger();

    private String value;

    public Push(ArrayList<String> args) throws WrongArgumentCountException {
        if (args.size() != 1) {
            logger.warn(args.size() + " arguments instead of 1");
            throw new WrongArgumentCountException(args.size(), 1);
        }

        value = args.get(0);
    }

    @Override
    public void exec(Context context) throws NoAliasException {
        Map<String, Double> aliases = context.getAliases();
        Double realValue = CommandUtil.getArg(aliases, value);
        ArrayList<Double> stack = context.getStack();
        stack.add(realValue);
    }
}
