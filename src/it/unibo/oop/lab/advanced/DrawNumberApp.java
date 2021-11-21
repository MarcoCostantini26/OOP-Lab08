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
    private static final String CONFIG_FILE = "config.yml";
    private static final String LOG_FILE = "outputResult.txt";
    private int min;
    private int max;
    private int attempts;
    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * 
     * @param views List of views 
     * @throws IOException
     */
    public DrawNumberApp(final List<DrawNumberView> views) throws IOException {
        this.setThreeVariable();
        this.model = new DrawNumberImpl(min, max, attempts);
        this.views = new ArrayList<>(views);
        for (final DrawNumberView view : this.views) {
            view.setObserver(this);
            view.start();
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view : this.views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view : this.views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView view : this.views) {
                view.limitsReached();
            }
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
        final InputStream in = ClassLoader.getSystemResourceAsStream(CONFIG_FILE);
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
     * Get Attempts.
     * @return Attempts
     */
    public int getAttemps() {
        return attempts;
    }
    /**
     * Set attempts.
     * @param attempts value to set attempts
     */
    public void setAttemps(final int attempts) {
        this.attempts = attempts;
    }

    /**
     * @param args
     *            ignored
     * @throws IOException 
     */
    public static void main(final String... args) throws IOException {
        final List<DrawNumberView> views = new ArrayList<>();
        views.add(new WriteStdout());
        views.add(new WriteMatchView(LOG_FILE));
        views.add(new DrawNumberViewImpl());
        new DrawNumberApp(views);
    }

}
