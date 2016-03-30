package ru.nsu.fit.g14201.dserov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.g14201.dserov.core.Command;
import ru.nsu.fit.g14201.dserov.core.CompileCommandException;
import ru.nsu.fit.g14201.dserov.core.Context;
import ru.nsu.fit.g14201.dserov.core.RuntimeCommandException;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by dserov on 06/03/16.
 */
public class Controller {
    private static final Logger logger = LogManager.getLogger();
    private CommandReader reader;
    private Context context;
    private CommandFactory factory;

    public Controller(Reader input, Writer output) throws Exception {
        reader = new CommandReader(input);
        context = new ImplContext(output);
        factory = new CommandFactory();
    }

    public void run() {
        ArrayList<Command> commandFlow = new ArrayList<>();
        int line = 1;
        logger.info("Compilation initiated");
        while (reader.nextLine()) {
            String commandName = reader.nextCommandName();
            if (commandName == null) continue;
            try {
                Command command = factory.factoryMethod(commandName, reader.nextCommandArgs());
                commandFlow.add(command);
            }
            catch (CompileCommandException e) {
                System.err.println("Error on line: " + line + ". The core will be skipped.");
                System.err.println(e.getLocalizedMessage());
            }
            line++;
        }
        logger.info("Compilation finished");

        logger.info("Command execution initiated");
        for (Command curCommand : commandFlow) {
            try {
                curCommand.exec(context);
            } catch (RuntimeCommandException e) {
                System.err.println(curCommand.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
            }
        }
        logger.info("Command execution finished");
    }
}
