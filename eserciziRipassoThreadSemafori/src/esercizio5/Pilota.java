package esercizio5;

import java.util.Random;

public class Pilota extends Thread {

    private String nome;
    private int velocita;
    private int metriPercorsi;
    private int giro;

    public Pilota(String nome, int velocita) {
        this.nome = nome;
        this.velocita = velocita;
        metriPercorsi = 0;
        giro = 0;
    }

    @Override
    public void run() {
        try {

            Random random = new Random();

            while( metriPercorsi<(4*100) ){

                Thread.sleep(300);
                metriPercorsi += velocita;
                giro = metriPercorsi/100;

                System.out.println(nome + ": metri totali percorsi " + metriPercorsi + ", giro numero " + giro);

                if( random.nextInt(100) < 15 ){
                    System.out.println(nome + " ha forato e si ferma un attimo");
                    Thread.sleep(600);
                }

            }

            Main.mutexClassifica.acquire();
            Main.classifica.add(this);
            Main.mutexClassifica.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNome() {
        return nome;
    }
}
