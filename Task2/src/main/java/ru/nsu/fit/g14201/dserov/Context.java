package ru.nsu.fit.g14201.dserov;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by dserov on 04/03/16.
 */
public class Context {
    private ArrayList<Double> stack;
    private Map<String, Double> aliases;
    private Writer writer;

    public Context(Writer output) {
        stack = new ArrayList<>();
        aliases = new HashMap<>();
        writer = output;

    }

    public ArrayList<Double> getStack() {
        return stack;
    }

    public Map<String, Double> getAliases() {
        return aliases;
    }

    public Writer getWriter() {
        return writer;
    }

}
