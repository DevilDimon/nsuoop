package ru.nsu.fit.g14201.dserov;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by dserov on 24/02/16.
 */
public class CSVReport extends Report {

    @Override
    void generateReport(WordStat stat, TreeSet<Counter> dict, Writer output) {
        try (BufferedWriter bufWriter = new BufferedWriter(output)) {
            for (Counter i : dict) {
                String line = String.format("%s,%d,%f%%\n", i.getWord(), i.getCount(),
                        ((double) i.getCount() / stat.getCount()) * 100);
                bufWriter.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getLocalizedMessage());
        }
    }
}
