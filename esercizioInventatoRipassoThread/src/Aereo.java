import java.util.Random;

public class Aereo extends Thread{

    private Random random;
    private String id;
    private boolean priorita; //if true == atterraggio (priorità)

    Aereo(String id, boolean priorita){
        this.id = id;
        random = new Random();
        this.priorita = priorita;
    }

    @Override
    public void run() {
        try {

            Thread.sleep(6000+(random.nextInt(10000))); //L'aereo diventa pronto in un tempo random compreso tra 1-3minuti (//valori cambiati per velocizzare debug)

            if(priorita){

                Main.mutex_priorita.acquire();
                Main.aerei_con_priorita.addLast(this);
                Main.mutex_priorita.release();

            }
            else{
                Main.mutex_no_priorita.acquire();
                Main.aerei_senza_priorita.addLast(this);
                Main.mutex_no_priorita.release();
            }

            if(priorita){
                Main.semaphore_pista.acquire();
                System.out.println("L'aereo " + id + " è entrato in pista (" + priorita + ")");
                Thread.sleep(2000);
                System.out.println("L'aereo " + id + " ha liberato la pista (" + priorita + ")");
                Main.semaphore_pista.release();
            }
            else{ //Se non ha priorità, devo prima controllare se ci sono quelli con priorità

                while(true) {
                    Main.mutex_priorita.acquire();
                    boolean ci_sono = !Main.aerei_con_priorita.isEmpty();
                    Main.mutex_priorita.release();
                    if ( !ci_sono ) { //se ci sono priorità
                        System.out.println("L'aereo " + id + " deve aspettare per entrare in pista");
                        Thread.sleep(1000);
                    }
                    else{
                        break;
                    }
                }

                Main.semaphore_pista.acquire();
                System.out.println("L'aereo " + id + " è entrato in pista (" + priorita + ")");
                Thread.sleep(2000);
                System.out.println("L'aereo " + id + " ha liberato la pista (" + priorita + ")");
                Main.semaphore_pista.release();

            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
