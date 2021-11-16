package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {
    private File currentFile;
    private static final String PATH = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + "output.txt";

    public Controller() {
        super();
        this.currentFile = new File(PATH);
    }
    /**
     * Method for setting a File as current file.
     * 
     * @param currentFile
     */
    public void setCurrentFile(final File currentFile) {
        this.currentFile = currentFile;
    }
    /**
     * Get current file.
     * 
     * @return current file
     */
    public File getCurrentFile() {
        return currentFile;
    }
    /**
     * Getting the path (in form of String) of the current File.
     * 
     * @return String of current file
     */
    public String getPath() {
        return currentFile.getPath();
    }
    /**
     * A method that gets a String as input and saves its content on the current
     * file.
     * 
     * @param input
     * @throws IOException, FileNotFoundException
     */
    public void write(final String input) throws IOException, FileNotFoundException {
        try (PrintStream ps = new PrintStream(currentFile)) {
            ps.print(input);
        }
    }
}
