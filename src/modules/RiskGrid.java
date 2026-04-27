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

    public void printSignificantClusters() {
        int significantClustersAmount = 0;
        for (Cluster c : clusters) {
            if (c.isSignificant()) {
                significantClustersAmount += 1;
                System.out.println(c);
            }
        }
        System.out.println("Total Clusters: " + clusters.size());
        System.out.println("Significant Clusters: " + significantClustersAmount);
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
        // If there is no string creates an empty matrix
        if (lineStrings.isEmpty()) {
            System.out.print("Error creating grid, lineString is empty.");
            return new int[0][0];
        }

        try {
            int n = lineStrings.size();
            int[][] grid = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] cellStrings = lineStrings.get(i).split(" ");
                if (cellStrings.length > n || cellStrings.length < n) {
                    throw new IllegalArgumentException("File must contain a square NxN grid"); 
                }
                
                for (int j = 0; j < n; j++) {
                    int value = Integer.parseInt(cellStrings[j]);
                    grid[i][j] = value;
                }
            }
            return grid;
        
        } 
        // If provided file has anything else than numbers with spaces between them we get empty array
        catch (NumberFormatException e) {
            System.out.print("Could not parse number " + e.getMessage() + "\n");
            return new int[0][0];
        }
        // If the grid is not NxN this is thrown
        catch (IllegalArgumentException e) {
            System.out.print(e.getMessage() + "\n");
            return new int[0][0];
        }

    };

    // Is called by constructor. Calls the bellow function for every single index in the grid. 
    private ArrayList<Cluster> createClustersForGrid() {
        ArrayList<Cluster> clusters = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                clusters.addAll(createClustersFromIndex(i,j));
            }
        }

        // Sorts clusters according to the given criteria 
        // using the compareTo method found in the cluster class.
        clusters.sort(null);
        
        return clusters;
    }


    // Returns a list of all clusters that can be formed from a given index.
    // Using the clusterSize variable 
    private ArrayList<Cluster> createClustersFromIndex(int row, int col) {

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
