package ru.nsu.fit.g14201.dserov;

import java.io.InputStream;
import java.io.Writer;

/**
 * Created by dserov on 19/02/16.
 */
public class Processor {
    private WordReader reader;
    private WordStat stat;
    private Report report;
    private Writer output;

    public Processor(InputStream input, Writer output, Report report) {
        reader = new WordReader(input);
        stat = new WordStat();
        this.report = report;
        this.output = output;
    }

    public void run() {
        String word;
        while ((word = reader.nextWord()) != null) {
            stat.acceptWord(word);
        }
        report.generateReport(stat, report.prepareReport(stat), output);
    }
}
