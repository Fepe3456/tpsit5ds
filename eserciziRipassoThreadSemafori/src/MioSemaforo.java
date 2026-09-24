public class MioSemaforo {

    private int valore;

    public MioSemaforo(int valoreIniziale) {
        this.valore = valoreIniziale;
    }

    // Operazione P (wait)
    public synchronized void p() throws InterruptedException {
        while (valore == 0) {
            wait();
        }

        valore--;
    }

    // Operazione V (signal)
    public synchronized void v() {
        valore++;
        notify();
    }
}
