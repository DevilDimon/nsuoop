package ru.nsu.fit.g14201.dserov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dserov on 14/04/16.
 */
public class Move {
    private Map<Cell, Tile> buffer;

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

    public void clear() {
        buffer.clear();
    }

    public void makeMove() {
        for (Map.Entry<Cell, Tile> entry : buffer.entrySet()) {
            entry.getKey().setTile(entry.getValue());
        }
    }

    public boolean checkMove(Board board) {
        return checkMove(board, false);
    }

    public boolean checkMove(Board board, boolean first) {
        ArrayList<Cell> cells = new ArrayList<>(buffer.keySet());
        int size = cells.size();
        int x = cells.get(0).getX();
        int y = cells.get(0).getY();
        int xleft = (x - size < 0) ? 0 : x;
        int xright = (x + size > 14) ? 14 : x;
        int ybottom = (y - size < 0) ? 0 : y;
        int ytop = (y + size > 14) ? 14 : y;

        if (cells.size() > 1) {
            // check direction consistency & H8 coverage for the first turn
            int direction = (y == cells.get(1).getY()) ? 1 : 0;
            int max = (direction == 1) ? y : x;
            int min = max;
            boolean h8covered = false;
            for (int i = 1; i < cells.size(); i++) {
                if (direction == 1) {
                    if (y != cells.get(i).getY()) return false;
                    int curX = cells.get(i).getX();
                    if (curX > max) max = curX;
                    if (curX < min) min = curX;
                } else {
                    if (x != cells.get(i).getX()) return false;
                    int curY = cells.get(i).getY();
                    if (curY > max) max = curY;
                    if (curY < min) min = curY;
                }
                if (first && cells.get(i).getX() == 7 && cells.get(i).getY() == 7) h8covered = true;
            }
            if (first && !h8covered) return false;

            // check skipping tiles
            for (int i = min; i <= max; i++) {
                if (direction == 1) {
                    if (!cells.contains(board.getCell(i, y)) && !board.isOccupied(i, y)) return false;
                } else {
                    if (!cells.contains(board.getCell(x, i)) && !board.isOccupied(x, i)) return false;
                }
            }

            // check adjacency
            if (!first) {
                boolean adjacent = false;
                for (int i = xleft; i <= xright; i++) {
                    for (int j = ybottom; j < ytop; j++) {
                        if ((i == xleft && j == ybottom)
                                || (i == xleft && j == ytop)
                                || (i == xright && j == ybottom)
                                || (i == xright && j == ytop)) {
                            continue;
                        }
                        if (cells.contains(board.getCell(i, j))) continue;
                        else {
                            if (board.isOccupied(i, j)) {
                                adjacent = true;
                                break;
                            }
                        }
                    }
                }
                if (!adjacent) return false;
            }
        }

        // simplified algorithm for 1 tile
        if (cells.size() == 1) {
            if (first) return false;
            if (xleft != x && board.isOccupied(xleft, y)) return true;
            if (xright != x && board.isOccupied(xright, y)) return true;
            if (ytop != y && board.isOccupied(x, ytop)) return true;
            if (ybottom != y && board.isOccupied(x, ybottom)) return true;

        }
        return true;
    }

    public String getWords(Board board) {
        String res = "";
        return res;

    }

}
