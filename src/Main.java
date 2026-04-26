package src;

import java.util.List;

import src.modules.RiskGrid;
import src.modules.Cluster;
import static src.util.InputHandler.parseArguments;
import static src.util.InputHandler.readFile;



public class Main {
    
    public static void main(String[] args) {

        List<String> lineStrings;

        try {
            String filename = parseArguments(args);
            lineStrings = readFile(filename);
        }
        // If anything is wrong with user input error is printed and program is stopped
        catch (IllegalArgumentException e) {
            System.out.print(e.getMessage() + "\n");;
            return;
        }
    
        RiskGrid rGrid = new RiskGrid(lineStrings, 4);

        List<Cluster> allClusters = rGrid.getAllClusters();
        List<Cluster> significantClusters = rGrid.getSignificantClusters();

        System.out.println("Total clusters: " + allClusters.size());
        System.out.println("Significant Clusters: " + significantClusters.size());

        
    
        
    }
}
