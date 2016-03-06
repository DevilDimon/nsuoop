package ru.nsu.fit.g14201.dserov;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by dserov on 04/03/16.
 */
public class CommandReader {
    private Scanner sc;
    private String line;
    private Scanner linesc;

    public CommandReader(InputStream stream) {
        sc = new Scanner(stream);
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
        linesc = new Scanner(line);
        return linesc.next();
    }

    // TODO: Limit for #
    public ArrayList<String> nextCommandArgs() {
        ArrayList<String> args = new ArrayList<>();
        while (linesc.hasNext()) {
            args.add(linesc.next());
        }
        return args;
    }
}
