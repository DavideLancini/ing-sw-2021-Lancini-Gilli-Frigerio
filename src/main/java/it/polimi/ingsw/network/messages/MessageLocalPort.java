package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.network.ClientMessage;

public class MessageLocalPort extends ClientMessage {
    private int localPort;

    public MessageLocalPort(int localPort){
        this.type=MessageType.LocalPort;
        this.localPort=localPort;
    }

    public int getPort(){return localPort; }
}
