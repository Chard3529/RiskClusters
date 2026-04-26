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
        // Luckily we know the col cannot be equal. 
        if (this.cells.peek().col < other.cells.peek().col) return 1;
        if (this.cells.peek().col > other.cells.peek().col) return -1;

        return 0;
            
    }
   
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Direction: ").append(direction).append("\n");
        stringBuilder.append("Total Risk Value: ").append(totalRiskValue).append("\n");
        stringBuilder.append("Average Risk Value: ").append(averageRiskValue()).append("\n");
        stringBuilder.append("Cells: ");
        for (Cell c : cells){
            stringBuilder.append("[").append(c.row).append("]");
            stringBuilder.append("[").append(c.col).append("]");
            stringBuilder.append(":").append(c.riskValue).append(" ");
        }
        stringBuilder.append("\n").append("Direction: ").append(direction);
        return stringBuilder.toString();
    }
}
