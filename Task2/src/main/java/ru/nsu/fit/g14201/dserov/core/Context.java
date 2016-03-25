package ru.nsu.fit.g14201.dserov.core;

/**
 * Created by dserov on 25/03/16.
 */
public interface Context {
    void pushStack(Double value);
    Double popStack();
    void addAlias(String alias, Double value);
    Double peekStack();
    int getStackSize();
    Double getByAlias(String alias);
    void print();
}
