package ru.nsu.fit.g14201.dserov;


import org.junit.After;
import org.junit.Test;
import ru.nsu.fit.g14201.dserov.commands.*;
import ru.nsu.fit.g14201.dserov.core.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by dserov on 25/03/16.
 */
public class RuntimeCommandTest {
    private final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
    {
        System.setOut(new PrintStream(myOut));
    }
    private Context context = new ImplContext(new OutputStreamWriter(System.out));
    private ArrayList<String> args = new ArrayList<>();

    @After
    public void resetOut() {
        myOut.reset();
        args.clear();
        context.clearStack();
    }

    @Test
    public void addOK() throws CommandException {
        Add add = new Add(args);
        context.pushStack(2.0);
        context.pushStack(3.0);
        add.exec(context);
        assertEquals((Double) 5.0, context.peekStack());
        assertEquals(1, context.getStackSize());
    }

    @Test(expected = StackUnderflowException.class)
    public void addUnderflowOne() throws CommandException {
        Add add = new Add(args);
        context.pushStack(228.0);
        try {
            add.exec(context);
        } catch (StackUnderflowException e) {
            assertEquals((Double) 228.0, context.peekStack());
            assertEquals(1, context.getStackSize());
            throw e;
        }
    }

    @Test(expected = StackUnderflowException.class)
    public void addUnderflowZero() throws CommandException {
        Add add = new Add(args);
        try {
            add.exec(context);
        } catch (StackUnderflowException e) {
            assertEquals(0, context.getStackSize());
            throw e;
        }
    }

    @Test
    public void addStackPersistence() throws CommandException {
        Add add = new Add(args);
        context.pushStack(228.0);
        context.pushStack(7.0);
        context.pushStack(2.0);
        add.exec(context);
        assertEquals((Double) 9.0, context.peekStack());
        assertEquals((Double) 228.0, context.peekStack(1));
        assertEquals(2, context.getStackSize());
    }

    @Test
    public void divideOK() throws CommandException {
        Divide divide = new Divide(args);
        context.pushStack(8.0);
        context.pushStack(4.0);
        divide.exec(context);
        assertEquals((Double) 2.0, context.peekStack());
        assertEquals(1, context.getStackSize());
    }

    @Test(expected = StackUnderflowException.class)
    public void divideUnderflowOne() throws CommandException {
        Divide divide = new Divide(args);
        context.pushStack(228.0);
        try {
            divide.exec(context);
        } catch (StackUnderflowException e) {
            assertEquals((Double) 228.0, context.peekStack());
            assertEquals(1, context.getStackSize());
            throw e;
        }
    }

    @Test(expected = StackUnderflowException.class)
    public void divideUnderflowZero() throws CommandException {
        Divide divide = new Divide(args);
        try {
            divide.exec(context);
        } catch (StackUnderflowException e) {
            assertEquals(0, context.getStackSize());
            throw e;
        }
    }

    @Test
    public void divideStackPersistence() throws CommandException {
        Divide divide = new Divide(args);
        context.pushStack(228.0);
        context.pushStack(8.0);
        context.pushStack(4.0);
        divide.exec(context);
        assertEquals((Double) 2.0, context.peekStack());
        assertEquals((Double) 228.0, context.peekStack(1));
        assertEquals(2, context.getStackSize());
    }

    @Test(expected = IllegalOperationException.class)
    public void divideByZero() throws CommandException {
        Divide divide = new Divide(args);
        context.pushStack(1.0);
        context.pushStack(0.0);
        try {
            divide.exec(context);
        } catch (IllegalOperationException e) {
            assertEquals((Double) 0.0, context.peekStack());
            assertEquals((Double) 1.0, context.peekStack(1));
            assertEquals(2, context.getStackSize());
            throw e;
        }
    }

    @Test
    public void multiplyOK() throws CommandException {
        Multiply multiply = new Multiply(args);
        context.pushStack(8.0);
        context.pushStack(4.0);
        multiply.exec(context);
        assertEquals((Double) 32.0, context.peekStack());
        assertEquals(1, context.getStackSize());
    }

    @Test(expected = StackUnderflowException.class)
    public void multiplyUnderflowOne() throws CommandException {
        Multiply multiply = new Multiply(args);
        context.pushStack(228.0);
        try {
            multiply.exec(context);
        } catch (StackUnderflowException e) {
            assertEquals((Double) 228.0, context.peekStack());
            assertEquals(1, context.getStackSize());
            throw e;
        }
    }

    @Test(expected = StackUnderflowException.class)
    public void multiplyUnderflowZero() throws CommandException {
        Multiply multiply = new Multiply(args);
        try {
            multiply.exec(context);
        } catch (StackUnderflowException e) {
            assertEquals(0, context.getStackSize());
            throw e;
        }
    }

    @Test
    public void multiplyStackPersistence() throws CommandException {
        Multiply multiply = new Multiply(args);
        context.pushStack(228.0);
        context.pushStack(8.0);
        context.pushStack(4.0);
        multiply.exec(context);
        assertEquals((Double) 32.0, context.peekStack());
        assertEquals((Double) 228.0, context.peekStack(1));
        assertEquals(2, context.getStackSize());
    }

    @Test
    public void subtractOK() throws CommandException {
        Subtract subtract = new Subtract(args);
        context.pushStack(8.0);
        context.pushStack(1.0);
        subtract.exec(context);
        assertEquals((Double) 7.0, context.peekStack());
        assertEquals(1, context.getStackSize());
    }

    @Test(expected = StackUnderflowException.class)
    public void subtractUnderflowOne() throws CommandException {
        Subtract subtract = new Subtract(args);
        context.pushStack(228.0);
        try {
            subtract.exec(context);
        } catch (StackUnderflowException e) {
            assertEquals((Double) 228.0, context.peekStack());
            assertEquals(1, context.getStackSize());
            throw e;
        }
    }

    @Test(expected = StackUnderflowException.class)
    public void subtractUnderflowZero() throws CommandException {
        Subtract subtract = new Subtract(args);
        try {
            subtract.exec(context);
        } catch (StackUnderflowException e) {
            assertEquals(0, context.getStackSize());
            throw e;
        }
    }

    @Test
    public void subtractStackPersistence() throws CommandException {
        Subtract subtract = new Subtract(args);
        context.pushStack(228.0);
        context.pushStack(8.0);
        context.pushStack(1.0);
        subtract.exec(context);
        assertEquals((Double) 7.0, context.peekStack());
        assertEquals((Double) 228.0, context.peekStack(1));
        assertEquals(2, context.getStackSize());
    }

    @Test
    public void sqrtOK() throws CommandException {
        SquareRoot sqrt = new SquareRoot(args);
        context.pushStack(4.0);
        sqrt.exec(context);
        assertEquals((Double) 2.0, context.peekStack());
        assertEquals(1, context.getStackSize());
    }

    @Test(expected = StackUnderflowException.class)
    public void sqrtUnderflow() throws CommandException {
        SquareRoot sqrt = new SquareRoot(args);
        try {
            sqrt.exec(context);
        } catch (StackUnderflowException e) {
            assertEquals(0, context.getStackSize());
            throw e;
        }
    }

    @Test
    public void sqrtStackPersistence() throws CommandException {
        SquareRoot sqrt = new SquareRoot(args);
        context.pushStack(228.0);
        context.pushStack(8.0);
        context.pushStack(4.0);
        sqrt.exec(context);
        assertEquals((Double) 2.0, context.peekStack());
        assertEquals((Double) 8.0, context.peekStack(1));
        assertEquals(3, context.getStackSize());
    }

    @Test(expected = IllegalOperationException.class)
    public void sqrtFromNegativeNumber() throws CommandException {
        SquareRoot sqrt = new SquareRoot(args);
        context.pushStack(-1.0);
        try {
            sqrt.exec(context);
        } catch (IllegalOperationException e) {
            assertEquals((Double) (-1.0), context.peekStack());
            assertEquals(1, context.getStackSize());
            throw e;
        }
    }

    @Test
    public void printOK() throws CommandException {
        Print print = new Print(args);
        context.pushStack(42.0);
        print.exec(context);
        String output = myOut.toString();
        assertEquals("42.0\n", output);
    }

    @Test
    public void defineOK() throws CommandException {
        args.add("a");
        args.add("4");
        Define define = new Define(args);
        define.exec(context);
        assertEquals((Double) 4.0, context.getByAlias("a"));
    }

    @Test(expected = StackUnderflowException.class)
    public void printUnderflow() throws CommandException {
        Print print = new Print(args);
        try {
            print.exec(context);
        } catch (StackUnderflowException e) {
            assertEquals(0, context.getStackSize());
            throw e;
        }
    }



}
