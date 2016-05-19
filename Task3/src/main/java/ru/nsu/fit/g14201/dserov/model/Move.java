package ru.nsu.fit.g14201.dserov.model;

import java.util.*;

/**
 * Created by dserov on 14/04/16.
 */
public class Move {
    private Map<Cell, Tile> buffer;

    public Move() {
        buffer = new HashMap<>();
    }

    public Move(Cell firstCell, Tile firstTile) {
        buffer = new HashMap<>();
        buffer.put(firstCell, firstTile);
    }

    public void add(Cell cell, Tile tile) {
        buffer.put(cell, tile);
    }

    public Tile remove(Cell cell) {
        return buffer.remove(cell);
    }

    public int getSize() {
        return buffer.size();
    }

    public boolean covers(int x, int y) {
        for (Cell c : buffer.keySet()) {
            if (c.getX() == x && c.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public Collection<Tile> tiles() {
        return buffer.values();
    }

    public void clear() {
        buffer.clear();
    }

    public void makeMove() {
        for (Map.Entry<Cell, Tile> entry : buffer.entrySet()) {
            entry.getKey().setTile(entry.getValue());
        }
    }

    public int evalMove(Board board, Dictionary dict, boolean first) throws ScrabbleException {
        ArrayList<Cell> cells = new ArrayList<>(buffer.keySet());
        int size = cells.size();
        int x = cells.get(0).getX();
        int y = cells.get(0).getY();

        if (size > 1) {
            // check direction consistency & H8 coverage for the first turn
            int direction = (y == cells.get(1).getY()) ? 1 : 0;
            boolean h8covered = false;
            for (int i = 0; i < size; i++) {
                if (direction == 1) {
                    if (y != cells.get(i).getY()) throw new WrongPlacementException("The tiles should maintain a common direction.");
                } else {
                    if (x != cells.get(i).getX()) throw new WrongPlacementException("The tiles should maintain a common direction.");
                }
                if (cells.get(i).getX() == 7 && cells.get(i).getY() == 7) h8covered = true;
            }
            if (first && !h8covered) throw new WrongPlacementException("The first turn should cover the central cell.");

            // check for empty cells
            Collections.sort(cells);
            if (direction == 1) {
                for (int i = cells.get(0).getX(); i <= cells.get(size - 1).getX(); i++) {
                    if (!board.isOccupied(i, y) && !cells.contains(board.getCell(i, y)))
                        throw new WrongPlacementException("The tiles have to form a continuous line.");
                }
            } else {
                for (int i = cells.get(0).getY(); i <= cells.get(size - 1).getY(); i++) {
                    if (!board.isOccupied(x, i) && !cells.contains(board.getCell(x, i)))
                        throw new WrongPlacementException("The tiles have to form a continuous line.");
                }
            }
        }

        if (size == 1 && first) throw new WrongPlacementException("Single-letter words are not allowed on the first turn.");

        // build words
        Set<List<Integer>> coords = new HashSet<>();
        boolean adjacent = false;
        for (Cell c : cells) {
            int xc = c.getX();
            int yc = c.getY();
            int x1, x2, y1, y2;
            x1 = x2 = xc;
            y1 = y2 = yc;

            int i = xc;
            while (i >= 0 && (board.isOccupied(i, yc) || cells.contains(board.getCell(i, yc)))) {
                adjacent |= board.isOccupied(i, yc);
                x1 = i;
                i--;
            }
            i = xc;
            while (i < 15 && (board.isOccupied(i, yc) || cells.contains(board.getCell(i, yc)))) {
                adjacent |= board.isOccupied(i, yc);
                x2 = i;
                i++;
            }
            if (x1 != x2) {
                String word = getWord(board, x1, yc, x2, yc);
                if (!dict.isValidWord(word)) throw new NoSuchWordException(word);
                else coords.add(Arrays.asList(x1, yc, x2, yc));
            }

            i = yc;
            while (i >= 0 && (board.isOccupied(xc, i) || cells.contains(board.getCell(xc, i)))) {
                adjacent |= board.isOccupied(xc, i);
                y1 = i;
                i--;
            }
            i = yc;
            while (i < 15 && (board.isOccupied(xc, i) || cells.contains(board.getCell(xc, i)))) {
                adjacent |= board.isOccupied(xc, i);
                y2 = i;
                i++;
            }
            if (y1 != y2) {
                String word = getWord(board, xc, y1, xc, y2);
                if (!dict.isValidWord(word)) throw new NoSuchWordException(word);
                else coords.add(Arrays.asList(xc, y1, xc, y2));
            }
        }
        if (!first && !adjacent) throw new WrongPlacementException("Standalone words are not allowed.");

        // evaluate words
        int res = 0;
        for (List<Integer> word : coords) {
            int wordScore = 0;
            int wordMult = 1;
            for (int i = word.get(0); i <= word.get(2); i++) {
                for (int j = word.get(1); j <= word.get(3); j++) {
                    boolean occupied = board.isOccupied(i, j);
                    Cell cell = board.getCell(i, j);
                    Tile tile = (occupied) ? cell.getTile() : buffer.get(cell);
                    wordScore += tile.getValue() * ((!occupied) ? cell.getLetterMult() : 1);
                    if (!occupied)
                        wordMult *= cell.getWordMult();
                }
            }
            res += wordScore * wordMult;
        }
        if (size == 7) res += 50;
        return res;
    }

    private String getWord(Board board, int x1, int y1, int x2, int y2) {
        StringBuilder builder = new StringBuilder(15);
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                String letter = board.isOccupied(i, j) ?
                        board.getCell(i, j).getTile().getLetter() : buffer.get(board.getCell(i, j)).getLetter();
                builder.append(letter);
            }
        }
        return builder.toString();
    }



}
