package ru.nsu.fit.g14201.dserov.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.g14201.dserov.core.Context;
import ru.nsu.fit.g14201.dserov.core.Command;
import ru.nsu.fit.g14201.dserov.core.StackUnderflowException;
import ru.nsu.fit.g14201.dserov.core.WrongArgumentCountException;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by dserov on 04/03/16.
 */
public class Print implements Command {
    private static final Logger logger = LogManager.getLogger();

    public Print(ArrayList<String> args) throws WrongArgumentCountException {
        if (args.size() != 0) {
            logger.warn(args.size() + " arguments instead of 0");
            throw new WrongArgumentCountException(args.size(), 0);
        }
    }

    @Override
    public void exec(Context context) throws StackUnderflowException {
        if (context.getStackSize() < 1) {
            throw new StackUnderflowException();
        }
        try {
            context.print(context.peekStack());
            // TODO: BufferedWriter
        } catch (IOException e) {
            System.err.println("Error: " + e.getLocalizedMessage());
        }
    }
}
