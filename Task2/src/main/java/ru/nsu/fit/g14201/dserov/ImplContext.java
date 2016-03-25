package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.core.Context;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dserov on 04/03/16.
 */
public class ImplContext implements Context {
    private ArrayList<Double> stack;
    private Map<String, Double> aliases;
    private Writer writer;

    public ImplContext(Writer output) {
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
