package it.unibo.oop.lab.mvc;

import java.util.List;

/**
 * A controller that prints strings and has memory of the strings it printed.
 */
public interface Controller {
    /**
     * Method for setting the next string to print. Null values are not acceptable.
     * 
     * @param stringToPrint
     * @exception 
     */
    void setNextStringToPrint(String stringToPrint);
    /**
     * Getting the next string to print.
     * 
     * @return return next String to print
     */
    String getNextStringToPrint();
    /**
     * Getting the history of the printed strings.
     * 
     * @return List of the printed strings
     */
    List<String> getHistoryList();
    /**
     * Prints the current string. If the current string is unset,
     * an IllegalStateException should be thrown
     * 
     * @exception IllegalStateException
     */
    void printCurrentString();

    /*
     * This interface must model a simple controller responsible of I/O access. It
     * considers only the standard output, and it is able to print on it.
     */

}
