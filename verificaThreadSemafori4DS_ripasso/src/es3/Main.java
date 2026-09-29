package es3;

import java.util.concurrent.Semaphore;

public class Main {

    protected static int pacchi = 50;
    protected static Semaphore mutex = new Semaphore(1);

    public static void main(String[] args) {

        Robot r1 = new Robot("Robot1");
        Robot r2 = new Robot("Robot2");
        Rifornitore addetto = new Rifornitore("Addetto_rifornimento");

        r1.start();
        r2.start();
        addetto.start();

        try {
            r1.join();
            r2.join();
            addetto.setChecking(false);
            addetto.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Prelevati tutti i pacchi!");

    }
}
