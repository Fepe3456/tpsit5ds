package esercizio2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cassa extends Thread {

    private int versamenti;
    private Semaphore s;

    public Cassa(Semaphore s, int versamenti){
        this.versamenti = versamenti;
        this.s = s;
    }

    public void run(){
        try {
            s.acquire();

            Main.contoCondiviso += 10*versamenti;

            s.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Cassa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
