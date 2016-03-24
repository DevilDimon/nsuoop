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
 * Created by dserov on 04/03/16.
 */
public class Multiply implements Command {
    private static final Logger logger = LogManager.getLogger();
    public Multiply(ArrayList<String> args) throws WrongArgumentCountException {
        if (args.size() != 0) {
            logger.warn(args.size() + " arguments instead of 0");
            throw new WrongArgumentCountException(args.size(), 0);
        }
    }

    @Override
    public void exec(Context context) throws StackUnderflowException {
        ArrayList<Double> stack = context.getStack();
        if (stack.size() < 2) {
            throw new StackUnderflowException();
        }
        double arg1 = stack.remove(stack.size() - 1);
        double arg2 = stack.remove(stack.size() - 1);
        stack.add(arg1 * arg2);
    }
}
