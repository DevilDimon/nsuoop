package ru.nsu.fit.g14201.dserov;

import java.io.*;
import java.util.*;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        FileInputStream input = new FileInputStream(args[0]);
        Map<Word, Word> dict = new HashMap<>();
        long wordCount = 0;
        try (InputStreamReader reader = new InputStreamReader(input)) {
            int c;
            boolean midWord = false;
            StringBuilder buff = new StringBuilder();
            while ((c = reader.read()) != -1) {
                if (!Character.isLetterOrDigit((char) c) && midWord) {
                    midWord = false;
                    Word curWord = new Word(buff.toString(), 1);
                    buff.delete(0, buff.length());
                    if (dict.containsKey(curWord)) {
                        dict.get(curWord).incCount();
                    }
                    else {
                        dict.put(curWord, curWord);
                    }
                    wordCount++;
                    continue;
                }
                if (Character.isLetterOrDigit((char) c)) {
                    if (!midWord) {
                        midWord = true;
                    }
                    buff.append((char) c);
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        ArrayList<Word> sortDict = new ArrayList<>(dict.keySet());
        Collections.sort(sortDict);
        FileWriter writer = new FileWriter("output.csv");
        try (BufferedWriter bufWriter = new BufferedWriter(writer)) {
            for (int i = sortDict.size() - 1; i >= 0 ; i--) {
                String line = String.format("%s,%f%%%n", sortDict.get(i),
                        ((double) sortDict.get(i).getCount() / wordCount) * 100);
                bufWriter.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getLocalizedMessage());
        }
    }

}
