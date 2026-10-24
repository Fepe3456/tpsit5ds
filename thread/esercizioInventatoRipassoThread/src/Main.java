import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    protected static Semaphore semaphore_pista = new Semaphore(1);

    protected static Semaphore mutex_priorita = new Semaphore(1);
    protected static ArrayList aerei_con_priorita = new ArrayList<>();

    protected static Semaphore mutex_no_priorita = new Semaphore(1);
    protected static ArrayList aerei_senza_priorita = new ArrayList<>();


    public static void main(String[] args) {

        Random random = new Random();

        Aereo[] aerei = new Aereo[10];

        boolean priorita;
        for(int i=0; i<aerei.length; i++){
            if( random.nextInt(6) == 3 ){
                priorita = true;
            }
            else{
                priorita = false;
            }
            aerei[i] = new Aereo(("" + i + i + i), priorita);
            aerei[i].start();
        }
        for(int i=0; i<aerei.length; i++){
            try {
                aerei[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

/*

Consegna modificata in seguito per complicare esercizio --> inserite priorità per atterraggio

        SIMULAZIONE PISTA ATTERRAGGIO AEROPORTO

In un aeroporto una pista di atterraggio è messa a disposizione sia ad aerei in partenza sia a quelli in arrivo.

Bisogna fare in modo che gli aerei si mettano d'accordo su chi occupa la pista.

L'aereo quando viene creato deve andare a dormire subito per un tempo compreso tra 60-180secondi,
in modo da simulare che gli aerei siano pronti per prendere la pista in momenti differenti.

Ogni aereo impiega 120secondi in pista.

*/