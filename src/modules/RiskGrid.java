package src.modules;

import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;



public class RiskGrid {

    // Variables:
    private int[][] grid;

    private int clusterSize;

    // Clusters already stored in correct order 
    private ArrayList<Cluster> clusters;


    public ArrayList<Cluster> getAllClusters() {
        return clusters;
    }

    public ArrayList<Cluster> getSignificantClusters(){
        ArrayList<Cluster> significantClusters = new ArrayList<>();
        for (Cluster c : clusters) {
            if (c.isSignificant()) {
                significantClusters.add(c);
            }
        }
        return significantClusters;
    }


    // Constructor, calls makeGridFromLineStrings function to set the grid,
    // as well as createClustersFromGrid function to set the clusters array
    public RiskGrid(List<String> lineStrings, int clusterSize) {
        this.grid = makeGridFromLineStrings(lineStrings);
        this.clusterSize = clusterSize;
        this.clusters = createClustersForGrid();
        
    }

    // Is called by constructor creates a grid from lineStrings
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

    // Is called by constructor. Calls the bellow function for every single index in the grid. 
    private ArrayList<Cluster> createClustersForGrid() {
        ArrayList<Cluster> clusters = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                clusters.addAll(createClustersFromIndex(i,j, clusterSize));
            }
        }

        clusters.sort(null);
        return clusters;
    }


    // Returns a list of all clusters from given indices 
    private ArrayList<Cluster> createClustersFromIndex(int row, int col, int clusterSize) {

        ArrayList<Cluster> clusters = new ArrayList<>();

        // North 
        if (!outOfBounds((row - (clusterSize - 1)), col)) {
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = 0; i < clusterSize; i++) {
                int curRow = row - i;
                cells.addLast(new Cell(curRow, col, grid[curRow][col]));
            }
            clusters.add(new Cluster(cells, "N"));
        }

        // NorthEast
        if (!outOfBounds((row - (clusterSize - 1)), (col + (clusterSize - 1)))) {
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = 0; i < clusterSize; i++) {
                int curRow = row - i;
                int curCol = col + i;
                cells.addLast(new Cell(curRow,curCol,grid[curRow][curCol]));
            }
            clusters.add(new Cluster(cells, "NE"));
        }

        // East
        if (!outOfBounds(row, (col + (clusterSize - 1)))) {
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = 0; i < clusterSize; i++) {
                int curCol = col + i;
                cells.addLast(new Cell(row, curCol, grid[row][curCol]));
            }
            clusters.add(new Cluster(cells, "E"));
        }

        // South East
        if (!outOfBounds((row + (clusterSize - 1)), (col + (clusterSize - 1)))) {
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = 0; i < clusterSize; i++) {
                int curRow = row + i;
                int curCol = col + i;
                cells.addLast(new Cell(curRow, curCol, grid[curRow][curCol]));
            }
            clusters.add(new Cluster(cells, "SE"));
        }
    
        // South 
        if (!outOfBounds(row + (clusterSize - 1), col)){
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = 0; i < clusterSize; i++) {
                int curRow = row + i;
                cells.addLast(new Cell(curRow, col, grid[curRow][col]));
            }
            clusters.add(new Cluster(cells, "S"));
        }
        

        // South West
        if (!outOfBounds(row + (clusterSize - 1), col - (clusterSize - 1))) {
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = 0; i < clusterSize; i++) {
                int curRow = row + i;
                int curCol = col - i;
                cells.addLast(new Cell(curRow, curCol, grid[curRow][curCol]));
            }
            clusters.add(new Cluster(cells, "SW"));
        }

        // West 
        if (!outOfBounds(row, col - (clusterSize - 1))) {
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = 0; i < clusterSize; i++) {
                int curCol = col - i;
                cells.addLast(new Cell(row, curCol, grid[row][curCol]));
            }
            clusters.add(new Cluster(cells, "W"));

        }

        // North West
        if (!outOfBounds(row - (clusterSize - 1), col - (clusterSize - 1))) {
            LinkedList<Cell> cells = new LinkedList<>();
            for (int i = 0; i < clusterSize; i++) {
                int curRow = row - i;
                int curCol = col - i;
                cells.addLast(new Cell(curRow, curCol, grid[curRow][curCol]));
            }
            clusters.add(new Cluster(cells, "NW"));
        }

        return clusters;
    }

    // Returns true if index is out of bounds for given grid 
    private boolean outOfBounds(int row, int col) {
        int n = grid.length;

        if (row < 0 || col < 0) return true;

        if (row >= n || col >= n) return true;

        return false;
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
