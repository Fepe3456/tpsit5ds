package es1;

import java.util.concurrent.Semaphore;

public class Main {

    protected static Semaphore s = new Semaphore(15);

    public static void main(String[] args) {

        //Veicoli leggeri: 8 -- Veicoli pesanti: 6
        for(int i=0; i<8; i++){
            Vettura v = new Vettura(("VetturaLeggera" +(i+1)), 1);
            v.start();
            if( i<6){
                Vettura v2 = new Vettura(("VetturaPesante" +(i+1)), 2);
                v2.start();
            }
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