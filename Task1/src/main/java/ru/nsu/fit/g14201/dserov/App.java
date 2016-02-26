package ru.nsu.fit.g14201.dserov;

import java.io.*;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        if (args.length < 2) {
            System.err.println("Error: not enough arguments.");
            return;
        }
        FileInputStream input = new FileInputStream(args[1]);
        FileWriter output = new FileWriter(args[0]);
        CSVReport report = new CSVReport();
        Processor proc = new Processor(input, output, report);
        proc.run();
        try {
            input.close();
            output.close();
        } catch (IOException e) {

        }
    }

}
