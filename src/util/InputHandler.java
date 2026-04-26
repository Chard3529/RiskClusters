package src.util;

import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class InputHandler {

    public static List<String> readFile(String fileName) {
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

   public static String parseArguments(String[] args) {
        if (args.length == 0) throw new IllegalArgumentException("Must provide a filename argument!");
    
        if (args.length > 1) throw new IllegalArgumentException("Must provide only filename argument!");

        // Regex for pattern any file ending in ".txt"
        String regex = ".*\\.txt$"; 
        String filename = args[0];
        if (!filename.matches(regex)) throw new IllegalArgumentException("Must provide a filename ending in \".txt\"!");
        
        return filename;
    }
}
