package es2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static void main() {
        Server server = new Server();
        server.attendi();
        server.rispondi();
    }


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

    public void rispondi(){
        try {

            int mediana, massimo=-1, minimo=20, somma=0;
            float media;

            for(int i=0; i<15; i++){
                messaggioClient = dalClient.readInt();
                System.out.println("Il server ha ricevuto il " + (i+1) + "° numero = " + messaggioClient);
                somma += messaggioClient;
                if( messaggioClient > massimo){
                    massimo = messaggioClient;
                }
                if( messaggioClient < minimo ){
                    minimo = messaggioClient;
                }
            }

            media = somma / 15;
            messaggioServer = "\nMedia = " + media +
                    "\nMassimo = " + massimo +
                    "\nMinimo = " + minimo;
            versoClient.writeUTF( messaggioServer );

            System.out.println("Chiusura connessione server ...");

            server.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
