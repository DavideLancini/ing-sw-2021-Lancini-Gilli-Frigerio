package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.ClientMessage;

public class ClientMessageLocalPort extends ClientMessage {
    private int localPort;

    public ClientMessageLocalPort(int localPort){
        this.type=MessageType.LocalPort;
        this.localPort=localPort;
    }

    public int getPort(){return localPort; }
}
