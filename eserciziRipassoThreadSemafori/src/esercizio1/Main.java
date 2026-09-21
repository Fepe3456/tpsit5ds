package esercizio1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    protected static String[] parole = new String[]{"ciao", "tre", "un", "sette", "boh", "ciao", "tre", "un", "sette", "boh", "ciao", "tre", "un", "sette", "boh", "ciao", "tre", "un", "sette", "boh"};

    public static void main(String[] args) {
        Thread t1 = new ContaVocali(0, parole.length/4);
        Thread t2 = new ContaVocali(parole.length/4, parole.length/2);
        Thread t3 = new ContaVocali(parole.length/2, (parole.length/2 + parole.length/4));
        Thread t4 = new ContaVocali((parole.length/2 + parole.length/4), parole.length);

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Totale: " + (t2.getTotale() + t1.getTotale() + t3.getTotale() + t4.getTotale()) );

    }
}
