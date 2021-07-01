package it.polimi.ingsw.network.messages;
/**
 * Class ClientMessageLocalPort
 * @author Group 12
 */
public class ClientMessageLocalPort extends ClientMessage {
    private final int localPort;

    public ClientMessageLocalPort(int localPort){
        this.type=MessageType.LocalPort;
        this.localPort=localPort;
    }

    public int getPort(){return localPort; }
}
