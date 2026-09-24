package esercizio7;

import java.util.concurrent.Semaphore;

public class Main {

    protected static Semaphore[] forchette = new Semaphore[5];
    protected static Semaphore posti = new Semaphore(4);

    public static void main(String[] args) {

        Filosofo[] filosofi = new Filosofo[5];

        for(int i=0; i<forchette.length; i++){
            forchette[i] = new Semaphore(1);
            filosofi[i] = new Filosofo(i);
        }
        for(int i=0; i<filosofi.length; i++){
            filosofi[i].start();
        }
        for(int i=0; i<filosofi.length; i++){
            try {
                filosofi[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("\nTutti i filosofi hanno finito di mangiare");

    }
}