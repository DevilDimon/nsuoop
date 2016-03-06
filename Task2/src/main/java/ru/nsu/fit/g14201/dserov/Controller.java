package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.command.Command;

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

    public Controller(InputStream inputStream, OutputStream outputStream) {
        reader = new CommandReader(inputStream);
        context = new Context(outputStream);
        factory = new CommandFactory();
    }

    public void run() {
        ArrayList<Command> commandFlow = new ArrayList<>();
        while (reader.nextLine()) {
            Command command = factory.factoryMethod(reader.nextCommandName(), reader.nextCommandArgs());
            commandFlow.add(command);
        }
        for (Command command : commandFlow) {
            try {
                command.exec(context);
            }
            catch (){

            }
        }
    }
}
