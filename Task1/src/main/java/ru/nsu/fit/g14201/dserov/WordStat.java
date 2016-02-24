package ru.nsu.fit.g14201.dserov;

import java.util.*;

/**
 * Created by dserov on 19/02/16.
 */
public class WordStat {
    private Map<String, Integer> dict;
    private int count;

    public WordStat() {
        dict = new HashMap<>();
    }

    public void acceptWord(String word) {
        count++;
        if (dict.containsKey(word)) {
            dict.put(word, dict.get(word) + 1);
        } else {
            dict.put(word, 1);
        }
    }

    public int getCount() {
        return count;
    }

    public LinkedHashMap<String, Integer> asLinkedHashMap() {
        List<Map.Entry<String, Integer>> list =  new ArrayList<>(dict.entrySet());
        Collections.sort(list, new WordComparator());
        LinkedHashMap<String, Integer> res = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> i : list) {
            res.put(i.getKey(), i.getValue());
        }
        return res;
    }


}
