package ru.nsu.fit.g14201.dserov;

import org.junit.Test;
import ru.nsu.fit.g14201.dserov.core.CompileCommandException;

import java.io.IOException;

/**
 * Created by dserov on 25/03/16.
 */
public class CommandFactoryTest {
    private CommandFactory commandFactory = new CommandFactory();

    public CommandFactoryTest() throws IOException, ClassNotFoundException {
    }

    @Test(expected = CompileCommandException.class)
    public void nullCommandName() throws CompileCommandException {
        commandFactory.factoryMethod(null, null);
    }

}
