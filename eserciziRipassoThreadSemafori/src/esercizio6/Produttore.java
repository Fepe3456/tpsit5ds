package esercizio6;

public class Produttore extends Thread {

    private boolean isWorking;
    private int number;

    Produttore(){
        isWorking = true;
        number = 0;
    }

    @Override
    public void run() {
        while( isWorking && number <= 20 ){
            try {
                Thread.sleep(50);

                Main.semaphoreProduttore.acquire(); //Verifico se ci sono già 5 elementi o se ci sono posti liberi in cui aggiungere l'elemento
                Main.mutex.acquire(); //per accedere al buffer
                Main.buffer.add( number );
                System.out.println("Prodotto elemento: " + number);
                Main.mutex.release(); //libero il buffer
                Main.semaphoreConsumatore.release(); //Permetto ai consumatori di accedervi
                number++;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
}
