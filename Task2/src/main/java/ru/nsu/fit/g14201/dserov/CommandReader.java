package ru.nsu.fit.g14201.dserov;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by dserov on 04/03/16.
 */
public class CommandReader {
    private Scanner sc;
    private String line;
    private Scanner linesc;

    public CommandReader(Reader input) {
        sc = new Scanner(input);
        line = null;
        linesc = null;
    }

    public boolean nextLine() {
        boolean res = sc.hasNextLine();
        if (res) {
            line = sc.nextLine();
        }
        return res;
    }

    public String nextCommandName() {
        if (line.trim().startsWith("#") || line.trim().equals("")) return null;
        linesc = new Scanner(line);
        return linesc.next();
    }

    public ArrayList<String> nextCommandArgs() {
        ArrayList<String> args = new ArrayList<>();
        while (linesc.hasNext()) {
            args.add(linesc.next());
        }
        return args;
    }
}
