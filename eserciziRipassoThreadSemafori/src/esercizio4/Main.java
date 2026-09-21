package esercizio4;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Semaphore s = new Semaphore(3);

        for(int i=0; i<8; i++){
            Auto auto = new Auto(("Autos"+i), s);
            auto.start();
        }

    }
}
