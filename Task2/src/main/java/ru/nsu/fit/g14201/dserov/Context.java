package ru.nsu.fit.g14201.dserov;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by dserov on 04/03/16.
 */
public class Context {
    private Stack<Double> stack;
    private Map<String, Double> aliases;
    private PrintWriter writer;

    public Context(OutputStream output) {
        stack = new Stack<>();
        aliases = new HashMap<>();
        writer = new PrintWriter(output);

    }

    public Stack<Double> getStack() {
        return stack;
    }

    public Map<String, Double> getAliases() {
        return aliases;
    }

    public PrintWriter getWriter() {
        return writer;
    }

}
