package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.command.Command;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dserov on 06/03/16.
 */
public class CommandFactory {
    private Map<String, Class> commands;

    public CommandFactory() {
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
            e.printStackTrace();
        }
    }

    public Command factoryMethod(String commandName, ArrayList<String> args) {

        try {
            Constructor ctor = commands.get(commandName).getDeclaredConstructor(ArrayList.class);
            ctor.setAccessible(true);
            return (Command) ctor.newInstance(args);
        } catch (NullPointerException e) {
            System.err.println("Invalid token in command flow: " + commandName);
            System.err.println("The command will be skipped.");
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.err.println("Error while handling command " + commandName +
                    " The command will be skipped: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }
}
