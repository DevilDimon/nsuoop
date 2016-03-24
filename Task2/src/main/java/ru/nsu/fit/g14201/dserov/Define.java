package ru.nsu.fit.g14201.dserov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.g14201.dserov.command.CommandUtil;
import ru.nsu.fit.g14201.dserov.command.Command;
import ru.nsu.fit.g14201.dserov.command.IllegalCommandArgumentException;
import ru.nsu.fit.g14201.dserov.command.NoAliasException;
import ru.nsu.fit.g14201.dserov.command.WrongArgumentCountException;

import java.util.ArrayList;
import java.util.Map;

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
        Map<String, Double> aliases = context.getAliases();
        Double realValue = CommandUtil.getArg(aliases, value);
        aliases.put(alias, realValue);
    }
}