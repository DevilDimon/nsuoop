package ru.nsu.fit.g14201.dserov;

import ru.nsu.fit.g14201.dserov.core.Context;

import java.io.IOException;
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


    @Override
    public void pushStack(Double value) {
        stack.add(value);
    }

    @Override
    public Double popStack() {
        return stack.remove(stack.size() - 1);
    }

    @Override
    public void addAlias(String alias, Double value) {
        aliases.put(alias, value);
    }

    @Override
    public Double peekStack() {
        return peekStack(0);
    }

    @Override
    public Double peekStack(int depth) {
        return stack.get(stack.size() - 1 - depth);
    }

    @Override
    public int getStackSize() {
        return stack.size();
    }

    @Override
    public Double getByAlias(String alias) {
        return aliases.get(alias);
    }

    @Override
    public boolean isAlias(String alias) {
        return aliases.containsKey(alias);
    }

    @Override
    public void print(Double value) throws IOException {
        writer.write(value + "\n");
        writer.flush();
    }

    @Override
    public void clearStack() {
        stack.clear();
    }

}
