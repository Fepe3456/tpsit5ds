package es1;

public class Vettura extends Thread {

    private int n_posti_da_occupare;
    private String nome;

    Vettura(String nome, int n_posti_da_occupare){
        this.nome = nome;
        this.n_posti_da_occupare = n_posti_da_occupare;
    }

    @Override
    public void run() {
        try {
            Main.s.acquire(n_posti_da_occupare);
            System.out.println(nome + " entrata nel parcheggio!");
            Thread.sleep(5000);
            System.out.println(nome + " uscita dal parcheggio!");
            Main.s.release(n_posti_da_occupare);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

/*

Esercizio 1:
Si vuole simulare l’ingresso e l’uscita di alcune vetture da un parcheggio. Le vetture possono
essere di due tipi: Leggere e Pesanti. Quelle leggere occupano un posto, quelle pesanti due
posti. Ci sono 15 posti liberi nel parcheggio inizialmente, 8 auto leggere e 6 pesanti. Ogni
auto deve entrare nel parcheggio, aspettare 5 secondi e uscire.
E’ concessa la modifica della regolare classe semaforo per aggiungere dei metodi, qualora
venga ritenuto necessario.

* */