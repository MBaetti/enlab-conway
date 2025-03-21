package ch.hslu.enlab.conway;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    void emptyWorldStaysEmpty() {
        World world = new World().nextGeneration();
        assertEquals(world(""), world.showWorld());
    }

    @Test
    void printWorld() {
        World world = new World("X X", " X ", "X X");
        assertEquals(world("X X,\n X ,\nX X"), world.showWorld());
    }

    @Test
    void singleCellDies() {
        World world = new World("X").nextGeneration();
        assertEquals(world(" "), world.showWorld());
    }

    private static String world(String content) {
        return "World{\n" +content +"\n}";
    }

    @Test
    void twoCellsDie() {
        World world = new World("XX").nextGeneration();
        assertEquals(world("  "), world.showWorld());
    }

    @Test
    void oscillatingPattern() {
        // Test a blinker pattern (period 2 oscillator)
        World world = new World("   ", " XXX", "   ");
        World nextGen = world.nextGeneration();
        assertEquals(world("  X,\n  X ,\n  X"), nextGen.showWorld());
        
        // Should return to original state after another generation
        World thirdGen = nextGen.nextGeneration();
        assertEquals(world("   ,\n XXX,\n   "), thirdGen.showWorld());
    }

    @Test
    void oscillatingPattern2() {
        // Test a blinker pattern (period 2 oscillator)
        World world = new World(" X ", " X ", " X ");
        World nextGen = world.nextGeneration();
        assertEquals(world("   ,\nXXX,\n   "), nextGen.showWorld());
    
        // Should return to original state after another generation
        World thirdGen = nextGen.nextGeneration();
        assertEquals(world(" X ,\n X ,\n X "), thirdGen.showWorld());
    }

    @Test
    void liveCellWithTwoNeighboursSurvives() {
        // A live cell with 2 neighbors should survive
        World world = new World(
                " X ",
                "XX ",
                "   "
        ).nextGeneration();
        assertEquals(world("XX ,\nXX ,\n   "), world.showWorld());
    }

    @Test
    void liveCellWithThreeNeighboursSurvives() {
        // A live cell with 3 neighbors should survive
        World world = new World(
                "XX ",
                "X  ",
                "   "
        ).nextGeneration();
        assertEquals(world("XX ,\nXX ,\n   "), world.showWorld());
    }

    @Test
    void liveCellWithLessThanTwoNeighboursDies() {
        // A live cell with fewer than 2 neighbors dies (underpopulation)
        World world = new World(
                "X  ",
                " X ",
                "   "
        ).nextGeneration();
        assertEquals(world("   ,\n   ,\n   "), world.showWorld());
    }

    @Test
    void liveCellWithMoreThanThreeNeighboursDies() {
        // A live cell with more than 3 neighbors dies (overpopulation)
        World world = new World(
                "XXX",
                "XXX",
                "   "
        ).nextGeneration();
        assertEquals(world("X X,\nX X,\n X "), world.showWorld());
    }

    @Test
    void deadCellWithExactlyThreeNeighboursBecomesAlive() {
        // A dead cell with exactly 3 neighbors becomes alive (reproduction)
        World world = new World(
                "XX ",
                "X  ",
                "   "
        ).nextGeneration();
        assertEquals(world("XX ,\nXX ,\n   "), world.showWorld());
    }

    @Test
    void deadCellWithLessThanThreeNeighboursStaysDead() {
        // A dead cell with fewer than 3 neighbors stays dead
        World world = new World(
                "X  ",
                "X  ",
                "   "
        ).nextGeneration();
        assertEquals(world("   ,\n   ,\n   "), world.showWorld());
    }

    @Test
    void deadCellWithMoreThanThreeNeighboursStaysDead() {
        // A dead cell with more than 3 neighbors stays dead
        World world = new World(
                "XXX",
                "X X",
                "   "
        ).nextGeneration();
        assertEquals(world("X X,\nX X,\n   "), world.showWorld());
    }

    @Test
    void stillLifeBlockPattern() {
        // Test a block pattern (2x2 square) - should remain stable
        World world = new World(
                "XX",
                "XX"
        );
        World nextGen = world.nextGeneration();
        assertEquals(world("XX,\nXX"), nextGen.showWorld());
        
        // Should remain stable in subsequent generations
        World thirdGen = nextGen.nextGeneration();
        assertEquals(world("XX,\nXX"), thirdGen.showWorld());
    }

    @Test
    void stillLifeBeehivePattern() {
        // Test a beehive pattern - should remain stable
        World world = new World(
                " XX ",
                "X  X",
                " XX "
        );
        World nextGen = world.nextGeneration();
        assertEquals(world(" XX ,\nX  X,\n XX "), nextGen.showWorld());
    }

    @Test
    void gliderPattern() {
        // Test a glider pattern - moves diagonally
        World world = new World(
                "  X ",
                "X X ",
                " XX ",
                "    "
        );
        World nextGen = world.nextGeneration();
        assertEquals(world(" X  ,\n  XX,\n XX ,\n    "), nextGen.showWorld());
        
        World thirdGen = nextGen.nextGeneration();
        assertEquals(world("  X ,\n   X,\n XXX,\n    "), thirdGen.showWorld());
    }
}