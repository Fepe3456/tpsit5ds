package esercizio3;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        for(int i=0; i<10; i++){

            Semaphore sdin = new Semaphore(1);
            Semaphore sdon = new Semaphore(0);
            Semaphore sdan = new Semaphore(0);

            Campana din = new Campana("din", sdin, sdon);
            Campana don = new Campana("don", sdon, sdan);
            Campana dan = new Campana("dan", sdan, sdin);

            din.start();
            don.start();
            dan.start();
            din.join();
            don.join();
            dan.join();
        }

    }
}
