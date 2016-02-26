package ru.nsu.fit.g14201.dserov;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by dserov on 25/02/16.
 */
public class WordComparator implements Comparator<Map.Entry<String, Counter>> {
    @Override
    public int compare(Map.Entry<String, Counter> o1, Map.Entry<String, Counter> o2) {
        return Integer.compare(o2.getValue().getCount(), o1.getValue().getCount());
    }
}
