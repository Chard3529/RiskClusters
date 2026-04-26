package src;

import java.io.IOException;

// Java imports:

// File handling
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

// Util
import java.util.List;

import src.modules.Cluster;
// Local modules:
import src.modules.RiskGrid;

public class Main {
    
   private static List<String> readFile(String fileName) {
        try {
            Path path = Path.of(fileName);
            return Files.readAllLines(path);
        }
        catch (InvalidPathException e) {
            System.out.println("Error getting path from" + fileName);
            e.printStackTrace();
            return List.of();
        }
        catch (IOException e) {
            System.out.println("Cannot read provided file");
            e.printStackTrace();
            return List.of();
        }
   } 


    public static void main(String[] args) {

    
        // List<String> lineStrings = readFile(args[0]);
        List<String> lineStrings = readFile("testgrid.txt");

        RiskGrid rGrid = new RiskGrid(lineStrings);
        
        List<Cluster> clusters = rGrid.createClustersFromIndex(1, 1);

        for (Cluster c : clusters){
            System.out.print(c);
        }
        
    }
}
