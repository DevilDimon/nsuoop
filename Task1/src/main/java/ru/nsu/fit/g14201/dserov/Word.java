package ru.nsu.fit.g14201.dserov;

/**
 * Created by dserov on 18/02/16.
 */
public class Word implements Comparable<Word> {
    private String word;
    private int count;

    public Word(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void incCount() {
        count++;
    }

    @Override
    public int compareTo(Word o) {
        return count - o.count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        return word.equals(word1.word);

    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }


    @Override
    public String toString() {
        return word + "," + count;
    }
}
