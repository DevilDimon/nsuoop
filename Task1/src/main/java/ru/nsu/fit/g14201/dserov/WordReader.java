package ru.nsu.fit.g14201.dserov;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dserov on 19/02/16.
 */
public class WordReader {
    private InputStreamReader stream;

    public WordReader(InputStream stream) {
        this.stream = new InputStreamReader(stream);
    }

    public String nextWord() {
        int c;
        boolean midWord = false;
        StringBuilder buff = new StringBuilder();
        try {
            while ((c = stream.read()) != -1) {
                if (!Character.isLetterOrDigit((char) c) && midWord) {
                    return buff.toString();
                }
                if (Character.isLetterOrDigit((char) c)) {
                    if (!midWord) {
                        midWord = true;
                    }
                    buff.append((char) c);
                }
            }
            if (!buff.toString().equals("")) {
                return buff.toString();
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        return null;
    }
}
