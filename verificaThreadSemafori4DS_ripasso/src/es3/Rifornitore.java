package es3;

public class Rifornitore extends Thread{

    private String nome;
    private boolean checking;

    Rifornitore(String nome){
        this.nome = nome;
        checking = true;
    }

    public void setChecking(boolean checking) {
        this.checking = checking;
    }

    @Override
    public void run() {

        try {

            while( checking ) {
                Thread.sleep(5000); //ogni 5 secondi

                Main.mutex.acquire(); //entra per vedere il numero di pacchi (serve comunque mutex, perché un robot potrebbe modificare il valore nello stesso momento (penso))
                if (Main.pacchi <= 15) {
                    Main.pacchi = 50;
                    System.out.println(nome + " ha riportato i pacchi a 50!");
                } //Se è minore o uguale a 15 pacchi, riporta i 50 pacchi
                Main.mutex.release(); //libera la risorsa condivisa
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
