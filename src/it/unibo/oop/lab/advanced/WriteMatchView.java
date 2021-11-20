package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
/**
 * Class that writes the match log on file.
 *
 */
public final class WriteMatchView implements DrawNumberView {

    private final PrintStream out;
    /**
     * 
     * @param path where to write match
     * @throws FileNotFoundException
     */
    public WriteMatchView(final String path) throws FileNotFoundException {
        super();
        this.out = new PrintStream(new File(path));
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
    }

    @Override
    public void start() {
    }

    @Override
    public void numberIncorrect() {
        out.println("Number Incorrect");
    }

    @Override
    public void result(final DrawResult res) {
        out.println(res.getDescription());
    }

    @Override
    public void limitsReached() {
        out.println("Game is lost");
    }

    @Override
    public void displayError(final String message) {
        out.println(message);
    }

}
