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

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */

}
