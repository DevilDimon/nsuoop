package ru.nsu.fit.g14201.dserov;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dserov on 24/02/16.
 */
public class CSVReport implements Report {
    @Override
    public void generateReport(WordStat stat, Writer output) {
        LinkedHashMap<String, Integer> sortDict = stat.asLinkedHashMap();
        try (BufferedWriter bufWriter = new BufferedWriter(output)) {
            for (Map.Entry<String, Integer> i : sortDict.entrySet()) {
                String line = String.format("%s,%d,%f%%\n", i.getKey(), i.getValue(),
                        ((double) i.getValue() / stat.getCount()) * 100);
                bufWriter.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getLocalizedMessage());
        }
    }
}
