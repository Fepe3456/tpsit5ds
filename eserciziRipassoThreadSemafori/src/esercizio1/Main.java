package  esercizio1;

public class Main {

    protected static String[] parole = new String[]{"ciao", "tre", "un", "sette", "boh", "ciao", "tre", "un", "sette", "boh", "ciao", "tre", "un", "sette", "boh", "ciao", "tre", "un", "sette", "boh"};

    public static void main(String[] args) {
        Thread t1 = new ContaVocali(0, parole.length/4);
        Thread t2 = new ContaVocali(parole.length/4, parole.length/2);
        Thread t3 = new ContaVocali(parole.length/2, (parole.length/2 + parole.length/4));
        Thread t4 = new ContaVocali((parole.length/2 + parole.length/4), parole.length);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}