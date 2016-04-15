package ru.nsu.fit.g14201.dserov;

import java.util.*;

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

    public int evalMove(Board board, Dictionary dict, boolean first) {
        ArrayList<Cell> cells = new ArrayList<>(buffer.keySet());
        int size = cells.size();
        int x = cells.get(0).getX();
        int y = cells.get(0).getY();

        if (size > 1) {
            // check direction consistency & H8 coverage for the first turn
            int direction = (y == cells.get(1).getY()) ? 1 : 0;
            boolean h8covered = false;
            for (int i = 1; i < size; i++) {
                if (direction == 1) {
                    if (y != cells.get(i).getY()) return -1;
                } else {
                    if (x != cells.get(i).getX()) return -1;
                }
                if (first && cells.get(i).getX() == 7 && cells.get(i).getY() == 7) h8covered = true;
            }
            if (first && !h8covered) return -1;

            // check for empty cells
            Collections.sort(cells);
            if (direction == 1) {
                for (int i = cells.get(0).getX(); i <= cells.get(size - 1).getX(); i++) {
                    if (!board.isOccupied(i, y) && !cells.contains(board.getCell(i, y))) return -1;
                }
            } else {
                for (int i = cells.get(0).getY(); i <= cells.get(size - 1).getY(); i++) {
                    if (!board.isOccupied(x, i) && !cells.contains(board.getCell(x, i))) return -1;
                }
            }
        }

        if (size == 1 && first) return -1;

        // build words
        Set<List<Integer>> coords = new HashSet<>();
        for (Cell c : cells) {
            int xc = c.getX();
            int yc = c.getY();
            int x1, x2, y1, y2;
            x1 = x2 = xc;
            y1 = y2 = yc;

            int i = xc;
            while (i >= 0 && (board.isOccupied(i, yc) || cells.contains(board.getCell(i, yc)))) {
                x1 = i;
                i--;
            }
            i = xc;
            while (i < 15 && (board.isOccupied(i, yc) || cells.contains(board.getCell(i, yc)))) {
                x2 = i;
                i++;
            }
            if (x1 != x2) {
                String word = getWord(board, x1, yc, x2, yc);
                if (!dict.isValidWord(word)) return 0;
                else coords.add(Arrays.asList(x1, yc, x2, yc));
            }

            i = yc;
            while (i >= 0 && (board.isOccupied(xc, i) || cells.contains(board.getCell(xc, i)))) {
                y1 = i;
                i--;
            }
            i = yc;
            while (i < 15 && (board.isOccupied(xc, i) || cells.contains(board.getCell(xc, i)))) {
                y2 = i;
                i++;
            }
            if (y1 != y2) {
                String word = getWord(board, xc, y1, xc, y2);
                if (!dict.isValidWord(word)) return 0;
                else coords.add(Arrays.asList(xc, y1, xc, y2));
            }
        }

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

    public boolean checkMove(Board board, boolean first) {
        ArrayList<Cell> cells = new ArrayList<>(buffer.keySet());
        int size = cells.size();
        int x = cells.get(0).getX();
        int y = cells.get(0).getY();

        if (size > 1) {
            // check direction consistency & H8 coverage for the first turn
            int direction = (y == cells.get(1).getY()) ? 1 : 0;
            int max = (direction == 1) ? x : y;
            int min = max;
            boolean h8covered = false;
            for (int i = 1; i < size; i++) {
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
                int xleft, xright, ytop, ybottom;
                if (direction == 1) {
                    ytop = (y - 1 < 0) ? 0 : y;
                    ybottom = (y + 1 > 14) ? 14 : y;
                    xleft = (min - 1 < 0) ? 0 : min - 1;
                    xright = (max + 1 > 14) ? 14 : max + 1;
                } else {
                    ytop = (min - 1 < 0) ? 0 : min - 1;
                    ybottom = (max + 1 > 14) ? 14 : max + 1;
                    xleft = (x - 1 < 0) ? 0 : x - 1;
                    xright = (x + 1 > 14) ? 14 : x + 1;
                }

                for (int i = xleft; i <= xright; i++) {
                    for (int j = ytop; j <= ybottom; j++) {
                        if ((i == xleft && j == ybottom)
                                || (i == xleft && j == ytop)
                                || (i == xright && j == ybottom)
                                || (i == xright && j == ytop)) {
                            continue;
                        }
                        if (!cells.contains(board.getCell(i, j)) && board.isOccupied(i, j))  {
                            adjacent = true;
                            break;
                        }
                    }
                }
                if (!adjacent) return false;
            }
        }

        // simplified algorithm for 1 tile
        if (size == 1) {
            if (first) return false;
            int xleft = (x - 1 < 0) ? 0 : x - 1;
            int xright = (x + 1 > 14) ? 14 : x + 1;
            int ytop = (y - 1 < 0) ? 0 : y - 1;
            int ybottom = (y + 1 > 14) ? 14 : y + 1;
            if (xleft != x && board.isOccupied(xleft, y)) return true;
            if (xright != x && board.isOccupied(xright, y)) return true;
            if (ytop != y && board.isOccupied(x, ytop)) return true;
            if (ybottom != y && board.isOccupied(x, ybottom)) return true;
            return false;
        }
        return true;
    }



}
