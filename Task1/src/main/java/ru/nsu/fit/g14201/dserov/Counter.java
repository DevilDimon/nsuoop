package ru.nsu.fit.g14201.dserov;

/**
 * Created by dserov on 26/02/16.
 */
public class Counter implements Comparable<Counter> {
    private String word;
    private int count;

    public Counter(int num, String word) {
        count = num;
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public String getWord() {
        return word;
    }

    public Counter inc() {
        count++;
        return this;
    }

    @Override
    public int compareTo(Counter o) {
        int res1 = Integer.compare(o.getCount(), getCount());
        if (res1 == 0) {
            return getWord().compareTo(o.getWord());
        }
        else return res1;
    }
}
