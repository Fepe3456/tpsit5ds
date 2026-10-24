package esercizio2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main2 {

    protected static int conto = 0;

    public static void main(String[] args) {

        Cassa2 c1 = new Cassa2(2000);
        Cassa2 c2 = new Cassa2(2000);
        Cassa2 c3 = new Cassa2(2000);
        Cassa2 c4 = new Cassa2(2000);
        Cassa2 c5 = new Cassa2(2000);

        try {
            c1.start();
            c2.start();
            c3.start();
            c4.start();
            c5.start();

            c1.join();
            c2.join();
            c3.join();
            c4.join();
            c5.join();

            conto += c1.getTotale() + c2.getTotale() + c3.getTotale() + c4.getTotale() + c5.getTotale();

            System.out.println("Conto condiviso: " + conto);

        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
