package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int LENGTH_LINE_SPLIT = 2;
    private int min;
    private int max;
    private int attemps;
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * @throws IOException 
     * 
     */
    public DrawNumberApp() throws IOException {
        this.setThreeVariable();
        this.model = new DrawNumberImpl(min, max, attemps);
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }
 
    /**
     * Method that set the three variables with value in "config.yml".
     * @throws IOException
     */
    private void setThreeVariable() {
        final InputStream in = ClassLoader.getSystemResourceAsStream("config.yml");
        String line;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            line = br.readLine();
            while (line != null) {
                final String [] lineSplit = line.split(":");
                final int value = Integer.parseInt(lineSplit[1].trim());
                if (lineSplit.length == LENGTH_LINE_SPLIT) {
                    if (lineSplit[0].contains("maximum")) {
                        this.setMax(value);
                    }
                    if (lineSplit[0].contains("minimum")) {
                        this.setMin(value);
                    }
                    if (lineSplit[0].contains("attempts")) {
                        this.setAttemps(value);
                    }
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e); 
        }
    }
    /**
     * Get min.
     * @return min
     */
    public int getMin() {
        return min;
    }
    /**
     * Set min.
     * @param min value to set min
     */
    public void setMin(final int min) {
        this.min = min;
    }
    /**
     * Get max.
     * @return max
     */
    public int getMax() {
        return max;
    }
    /**
     * Set max.
     * @param max value to set max
     */
    public void setMax(final int max) {
        this.max = max;
    }
    /**
     * Get Attemps.
     * @return Attemps
     */
    public int getAttemps() {
        return attemps;
    }
    /**
     * Set attemps.
     * @param attempts value to set attemps
     */
    public void setAttemps(final int attempts) {
        this.attemps = attempts;
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
