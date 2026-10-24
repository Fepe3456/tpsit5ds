package es3;

public class Robot extends Thread{

    private String nome;
    private int prelievi;

    Robot(String nome){
        this.nome = nome;
        prelievi = 0;
    }

    @Override
    public void run() {
        try {

            while( prelievi < 10 ){ //fino ai 10 prelievi

                Thread.sleep(2000); //ogni 2 secondi

                Main.mutex.acquire(); //verifico se posso entrare o se qualcun altro sta effettuando operazioni su pacchi

                if( Main.pacchi>0) { //se ci sono i pacchi prelievo, altrtimenti aspetto che intervenga l'addetto al rifornimento
                    Main.pacchi -= 2; //prelevo i 2 pacchi
                    prelievi++; //incremento il numero di prelievi del robot
                    System.out.println(nome + " ha prelevato 2 pacchi (" + prelievi + "/10 prelievi)");
                }

                Main.mutex.release(); //rilascio la risorsa condivisa

            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

/*

Esercizio 3:
Simulare il comportamento di un magazzino di e-commerce con una giacenza iniziale di 50
pacchi. Ci sono 3 robot che prelevano pacchi.
Ogni robot effettua un prelievo ogni 2 secondi. Ogni prelievo riduce la giacenza di 2 unità.
Un robot si ferma definitivamente dopo aver completato 10 prelievi totali.
Ogni 5 secondi, un addetto controlla la giacenza. Se il numero di pacchi è inferiore a 15,
aggiunge pacchi fino a riportare la giacenza a 50.
Stampare un messaggio ogni volta che un robot preleva (indicando quanti pacchi restano) e
ogni volta che il rifornitore interviene (indicando quanti pacchi ha aggiunto).

* */