package ru.nsu.fit.g14201.dserov;

import org.junit.Test;
import ru.nsu.fit.g14201.dserov.commands.*;
import ru.nsu.fit.g14201.dserov.core.IllegalCommandArgumentException;
import ru.nsu.fit.g14201.dserov.core.WrongArgumentCountException;

import java.util.ArrayList;

import static org.junit.Assert.fail;


/**
 * Created by dserov on 25/03/16.
 */
public class CompileCommandTest {

    @Test(expected = WrongArgumentCountException.class)
    public void addWrongArguments() throws WrongArgumentCountException {
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        args.add("2");
        Add add = new Add(args);
    }

    @Test
    public void addRightArguments() {
        ArrayList<String> args = new ArrayList<>();
        try {
            Add add = new Add(args);
        } catch (WrongArgumentCountException e) {
            fail();
        }
    }

    @Test(expected = WrongArgumentCountException.class)
    public void defineWrongArgumentCount() throws WrongArgumentCountException {
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        try {
            Define define = new Define(args);
        } catch (IllegalCommandArgumentException e) {
            fail();
        }
    }

    @Test(expected = IllegalCommandArgumentException.class)
    public void defineNumberAsAlias() throws IllegalCommandArgumentException {
        ArrayList<String> args = new ArrayList<>();
        args.add("4");
        args.add("5");
        try {
            Define define = new Define(args);
        } catch (WrongArgumentCountException e) {
            fail();
        }
    }

    @Test
    public void defineSecondAsNumber() {
        ArrayList<String> args = new ArrayList<>();
        args.add("a");
        args.add("4");
        try {
            Define define = new Define(args);
        } catch (IllegalCommandArgumentException | WrongArgumentCountException e) {
            fail();
        }
    }

    @Test
    public void defineSecondAsAlias() {
        ArrayList<String> args = new ArrayList<>();
        args.add("a");
        args.add("b");
        try {
            Define define = new Define(args);
        } catch (IllegalCommandArgumentException | WrongArgumentCountException e) {
            fail();
        }
    }

    @Test(expected = WrongArgumentCountException.class)
    public void divideWrongArguments() throws WrongArgumentCountException {
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        args.add("2");
        Divide divide = new Divide(args);
    }

    @Test
    public void divideRightArguments() {
        ArrayList<String> args = new ArrayList<>();
        try {
            Divide divide = new Divide(args);
        } catch (WrongArgumentCountException e) {
            fail();
        }
    }

    @Test(expected = WrongArgumentCountException.class)
    public void multiplyWrongArguments() throws WrongArgumentCountException {
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        args.add("2");
        Multiply multiply = new Multiply(args);
    }

    @Test
    public void multiplyRightArguments() {
        ArrayList<String> args = new ArrayList<>();
        try {
            Multiply multiply = new Multiply(args);
        } catch (WrongArgumentCountException e) {
            fail();
        }
    }

    @Test(expected = WrongArgumentCountException.class)
    public void subtractWrongArguments() throws WrongArgumentCountException {
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        args.add("2");
        Subtract subtract = new Subtract(args);
    }

    @Test
    public void subtractRightArguments() {
        ArrayList<String> args = new ArrayList<>();
        try {
            Subtract subtract = new Subtract(args);
        } catch (WrongArgumentCountException e) {
            fail();
        }
    }

    @Test(expected = WrongArgumentCountException.class)
    public void sqrtWrongArguments() throws WrongArgumentCountException {
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        args.add("2");
        SquareRoot squareRoot = new SquareRoot(args);
    }

    @Test
    public void sqrtRightArguments() {
        ArrayList<String> args = new ArrayList<>();
        try {
            SquareRoot squareRoot = new SquareRoot(args);
        } catch (WrongArgumentCountException e) {
            fail();
        }
    }

    @Test(expected = WrongArgumentCountException.class)
    public void pushWrongArguments() throws WrongArgumentCountException {
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        args.add("2");
        Push push = new Push(args);
    }

    @Test
    public void pushNumber() {
        ArrayList<String> args = new ArrayList<>();
        args.add("12");
        try {
            Push push = new Push(args);
        } catch (WrongArgumentCountException e) {
            fail();
        }
    }

    @Test
    public void pushAlias() {
        ArrayList<String> args = new ArrayList<>();
        args.add("c");
        try {
            Push push = new Push(args);
        } catch (WrongArgumentCountException e) {
            fail();
        }
    }

    @Test(expected = WrongArgumentCountException.class)
    public void popWrongArguments() throws WrongArgumentCountException {
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        args.add("2");
        Pop pop = new Pop(args);
    }

    @Test
    public void popRightArguments() {
        ArrayList<String> args = new ArrayList<>();
        try {
            Pop pop = new Pop(args);
        } catch (WrongArgumentCountException e) {
            fail();
        }
    }

    @Test(expected = WrongArgumentCountException.class)
    public void printWrongArguments() throws WrongArgumentCountException {
        ArrayList<String> args = new ArrayList<>();
        args.add("1");
        args.add("2");
        Print print = new Print(args);
    }

    @Test
    public void printRightArguments() {
        ArrayList<String> args = new ArrayList<>();
        try {
            Print print = new Print(args);
        } catch (WrongArgumentCountException e) {
            fail();
        }
    }




}
