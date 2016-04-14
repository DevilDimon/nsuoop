package ru.nsu.fit.g14201.dserov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by dserov on 14/04/16.
 */
public class Bag {
    private ArrayList<Tile> tiles = new ArrayList<>(98);
    private Random random = new Random();

    public Bag() {
        InputStream stream = getClass().getResourceAsStream("/bag.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                Scanner scanner = new Scanner(s);
                String letter = scanner.next();
                int frequency = scanner.nextInt();
                int value = scanner.nextInt();
                for (int i = 0; i < frequency; i++) {
                    tiles.add(new Tile(letter, value));
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tile nextTile() {
        return tiles.remove(random.nextInt(tiles.size()));
    }

    public int getSize() {
        return tiles.size();
    }
}
