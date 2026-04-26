package src.modules;

import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class RiskGrid {

    // Variables:
    private int[][] grid;


    // Constructor, calls makeGridFromLineStrings function to setGrid
    public RiskGrid(List<String> lineStrings) {
        this.grid = makeGridFromLineStrings(lineStrings);
    }

    // Private method that creates a grid from lineStrings
    private static int[][] makeGridFromLineStrings(List<String> lineStrings) {
        if (lineStrings.isEmpty()) {
            return new int[0][0];
        }

        int n = lineStrings.size();

        int[][] grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] cellStrings = lineStrings.get(i).split(" ");
            
            for (int j = 0; j < n; j++) {
                int value = Integer.parseInt(cellStrings[j]);
                grid[i][j] = value; 
            }
        }

        return grid;
    };

    private boolean outOfBounds(int row, int col) {
        int n = grid.length;

        if (row < 0 || col < 0) return true;

        if (row >= n || col >= n) return true;

        return false;
    }

    // Returns a list of all clusters at a given point 
    public ArrayList<Cluster> createClustersFromIndex(int row, int col) {

        ArrayList<Cluster> clusters = new ArrayList<>();

        // North 
        if (!outOfBounds((row - 3), col)) {
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = row; i >= 0; i--) {
                cells.addLast(new Cell(i, col, grid[i][col]));
            }
            clusters.add(new Cluster(cells, "N"));
        }

        // NorthEast
        if (!outOfBounds((row -3), (col + 3))) {
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = 0; i < 4; i++) {
                int curRow = row - i;
                int curCol = col + i;
                cells.add(new Cell(curRow,curCol,grid[curRow][curCol]));
            }
            clusters.add(new Cluster(cells, "NE"));
        }

        // East
        if (!outOfBounds(row, (col + 3))) {
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = 0; i < 4; i++) {
                int curCol = col + i;
                cells.add(new Cell(row, curCol, grid[row][curCol]));
            }
            clusters.add(new Cluster(cells, "E"));
        }

        // South East
        if (!outOfBounds((row + 3), (col + 3))) {
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = 0; i < 4; i++) {
                int curRow = row + i;
                int curCol = col + i;
                cells.add(new Cell(curRow, curCol, grid[curRow][curCol]));
            }
            clusters.add(new Cluster(cells, "SE"));
        }
    

        return clusters;

    }

    
    // Overide toString for printing the grid
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int value = grid[i][j];

                if(value < 10) stringBuilder.append("0");
                stringBuilder.append(value);
                stringBuilder.append(" ");    
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
