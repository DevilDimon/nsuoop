package ru.nsu.fit.g14201.dserov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by dserov on 25/03/16.
 */
public class CommandReaderTest {

    private CommandReader emptyCommandReader = new CommandReader(new StringReader(""));
    private CommandReader commandReader;

    @Test
    public void emptyFileNextLine() {
        assertFalse(emptyCommandReader.nextLine());
    }

    @Test(expected = NullPointerException.class)
    public void emptyFileCommandName() {
        emptyCommandReader.nextCommandName();
    }

    @Test(expected = NullPointerException.class)
    public void emptyFileCommandArgs() {
        emptyCommandReader.nextCommandArgs();
    }

    @Test(expected = NullPointerException.class)
    public void noArgsAfterNoName() {
        emptyCommandReader.nextCommandName();
        emptyCommandReader.nextCommandArgs();
    }

    @Test
    public void emptyLines() {
        String emptyLines = "\n\n\n\n\n";
        commandReader = new CommandReader(new StringReader(emptyLines));
        assertTrue(commandReader.nextLine());
        assertTrue(commandReader.nextLine());
        assertNull(commandReader.nextCommandName());
    }

    @Test
    public void commentLines() {
        String commentLines = "# 1\n#2\n       #      \n";
        commandReader = new CommandReader(new StringReader(commentLines));
        assertTrue(commandReader.nextLine());
        assertNull(commandReader.nextCommandName());
        assertTrue(commandReader.nextLine());
        assertNull(commandReader.nextCommandName());
        assertTrue(commandReader.nextLine());
        assertNull(commandReader.nextCommandName());
    }

    @Test
    public void simpleCommand() {
        String simpleCommand = "PUSH 18";
        commandReader = new CommandReader(new StringReader(simpleCommand));
        assertTrue(commandReader.nextLine());
        assertEquals("PUSH", commandReader.nextCommandName());
        ArrayList<String> list = new ArrayList<>();
        list.add("18");
        assertEquals(list, commandReader.nextCommandArgs());
    }

    @Test
    public void noArgumentCommand() {
        String noArgumentCommand =  "POP";
        commandReader = new CommandReader(new StringReader(noArgumentCommand));
        assertTrue(commandReader.nextLine());
        assertEquals("POP", commandReader.nextCommandName());
        ArrayList<String> list = new ArrayList<>();
        assertEquals(list, commandReader.nextCommandArgs());
    }
}