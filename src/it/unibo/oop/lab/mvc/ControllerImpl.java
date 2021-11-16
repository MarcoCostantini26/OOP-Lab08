package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    private String nextStringToPrint;
    private final List<String> historyList = new ArrayList<>();
    /**
     * Method for setting the next string to print. Null values are not acceptable.
     * 
     * @param stringToPrint
     * @exception 
     */
    public void setNextStringToPrint(final String stringToPrint) {
        this.nextStringToPrint = stringToPrint;
    }

    /**
     * Getting the next string to print.
     * 
     * @return return next String to print
     */
    public String getNextStringToPrint() {
        return this.nextStringToPrint;
    }

    /**
     * Getting the history of the printed strings.
     * 
     * @return List of the printed strings
     */
    public List<String> getHistoryList() {
        return this.historyList;
    }

    /**
     * Prints the current string. If the current string is unset,
     * an IllegalStateException should be thrown
     * 
     * @exception IllegalStateException
     */
    public void printCurrentString() {
        if (this.nextStringToPrint == null) {
            throw new IllegalStateException();
        } else {
            historyList.add(this.nextStringToPrint);
            System.out.println(this.nextStringToPrint);
        }
    }

}
