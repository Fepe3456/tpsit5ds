package esercizio1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author federico.pedretti
 */
public class ContaVocali extends Thread {

    private int inizio, fine;
    private int totale;

    public ContaVocali(int par, int par1) {
        this.inizio = par;
        this.fine = par1;
        totale = 0;
    }
    
    public int getTotale(){
        return totale; 
    }

    public void run(){
        for(int i=inizio; i<fine; i++){
            for(int j=0; j<Main.parole[i].length(); j++){
                if( Main.parole[i].charAt(j) == 'a' || 
                        Main.parole[i].charAt(j) == 'e' || 
                        Main.parole[i].charAt(j) == 'i' || 
                        Main.parole[i].charAt(j) == 'o' || 
                        Main.parole[i].charAt(j) == 'u'){
                    totale++; 
                }
            }
        }
        System.out.println("Vocali contate tra "+ inizio + " e " + fine + ": " + totale);
    }
}