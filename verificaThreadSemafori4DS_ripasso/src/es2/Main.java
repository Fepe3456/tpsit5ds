package es2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

    protected static Semaphore semaphore = new Semaphore(1);
    protected static ArrayList classifica = new ArrayList();

    protected static int distanza_giro = 260;

    public static void main(String[] args) {

        Pilota[] piloti = new Pilota[20];

        for(int i=1; i<=20; i++){
            Pilota p = new Pilota( ("Pilota"+(i+1)), (5%i));
            piloti[i-1] = p;
            piloti[i-1].start();
        }
        for(int i=0; i<20; i++){
            try {
                piloti[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for(int i=0; i<3; i++){
            System.out.println( (i+1) + "°. " + classifica.get(i).toString() );
        }
        System.out.println( "Classifica generale: \n" + classifica.toString() );

    }
}
