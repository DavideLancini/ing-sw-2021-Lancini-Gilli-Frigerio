package it.polimi.ingsw.network.serverNetwork;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverListenerSocket extends Thread {
    private ServerSocket listener = null;

    public serverListenerSocket(ServerSocket serverSocket){
        listener = serverSocket;
    }

    public void run(){
        Socket clientSock = null;
        try {
            clientSock = listener.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataInputStream in = null;
        while(true){
            // TODO while End with a timeout (restart the thread)
            try {
                in = new DataInputStream(new BufferedInputStream(clientSock.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String line = "";
            try {
                line = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(line);




            //TODO decode message (line)

            //TODO after first message open sending socket



        }

    }


}
