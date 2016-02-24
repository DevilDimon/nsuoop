package ru.nsu.fit.g14201.dserov;

import java.io.*;
import java.util.*;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        FileInputStream input = new FileInputStream(args[0]);
        FileWriter output = new FileWriter(args[1]);
        CSVReport report = new CSVReport();
        Processor proc = new Processor(input, output, report);
        proc.run();
    }

}
