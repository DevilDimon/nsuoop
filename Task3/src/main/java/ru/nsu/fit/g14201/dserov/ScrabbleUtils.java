package ru.nsu.fit.g14201.dserov;

/**
 * Created by dserov on 02/05/16.
 */
public class ScrabbleUtils {
    public static String getNameByInt(int num) {
        char A = 'A';
        char name = (char) ((int) A + num);
        return name + "";
    }
    public static int getIntByName(String name) {
        char a = name.charAt(0);
        return (int) a - 'A';
    }
}
