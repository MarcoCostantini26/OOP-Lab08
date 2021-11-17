package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;
    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * @throws IOException 
     * 
     */
    public DrawNumberApp() throws IOException {
        this.setThreeCostants();
        this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
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
 
    private void setThreeCostants() throws IOException {
        final InputStream in = ClassLoader.getSystemResourceAsStream("config.yml");
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        }
    }

    /**
     * @param args
     *            ignored
     * @throws IOException 
     */
    public static void main(final String... args) throws IOException {
        new DrawNumberApp();
    }

}
