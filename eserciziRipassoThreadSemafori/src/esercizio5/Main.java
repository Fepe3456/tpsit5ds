package esercizio5;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    protected static ArrayList<Pilota> classifica = new ArrayList<>();
    protected static Semaphore mutexClassifica = new Semaphore(1);

    public static void main(String[] args) {

        Random random = new Random();

        Pilota[] piloti = new Pilota[5];

        for(int i=0; i<5; i++){
            piloti[i] = new Pilota(("Pilota"+i), random.nextInt(16)+15);
            piloti[i].start();
        }
        for(int i=0; i<5; i++){
            try {
                piloti[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //System.out.println( classifica.toString() );
        for(int i=0; i< classifica.size(); i++){
            System.out.println( (i+1) + "°: " + classifica.get(i).getNome() );
        }

    }
}
