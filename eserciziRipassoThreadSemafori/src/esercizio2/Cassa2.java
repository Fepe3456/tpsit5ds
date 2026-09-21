package esercizio2;

public class Cassa2 extends Thread {

    private int versamenti;
    private int totale;

    Cassa2(int versamenti){
        this.versamenti = versamenti;
        totale = 0;
    }

    @Override
    public void run() {
        totale = versamenti * 10;
    }

    public int getTotale() {
        return totale;
    }
}
