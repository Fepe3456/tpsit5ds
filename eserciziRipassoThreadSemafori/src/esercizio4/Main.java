package esercizio4;

import java.util.concurrent.Semaphore;

public class Main {

    protected static Semaphore mutex = new Semaphore(1);
    protected static Semaphore postiLiberi = new Semaphore(3);
    protected static int postiOccupati = 0;

    public static void main(String[] args) {

        for(int i=0; i<8; i++){
            Auto auto = new Auto(("Auto"+i));
            auto.start();
        }

    }
}
