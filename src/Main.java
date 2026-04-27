package src;

import java.util.List;
import src.modules.RiskGrid;
import src.util.InputHandler;

public class Main {
    
    public static void main(String[] args) {

        List<String> lineStrings;
        int clustersize;

        try {
            InputHandler inputHandler = new InputHandler(args);
            lineStrings = inputHandler.readFile();
            clustersize = inputHandler.getClusterSize();
        }
        // If anything is wrong with user input error is printed and program is stopped
        catch (IllegalArgumentException e) {
            System.out.print(e.getMessage() + "\n");;
            return;
        }
    
        RiskGrid rGrid = new RiskGrid(lineStrings, clustersize);

        rGrid.printSignificantClusters();

    }
}
