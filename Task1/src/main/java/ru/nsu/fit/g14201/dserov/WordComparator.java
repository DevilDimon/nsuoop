package ru.nsu.fit.g14201.dserov;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by dserov on 25/02/16.
 */
public class WordComparator implements Comparator<Map.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return Integer.compare(o2.getValue(), o1.getValue());
    }
}
