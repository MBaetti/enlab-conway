package ch.hslu.enlab.conway;

import java.util.Arrays;
import java.util.stream.Collectors;

public class World {

    public static void main(String[] args) {
        World world = new World("XX ", "X  ", "   ");
        world = world.nextGeneration();
        System.out.println(world.showWorld());
        System.out.println("ToString-Method: " + world);
    }

    private final boolean[][] cells;

    /**
     * Create a new world with the given rows where a 'X' represents a living cell and a ' ' represents a dead cell.
     * Example: new World("X X", " X ", "X X");
     * Result:
     * X X
     *  X
     * X X
     */
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

    /**
     * Create a new world with the given cells where a 'true' represents a living cell and a 'false' represents a dead cell.
     * Example: new World(new boolean[][]{{true, false, true}, {false, true, false}, {true, false, true}});
     * Result:
     * X X
     *  X
     * X X
     */
    public World(boolean[][] cells) {
        this.cells = cells;
    }

    /**
     * Calculate the next generation of the world based on the rules of Conway's Game of Life.
     */
    public World nextGeneration() {
        boolean[][] next = new boolean[cells.length][];
        for (int y = 0; y < cells.length; y++) {
            next[y] = new boolean[cells[y].length];
            for (int x = 0; x < cells[y].length; x++) {
                int aliveNeighbours = countAliveNeighbours(y,x);
                next[y][x] = liveAndDeadCellsRule(aliveNeighbours, y, x);
            }
        }
        return new World(next);
    }

    /**
     * Check if the cell is alive or dead based on the number of alive neighbours.
     */
    private boolean liveAndDeadCellsRule(int aliveNeighbours, int y, int x) {
        if (cells[y][x]) {
            // Rules alive cell
            return (aliveNeighbours == 2 || aliveNeighbours == 3);
        } else {
            // Rules dead cell
            return (aliveNeighbours == 3);
        }
    }

    /**
     * Count the number of alive neighbours around the cell in a 3x3 grid.
     */
    private int countAliveNeighbours(int y, int x) {
        int count = 0;
        for (int dy = y-1; dy <= y+1; dy++) {
            for (int dx = x-1; dx <= x+1; dx++) {
                        if (checkWorldBoarder(dy, dx) && checkIfCurrentCell(dy, dx, y, x) && checkIfCellIsAlive(dy, dx)) count++;
            }
        }
        return count;
    }

    /**
     * Check if the cell is within the bounds of the world: Y-axis and X-axis.
     */
    private boolean checkWorldBoarder(int y, int x) {
        return y >= 0 && y < cells.length && x >= 0 && x < cells[y].length;
    }

    /**
     * Check if the cell is not the current cell.
     */
    private boolean checkIfCurrentCell(int yToCheck, int xToCheck, int yCurrent, int xCurrent) {
        return yToCheck != yCurrent || xToCheck != xCurrent;
    }

    /**
     * Check if the cell is alive.
     */
    private boolean checkIfCellIsAlive(int y, int x) {
        return cells[y][x];
    }

    /**
     * Show the world as a string where a 'X' represents a living cell and a ' ' represents a dead cell.
     */
    public String showWorld() {
        return "World" + Arrays.stream(cells)
                .map(row -> {
                    String rowString = "";
                    for (boolean b : row) {
                        if (b) {
                            rowString += 'X';
                        } else {
                            rowString += ' ';
                        }
                    }
                    return rowString;
                })
                .collect(Collectors.joining(",\n", "{\n", "\n}"));
    }

    @Override
    public String toString() {
        return "World{" +
                "cells=" + Arrays.toString(cells) +
                '}';
    }
}
