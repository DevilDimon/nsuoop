package ru.nsu.fit.g14201.dserov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.g14201.dserov.Context;
import ru.nsu.fit.g14201.dserov.command.Command;
import ru.nsu.fit.g14201.dserov.command.CompileCommandException;
import ru.nsu.fit.g14201.dserov.command.StackUnderflowException;
import ru.nsu.fit.g14201.dserov.command.WrongArgumentCountException;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by dserov on 04/03/16.
 */
public class Print implements Command {
    private static final Logger logger = LogManager.getLogger();

    public Print(ArrayList<String> args) throws CompileCommandException {
        if (args.size() != 0) {
            logger.warn(args.size() + " arguments instead of 0");
            throw new WrongArgumentCountException(args.size(), 0);
        }
    }

    @Override
    public void exec(Context context) throws StackUnderflowException {
        Writer writer = context.getWriter();
        ArrayList<Double> stack = context.getStack();
        if (stack.size() < 1) {
            throw new StackUnderflowException();
        }
        try {
            writer.write(stack.get(stack.size() - 1) + "\n");
            writer.flush();
            // TODO: BufferedWriter
        } catch (IOException e) {
            System.err.println("Error: " + e.getLocalizedMessage());
        }
    }
}
