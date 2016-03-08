package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.command.Command;
import ru.nsu.fit.g14201.dserov.exception.CompileCommandException;
import ru.nsu.fit.g14201.dserov.exception.WrongArgumentCountException;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dserov on 06/03/16.
 */
public class CommandFactory {
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
            System.err.println("Error while initializing command list: " + e.getLocalizedMessage());
            throw e;
        }
    }

    public Command factoryMethod(String commandName, ArrayList<String> args) throws CompileCommandException {

        try {
            Constructor ctor = commands.get(commandName).getDeclaredConstructor(ArrayList.class);
            ctor.setAccessible(true);
            return (Command) ctor.newInstance(args);
        } catch (NullPointerException e) {
            throw new CompileCommandException("No such command: " + commandName);
        }
        catch (ReflectiveOperationException e) {
            throw new CompileCommandException(commandName +
             ": " + e.getCause().getLocalizedMessage());
        }
    }
}
