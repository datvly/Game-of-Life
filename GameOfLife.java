
import java.util.Arrays;
import java.util.Scanner;

/**
 * Simple version of Conway's Game of Life.
 */
public class GameOfLife {

    public static void main(String[] args) {      
        // true if occupied, false if not
        boolean[][] theWorld = createWorld(15, 30, 0.3182378712);
        printWorld(theWorld);
        runSim(theWorld);
    }

    /*
     * Run the simulation. Pause after each generation
     * and wait for user to press enter.
     */
    public static void runSim(boolean[][] world) {
        Scanner kb = new Scanner(System.in);
        int gen = 0;
        boolean nextGen = true;
        while (nextGen) {
            System.out.println();
            System.out.print("Press enter for next gen, anything else to quit: ");
            nextGen = kb.nextLine().length() == 0;
            gen++;
            System.out.println("generation: " + gen);
            world = getNextGen(world);
            printWorld(world);
        }
    }

    // Determine and return the next generation.
    public static boolean[][] getNextGen(boolean[][] currentGen) {
        boolean[][] nextGen = new boolean[currentGen.length][currentGen[0].length];
        for (int r = 0; r < nextGen.length; r++) {
            for (int c = 0; c < nextGen[0].length; c++) {
                // current cell is currentGen[r][c]
                // How many neighbors are occupied?
                int numNeigh = getNumNeigh(currentGen, r, c);
                nextGen[r][c] = occupiedNext(numNeigh, currentGen[r][c]);
            }
        }
        return nextGen;
    }
    
    public static boolean occupiedNext(int numNeigh, boolean occupiedThisGen) {
//        return ((!occupiedThisGen && numNeigh == 3)
//                || (occupiedThisGen && (numNeigh == 2 || numNeigh == 3)));
        return (numNeigh == 3 || (occupiedThisGen && numNeigh == 2));
    }

    // how many of 8 neighbors of currentGen[row][col] are occupied?
    public static int getNumNeigh(boolean[][] currentGen, int row, int col) {
        int neighbors = 0;
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (inbounds(r, c, currentGen) && currentGen[r][c]) {
                    neighbors++;
                }
            }
        }
        // did I count myself?
        if (currentGen[row][col]) {
            neighbors--;
        }
        return neighbors;
    }

    // return number of cells neighboring world[row][col] that are true (occupied)
 //   public static int countNumNeigh(boolean[][] world, int row, int col) {

    

    
    

    
    /* Determine if this cell is occupied in the next
     * generation or not. Recall, if initially occupied
     * survives if 2 or 3 neighbors. 
     * If not occupied birth occurs if exactly 3 neighbors. 
     * A cell, in general has 8 neighbors.
     */
  //  public static boolean occupiedNextGen(int numNeigh, 

    
    
    public static boolean inbounds(int r, int c, boolean[][] world) {
        return 0 <= r && r < world.length 
                        && 0 <= c && c < world[0].length;
    }

    // print world, use * for occupied and - for unoccupied
    public static void printWorld(boolean[][] world) {
        for (boolean[] row : world) {
            for (boolean cell : row) {
                System.out.print(cell ? "*" : "-");
            }
            System.out.println();
        }
    }

      
    // I expect rows > 0, cols > 0, 0 <= chanceOccupied <= 1.0
    // Create and return a world that is rows by columns
    // in size. 
    public static boolean[][] createWorld(int rows, int cols, 
            double chanceOccupied) {
        boolean[][] theWorld = new boolean[rows][cols];
        for (int r = 0; r < theWorld.length; r++) {
            for (int c = 0; c < theWorld[0].length; c++) {
                theWorld[r][c] = Math.random() < chanceOccupied;
            }
        }
        return theWorld;
    }
}

