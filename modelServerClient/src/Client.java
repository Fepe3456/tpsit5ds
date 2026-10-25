import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    private String nomeServer;
    private int porta;
    private Socket mio;
    private BufferedReader tastiera;
    private DataInputStream dalServer;
    private DataOutputStream versoServer;
    private String messaggioClient;
    private String messaggioServer;

    Client(){
        nomeServer = "localhost";
        porta = 6789;
    }

    public void connetti(){

        try{

            tastiera = new BufferedReader( new InputStreamReader(System.in) );

            mio = new Socket(nomeServer, porta);

            versoServer = new DataOutputStream( mio.getOutputStream() );
            dalServer = new DataInputStream( mio.getInputStream() );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void comunica(){
        try{

            System.out.println("Messaggio per il server: ");
            messaggioClient = tastiera.readLine();
            versoServer.writeBytes( messaggioClient + "\n" );

            messaggioServer = dalServer.readLine();
            System.out.println("Risposta: " + messaggioServer);

            mio.close();

        }catch (Exception e){
            System.out.println( e.getMessage() );
        }
    }

}