package esercizio4;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Auto extends Thread{

    private String nome;

    Auto(String nome){
        this.nome = nome;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {

            System.out.println(nome + " è arrivata");

            //Verifica che ci siano posti liberi
            Main.postiLiberi.acquire();

            //Adesso deve coordinarsi con le altre auto entrate per poter modificare la variabile postiOccupato
            Main.mutex.acquire();

            Main.postiOccupati++;

            System.out.println(nome + " è entrata. Posti occupati: " + Main.postiOccupati);

            //Libera la risorsa condivisa
            Main.mutex.release();

            Thread.sleep(random.nextInt(4)*1000);

            //Verifica se può entrare ancora nella variabile condivisa a modificare la variabile dei posti occupati, prima di uscire
            Main.mutex.acquire();
            Main.postiOccupati--;
            System.out.println(nome + " esce. Posti occupati: " + Main.postiOccupati);
            Main.mutex.release();

            //Libera il posto ad un'altra auto
            Main.postiLiberi.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
