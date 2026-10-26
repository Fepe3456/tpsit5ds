package es1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;
    private Socket client;
    private DataOutputStream versoClient;
    private DataInputStream dalClient;
    private int messaggioClient;
    private String messaggioServer;

    public void attendi(){
        try {

            System.out.println("Server avviato ...");

            server = new ServerSocket(6789);

            client = server.accept();

            server.close();

            versoClient = new DataOutputStream( client.getOutputStream() );

            dalClient = new DataInputStream( client.getInputStream() );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void comunica(){

        try {

            int somma = 0;

            for(int i=0; i<10; i++){
                messaggioClient = dalClient.readInt();
                System.out.println("Il server ha ricevuto il " + (i+1) + "° numero = " + messaggioClient);
                somma += messaggioClient;
            }

            messaggioServer = "La media dei numeri è " + (somma/10);
            versoClient.writeUTF( messaggioServer );

            System.out.println("Chiusura connessione server ...");

            server.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}