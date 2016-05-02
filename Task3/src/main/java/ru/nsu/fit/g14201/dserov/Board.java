package ru.nsu.fit.g14201.dserov;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by dserov on 14/04/16.
 */
public class Board {
    private Cell[][] cells;

    public Board() {
        cells = new Cell[15][15];
        InputStream stream = getClass().getResourceAsStream("/board.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            String s;
            int i = 0;
            while ((s = bufferedReader.readLine()) != null) {
                Scanner scanner = new Scanner(s);
                String tile;
                for (int j = 0; j < 15; j++) {
                    tile = scanner.next();
                    switch (tile) {
                        case "w3" : {
                            cells[i][j] = new Cell(i, j, 3, 1);
                            break;
                        }
                        case "w2" : {
                            cells[i][j] = new Cell(i, j, 2, 1);
                            break;
                        }
                        case "l3" : {
                            cells[i][j] = new Cell(i, j, 1, 3);
                            break;
                        }
                        case "l2" : {
                            cells[i][j] = new Cell(i, j, 1, 2);
                            break;
                        }
                        default : {
                            cells[i][j] = new Cell(i, j, 1, 1);
                        }
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOccupied(int x, int y) {
        return cells[x][y].isOccupied();
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                res.append(cells[j][i]).append(" ");
            }
            res.append("\n");
        }
        return res.toString();
    }
}
