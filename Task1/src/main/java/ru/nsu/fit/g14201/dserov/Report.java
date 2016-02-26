package ru.nsu.fit.g14201.dserov;

import java.io.Writer;
import java.util.*;

/**
 * Created by dserov on 19/02/16.
 */
public abstract class Report {
    public Report() {}

    public TreeSet<Counter> prepareReport(WordStat stat) {
        Map<String, Counter> map = stat.asMap();
        TreeSet<Counter> set = new TreeSet<>(map.values());
        return set;
    }
    abstract void generateReport(WordStat stat, TreeSet<Counter> dict, Writer output);

}
