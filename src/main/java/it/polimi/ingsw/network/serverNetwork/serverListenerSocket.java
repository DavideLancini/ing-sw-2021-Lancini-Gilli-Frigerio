package it.polimi.ingsw.network.serverNetwork;

import it.polimi.ingsw.Server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

/**
 * Connection Thread Class
 *
 *
 *
 * @author Lancini Davide
 */
public class serverListenerSocket extends Thread {
    private ServerSocket serverSocket = null;

    private Socket listener = null;

    private DataInputStream inputStream = null;

    private Socket sender = null;


    public serverListenerSocket(ServerSocket serverSocketName){
        serverSocket = serverSocketName;
    }

    public void run(){
        openListener();
        activateListener();
        getClientAddress();

        //TODO Open listener from serverMain (to keep everything clean)

        openSender();
        waitLogin();
    }

    private void openListener() {
        try {
            listener = serverSocket.accept();
        } catch (IOException e) {
            Server.logger.log(Level.WARNING,"serverListenerSocket>run> This Thread cannot accept connections");
            // TODO restart thread
        }
    }

    private void activateListener() {
        try {
            inputStream = new DataInputStream(new BufferedInputStream(listener.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            // TODO while end with a timeout (then restart thread)
            String message = "";
            try {
                message = inputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println(message);

            //TODO decode message (message)
        }
    }

    private void getClientAddress() {
        String clientName;
        int clientPort;

    }

    public void openSender(){

    }

    private void waitLogin() {

    }
}
