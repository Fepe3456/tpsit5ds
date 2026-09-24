package esercizio7;

import java.util.Random;

public class Filosofo extends Thread {

    private int id;

    Filosofo(int id){
        this.id = id;
    }

    @Override
    public void run() {
        Random random = new Random();

        try {

            //1. Pensa
            Thread.sleep( random.nextInt(1001)+500 );
            System.out.println("Filosofo" + id + " pensa");

            //2. Si siede a tavola
            Main.posti.acquire();
            int sinistra = id;
            int destra = (id+1)%5;

            //Prendere le forchette
            Main.forchette[sinistra].acquire();
            Main.forchette[destra].acquire();

            System.out.println("Filosofo" + id + " mangia");

            Thread.sleep( random.nextInt(501)+500 );

            //Posa le posate
            Main.forchette[sinistra].release();
            Main.forchette[destra].release();

            //Libera il posto a tavola
            Main.posti.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

/*

Esercizio 7
Cinque filosofi siedono attorno a un tavolo rotondo. Tra ogni coppia di filosofi vicini c'è una
forchetta (5 forchette in totale). Ogni filosofo ripete 3 volte questo ciclo:
1. pensa (da 500 a 1500 ms);
2. prende la forchetta a sinistra e quella a destra (servono entrambe);
3. mangia (da 500 a 1000 ms);
4. posa le due forchette.
Se tutti i filosofi prendono contemporaneamente la forchetta di sinistra, nessuno riesce più a
prendere quella di destra. Si arriva a un deadlock.
Risolvi il problema aggiungendo un semaforo che permette di sedersi a tavola a al massimo
4 filosofi alla volta.
Il Main stampa Tutti i filosofi hanno finito di mangiare. a fine esecuzione.
Domanda: perché con 4 filosofi al tavolo il deadlock non può verificarsi?

* */