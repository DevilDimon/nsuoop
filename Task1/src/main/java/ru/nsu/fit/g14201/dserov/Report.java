package ru.nsu.fit.g14201.dserov;

import java.io.Writer;

/**
 * Created by dserov on 19/02/16.
 */
public interface Report {
    void generateReport(WordStat stat, Writer output);
}
