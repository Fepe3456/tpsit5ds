package esercizio3;

import java.util.concurrent.Semaphore;

public class Campana extends Thread {

    private String suono;
    private Semaphore semaphore;
    private Semaphore next;

    Campana(String suono, Semaphore s, Semaphore next){
        this.suono = suono;
        this.semaphore = s;
        this.next = next;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();

            System.out.println(suono);

            Thread.sleep(2000);

            next.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
