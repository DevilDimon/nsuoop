package ru.nsu.fit.g14201.dserov.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dserov on 14/04/16.
 */
public class Dictionary {
    private Set<String> words;

    public Dictionary() {
        words = new HashSet<>();
        InputStream stream = getClass().getResourceAsStream("/sowpods.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                words.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidWord(String word) {
        return words.contains(word.toLowerCase());
    }
}
