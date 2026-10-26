package es1;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket mio_socket;
    private DataInputStream dalServer;
    private DataOutputStream versoServer;
    private BufferedReader tastiera;
    private int messaggioClient;
    private String messaggioServer;
    private String nomeServer;
    private int porta;

    Client(){
        nomeServer = "localhost";
        porta = 6789;
    }

    public void connetti(){

        try {

            mio_socket = new Socket(nomeServer, porta);

            System.out.println("Client connesso al server!");

            dalServer = new DataInputStream( mio_socket.getInputStream() );
            versoServer = new DataOutputStream( mio_socket.getOutputStream() );

            tastiera = new BufferedReader( new InputStreamReader(System.in) );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void comunica(){

        try {

            for(int i=0; i<10; i++){
                System.out.println("Inserisci il " + (i+1) + "° numero: ");

                //messaggioClient = tastiera.read();
                messaggioClient = Integer.parseInt(tastiera.readLine());

                versoServer.writeInt( messaggioClient );
                System.out.println("Inviato al server il numero " + messaggioClient);
            }

            messaggioServer = dalServer.readUTF();
            System.out.println("Risposta del server: " + messaggioServer);

            mio_socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}