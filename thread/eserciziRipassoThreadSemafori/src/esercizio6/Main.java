package esercizio6;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

    protected static Semaphore semaphoreProduttore = new Semaphore(5);
    protected static Semaphore semaphoreConsumatore = new Semaphore(0);
    protected static Semaphore mutex = new Semaphore(1);

    protected static ArrayList<Integer> buffer = new ArrayList<>();

    public static void main(String[] args) {

        Produttore produttore = new Produttore();
        Consumatore cons1 = new Consumatore();
        Consumatore cons2 = new Consumatore();

        produttore.start();
        cons1.start();
        cons2.start();

        try {
            produttore.join();
            cons1.join();
            cons2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        produttore.setWorking(false);
        cons1.setWorking(false);
        cons2.setWorking(false);

    }
}

/*

Esercizio 6
Un Produttore genera i numeri da 1 a 20 e li inserisce, uno ogni 50 ms, in un Buffer
condiviso che può contenere al massimo 5 elementi. Due consumatori prelevano ognuno 10
elementi dal buffer, impiegando 200 ms per elaborarne uno.
Regole:
● se il buffer è pieno, il produttore deve aspettare;
● se il buffer è vuoto, i consumatori devono aspettare;
● l'accesso alla coda interna al buffer deve essere in mutua esclusione.
Il Main stampa Tutti gli elementi sono stati prodotti e consumati. alla fine.
Domanda: servono tre semafori? Se sì quali? Cosa contano e con che valore iniziale
partono?
Cosa succederebbe se scambiassi l'ordine delle due P() (prima il mutex e poi il semaforo dei
posti liberi) nel metodo inserisci()?

* */