package ru.nsu.fit.g14201.dserov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.g14201.dserov.command.Command;
import ru.nsu.fit.g14201.dserov.command.CompileCommandException;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dserov on 06/03/16.
 */
public class CommandFactory {
    private static final Logger logger = LogManager.getLogger();
    private Map<String, Class> commands;

    public CommandFactory() throws IOException, ClassNotFoundException {
        commands = new HashMap<>();
        InputStream stream = getClass().getResourceAsStream("/commands.txt");
        StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(stream));
        tokenizer.ordinaryChar('/');

        try {
            String cmd, cls;
            tokenizer.nextToken();
            while (tokenizer.ttype != StreamTokenizer.TT_EOF) {
                cmd = (tokenizer.sval == null) ? Character.toString((char) tokenizer.ttype) : tokenizer.sval;
                tokenizer.nextToken();
                cls = tokenizer.sval;
                commands.put(cmd, Class.forName(cls));
                tokenizer.nextToken();
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Configurating from {}: failed to create object of class: {}", "commands.txt", tokenizer.sval);
            System.err.println("Error while initializing command list: " + e.getLocalizedMessage());
            throw e; // TODO: remove to avoid exception handling in App.java?
        }
        logger.info("Configuration successfully completed");
    }

    public Command factoryMethod(String commandName, ArrayList<String> args) throws CompileCommandException {

        if (commands.get(commandName) == null) {
            logger.warn("Encountered command not present in config: {}", commandName);
            throw new CompileCommandException("No such command: " + commandName);
        }

        try {
            Constructor ctor = commands.get(commandName).getDeclaredConstructor(ArrayList.class);
            ctor.setAccessible(true);
            return (Command) ctor.newInstance(args);
        } catch (ReflectiveOperationException e) {
            throw new CompileCommandException(commandName +
                    ": " + e.getCause().getLocalizedMessage());
        }

    }
}
