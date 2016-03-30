package ru.nsu.fit.g14201.dserov.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.g14201.dserov.core.Context;
import ru.nsu.fit.g14201.dserov.core.CommandUtil;
import ru.nsu.fit.g14201.dserov.core.Command;
import ru.nsu.fit.g14201.dserov.core.IllegalCommandArgumentException;
import ru.nsu.fit.g14201.dserov.core.NoAliasException;
import ru.nsu.fit.g14201.dserov.core.WrongArgumentCountException;

import java.util.ArrayList;

/**
 * Created by dserov on 04/03/16.
 */
public class Define implements Command {
    private static final Logger logger = LogManager.getLogger();

    private String alias;
    private String value;

    public Define(ArrayList<String> args) throws WrongArgumentCountException, IllegalCommandArgumentException {
        if (args.size() != 2) {
            logger.warn(args.size() + " arguments instead of 2");
            throw new WrongArgumentCountException(args.size(), 2);
        }
        if (CommandUtil.isDouble(args.get(0))) {
            logger.warn(args.get(0) + "is a Double");
            throw new IllegalCommandArgumentException(args.get(0));
        }
        alias = args.get(0);
        value = args.get(1);
    }



    @Override
    public void exec(Context context) throws NoAliasException {
        Double realValue = CommandUtil.getArg(context, value);
        context.addAlias(alias, realValue);
    }
}
