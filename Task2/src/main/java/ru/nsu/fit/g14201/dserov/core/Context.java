package ru.nsu.fit.g14201.dserov.core;

import java.io.IOException;

/**
 * Created by dserov on 25/03/16.
 */
public interface Context {
    void pushStack(Double value);
    Double popStack();
    void addAlias(String alias, Double value);
    Double peekStack();
    Double peekStack(int depth);
    int getStackSize();
    Double getByAlias(String alias);
    boolean isAlias(String alias);
    void print(Double value) throws IOException;
    void clearStack();

}
