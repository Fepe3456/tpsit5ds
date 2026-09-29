package es2;

import java.util.Random;

public class Pilota extends Thread {

    private String nome;
    private int squadra;
    private int velocita;
    private int giro;
    private int metri_percorsi;
    private boolean pit_stop;

    private Random random = new Random();

    Pilota(String nome, int squadra){
        this.nome = nome;
        this.squadra = squadra;
        this.velocita = random.nextInt(51)+150; //random tra 150-200
        giro = 0;
        metri_percorsi = 0;
        pit_stop = false;
    }

    @Override
    public void run() {
        try {
            while (giro < 10) {

                metri_percorsi += velocita;

                Thread.sleep(1000); //Aspetta un secondo

                if( !pit_stop ){
                    //Incrementata o decrementata di 10
                    velocita += random.nextInt(21)-10;
                    System.out.println( "La velocità di " + nome + " è cambiata in " + velocita + "km/h");

                    //Se becca il numero su 20 possibilità è il momento del pit stop
                    if ( random.nextInt(21) == 1 ){
                        System.out.println( nome + " effettua un pit_stop");
                        pit_stop = true;
                        Thread.sleep(3000);
                        velocita += 20; //incrementata permanentemente di 20km/h
                    }
                }

                giro = metri_percorsi / Main.distanza_giro;

                System.out.println( nome + " ha percorso " + metri_percorsi + " metri ed è al giro " + giro + "/10");
            }

            Main.semaphore.acquire();
            Main.classifica.add(this);
            Main.semaphore.release();

        } catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public String toString(){
        return ("Nome pilota: " + nome + " - Squadra: " + squadra);
    }
}

/*

Esercizio 2:
Realizzare un programma che simuli una gara di Formula 1 composta da 22 piloti
(rappresentati da thread). Ogni pilota è caratterizzato da un nome, il nome della squadra e
una velocità base (espressa in km/h, generata casualmente tra 150 e 200).

La gara prevede 10 giri totali. Ogni giro viene completato al raggiungimento della sua
distanza, ovvero 260 km. Ad ogni percorrenza far attendere il pilota per un secondo.

Per ogni secondo che passa la velocità del pilota può incrementare o decrementare in un
range che va da -10 a +10.

Ogni pilota può decidere di effettuare un solo pit stop durante l'intera gara (scelta casuale o
basata su una condizione, a voi la scelta).

Il pit stop comporta un'attesa supplementare di 3 secondi nello stato di "attesa".

Al termine del pit stop, la velocità del pilota viene incrementata permanentemente di 20 km/h
per il resto della gara.

Al termine dei 10 giri, ogni thread deve salvare se stesso in una struttura dati condivisa (es.
array, ArrayList, LinkedList, Stack, quello che volete).

Il Main provvederà a stampare il podio.

* */