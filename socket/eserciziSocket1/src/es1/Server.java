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
    private String messaggioClient;
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

            messaggioClient = dalClient.readLine();

            messaggioServer = messaggioClient.toUpperCase();

            versoClient.writeBytes(messaggioServer + "\n");

            System.out.println("Chiusura connessione server ...");

            server.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}