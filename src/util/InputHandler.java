package src.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class InputHandler {

    private String filename;
    private int clusterSize = 4;

    public int getClusterSize() {
        return clusterSize;
    }

    public List<String> readFile() {
        return readFile(filename);
    }

    public InputHandler(String[] args) {
        parseArguments(args);
    }

    private static List<String> readFile(String fileName) {
        try {
            Path path = Path.of(fileName);
            return Files.readAllLines(path);
        }
        catch (InvalidPathException e) {
            throw new IllegalArgumentException("Could not get filepath: " + fileName);
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Could not read provided file: " + fileName);
        }
    } 

    private void parseArguments(String[] args) {
        if (args.length == 0) throw new IllegalArgumentException("Must provide a filename argument!");
    
        if (args.length  > 2) throw new IllegalArgumentException("Must only provide filename and optional clusterSize!");

        // Regex for pattern any file ending in ".txt"
        String regex = ".*\\.txt$"; 
        String fname = args[0];
        if (!fname.matches(regex)) throw new IllegalArgumentException("Must provide a filename ending in \".txt\"!");

        this.filename = fname;


        // If there are two arguments the second one is clusterSize
        if (args.length == 2) {
            try {
                this.clusterSize = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input for clusterSize: " + args[1]);
            }
        }
        
    }
}
