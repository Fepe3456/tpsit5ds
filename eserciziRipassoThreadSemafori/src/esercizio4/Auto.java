package esercizio4;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Auto extends Thread{

    private Semaphore semaphore;
    private String nome;

    Auto(String nome, Semaphore semaphore){
        this.semaphore = semaphore;
        this.nome = nome;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            semaphore.acquire();

            System.out.println(nome + " entrata");
            Thread.sleep(random.nextInt(4));

            semaphore.release();
            System.out.println(nome + "uscita");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
