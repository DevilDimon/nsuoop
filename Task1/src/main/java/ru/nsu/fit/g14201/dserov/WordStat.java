package ru.nsu.fit.g14201.dserov;

import java.util.*;

/**
 * Created by dserov on 19/02/16.
 */
public class WordStat {
    private Map<String, Counter> dict;
    private int count;

    public WordStat() {
        dict = new HashMap<>();
    }

    public void acceptWord(String word) {
        count++;
        if (dict.containsKey(word)) {
            dict.put(word, dict.get(word).inc());
        } else {
            dict.put(word, new Counter(1, word));
        }
    }

    public int getCount() {
        return count;
    }

    public Map<String, Counter> asMap() {
        return dict;
    }


}
