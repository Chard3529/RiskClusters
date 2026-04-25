package src.modules;

import java.lang.StringBuilder;
import java.util.List;

public class RiskGrid {

    // Variables:
    private int[][] grid;

    // Getters and setters:
    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

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
