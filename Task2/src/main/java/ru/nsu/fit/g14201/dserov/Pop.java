package ru.nsu.fit.g14201.dserov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.g14201.dserov.Context;
import ru.nsu.fit.g14201.dserov.command.Command;
import ru.nsu.fit.g14201.dserov.command.StackUnderflowException;
import ru.nsu.fit.g14201.dserov.command.WrongArgumentCountException;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by dserov on 06/03/16.
 */
public class Pop implements Command {
    private static final Logger logger = LogManager.getLogger();

    public Pop(ArrayList<String> args) throws WrongArgumentCountException {
        if (args.size() != 0) {
            logger.warn(args.size() + " arguments instead of 0");
            throw new WrongArgumentCountException(args.size(), 0);
        }
    }

    @Override
    public void exec(Context context) throws StackUnderflowException {
        ArrayList<Double> stack = context.getStack();
        if (stack.size() < 1) {
            throw new StackUnderflowException();
        }
        stack.remove(stack.size() - 1);
    }
}
