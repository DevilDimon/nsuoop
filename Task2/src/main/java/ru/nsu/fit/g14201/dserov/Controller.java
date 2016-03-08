package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.command.Command;
import ru.nsu.fit.g14201.dserov.exception.CompileCommandException;
import ru.nsu.fit.g14201.dserov.exception.RuntimeCommandException;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by dserov on 06/03/16.
 */
public class Controller {
    private CommandReader reader;
    private Context context;
    private CommandFactory factory;

    public Controller(InputStream inputStream, OutputStream outputStream) throws Exception {
        reader = new CommandReader(inputStream);
        context = new Context(outputStream);
        factory = new CommandFactory();
    }

    public void run() {
        ArrayList<Command> commandFlow = new ArrayList<>();
        int line = 1;
        while (reader.nextLine()) {
            try {
                Command command = factory.factoryMethod(reader.nextCommandName(), reader.nextCommandArgs());
                commandFlow.add(command);
            } catch (CompileCommandException e) {
                System.err.println("Error on line: " + line + ". The command will be skipped.");
                System.err.println(e.getLocalizedMessage());
            }
            line++;
        }
        for (int i = 0; i < commandFlow.size(); i++) {
            try {
                commandFlow.get(i).exec(context);
            }
            catch (RuntimeCommandException e) {
                System.err.println(e.getLocalizedMessage());
                System.err.println("The command will not be executed.");
            }
        }
    }
}
