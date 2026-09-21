package esercizio2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    protected static int contoCondiviso = 0;

    public static void main(String[] args) {

        Semaphore s = new Semaphore(1);

        Cassa c1 = new Cassa(s, 2);
        Cassa c2 = new Cassa(s, 2);
        Cassa c3 = new Cassa(s, 2);
        Cassa c4 = new Cassa(s, 2);
        Cassa c5 = new Cassa(s, 2);

        try {
            c1.join();
            c2.join();
            c3.join();
            c4.join();
            c5.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Conto condiviso: " + contoCondiviso);
    }
}
