package esercizio6;

public class Consumatore extends Thread {

    private boolean isWorking;

    Consumatore(){
        isWorking = true;
    }

    @Override
    public void run() {
        try {

            while( isWorking ) {
                Main.semaphoreConsumatore.acquire(); //controllo se posso consumare un prodotto
                Main.mutex.acquire(); //per accedere al buffer
                System.out.println("Consumato elemento: " + Main.buffer.getFirst());
                Main.buffer.removeFirst();
                Main.mutex.release();
                Main.semaphoreProduttore.release(); //libero uno spazio per il produttore
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
}
