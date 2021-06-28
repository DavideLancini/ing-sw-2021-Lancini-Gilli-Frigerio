package it.polimi.ingsw.network.messages;
/**
 * Class ClientMessageLocalPort
 * @author gruppo 12
 */
public class ClientMessageLocalPort extends ClientMessage {
    private int localPort;

    public ClientMessageLocalPort(int localPort){
        this.type=MessageType.LocalPort;
        this.localPort=localPort;
    }

    public int getPort(){return localPort; }
}
