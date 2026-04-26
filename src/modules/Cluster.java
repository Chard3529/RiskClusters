package src.modules;

import java.util.LinkedList;


public class Cluster implements Comparable<Cluster>  {

    public LinkedList<Cell> cells;

    public String direction;

    public int totalRiskValue;

    public double averageRiskValue() {
        return totalRiskValue / cells.size();
    }
    
    public boolean isSignificant() {
        return averageRiskValue() >= 70;
    }

    public Cluster(LinkedList<Cell> cells, String direction) {
        this.cells = cells;
        this.direction = direction;
        
        // Go trough cells to get the total risk value:
        int sum = 0;
        for (Cell cell : cells) {
            sum += cell.riskValue;
        }
        this.totalRiskValue = sum;
    }


    private int findHighestCellValue(LinkedList<Cell> cells){
        int max = 0;
        for (Cell c : cells) {
            if (c.riskValue > max) {
                max = c.riskValue;
            }
        }
        return max;
    }

    @Override
    public int compareTo(Cluster other) {

        // First we compare the average risk value 
        if (this.averageRiskValue() > other.averageRiskValue()) return 1;
        if (this.averageRiskValue() < other.averageRiskValue()) return -1;
        
        // If they are equal we must first find the highest individual cell value:
        int thisMaxCellValue = findHighestCellValue(this.cells);
        int otherMaxCellValue = findHighestCellValue(other.cells);
       
        if (thisMaxCellValue > otherMaxCellValue) return 1;
        if (thisMaxCellValue < otherMaxCellValue) return -1;

        // If we end up here the max individual cell value is the same. 
        // so we prioritize the cluster with the earliest starting position. 

        if (this.cells.peek().row < other.cells.peek().row) return 1;
        if (this.cells.peek().row > other.cells.peek().row) return -1;

        // If we end up here this means the rows are equal, so we must look at col
        if (this.cells.peek().col < other.cells.peek().col) return 1;
        if (this.cells.peek().col > other.cells.peek().col) return -1;

        // On the extreme off chance that the clusters also share the same index
        // They are deemed to be of the same priority
        return 0;       
    }
   
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        // Prints direction first. Adds extra space if direction only has one character.
        stringBuilder.append(direction);
        if (direction.length() == 1) stringBuilder.append(" ");
        stringBuilder.append(": ");

        // Prints indices and risk value for a given cell 
        // Adds extra zeroes for single values.
        for (Cell c : cells){
            stringBuilder.append("[");
            if (c.row < 10) stringBuilder.append(0);
            stringBuilder.append(c.row).append("][");
            if (c.col < 10) stringBuilder.append(0);
            stringBuilder.append(c.col).append("]");
            stringBuilder.append(": ");
            if (c.riskValue < 10) stringBuilder.append(0);
            stringBuilder.append(c.riskValue).append(" ");
        }
        stringBuilder.append(" AvgRisk: ").append(averageRiskValue());
        return stringBuilder.toString();
    }
}
