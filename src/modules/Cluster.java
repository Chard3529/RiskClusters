package src.modules;

import java.util.LinkedList;

public class Cluster {

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

   
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cells: ");
        for (Cell c : cells){
            stringBuilder.append("[").append(c.row).append("]");
            stringBuilder.append("[").append(c.col).append("]");
            stringBuilder.append(":").append(c.riskValue).append(" ");
        }
        stringBuilder.append("\n").append("Direction: ").append(direction);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
