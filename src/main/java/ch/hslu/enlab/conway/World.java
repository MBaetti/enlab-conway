package ch.hslu.enlab.conway;

import java.util.Arrays;
import java.util.stream.Collectors;

public class World {


    private final boolean[][] cells;

    public World(String... rows) {
        cells = new boolean[rows.length][];
        for (int y = 0; y < rows.length; y++) {
            char[] chars = rows[y].toCharArray();
            cells[y] = new boolean[chars.length];
            for (int x = 0; x < chars.length ; x++) {
                cells[y][x] = 'X' == chars[x];
            }
        }
    }

    public World(boolean[][] cells) {
        this.cells = cells;
    }

    public World nextGeneration() {
        boolean[][] next = new boolean[cells.length][];
        for (int y = 0; y < cells.length; y++) {
            next[y] = new boolean[cells[y].length];
            for (int x = 0; x < cells[y].length; x++) {
                int aliveNeighbours = countAliveNeighbours(y,x);
                if (cells[y][x]) {
                    // Live cell rules
                    if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        next[y][x] = true; // Survival: cell with 2 or 3 neighbors survives
                    } else {
                        next[y][x] = false; // Death: cell with <2 or >3 neighbors dies
                    }
                } else {
                    // Dead cell rules
                    if (aliveNeighbours == 3) {
                        next[y][x] = true; // Reproduction: dead cell with exactly 3 neighbors becomes alive
                    } else {
                        next[y][x] = false; // Dead cell stays dead
                    }
                }
            }
        }

        return new World(next);
    }

    private int countAliveNeighbours(int y, int x) {
        int count = 0;
        for (int dy = y-1; dy <= y+1; dy++) {
            for (int dx = x-1; dx <= x+1; dx++) {
                if (dy >= 0 && dy < cells.length && dx >= 0 && dx < cells[dy].length) {
                    if (dy != y || dx != x) {
                        if (cells[dy][dx]) count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "World" + Arrays.stream(cells)
                .map(row -> {
                    String rowString = "";
                    for (boolean b : row) {
                        if (b) {
                            rowString += 'X';
                        } else {
                            rowString += ' ';
                        };
                    }
                    return rowString;
                })
                .collect(Collectors.joining(",\n", "{\n", "\n}"));
    }
}
