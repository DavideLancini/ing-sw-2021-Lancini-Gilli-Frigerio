package it.polimi.ingsw.psp12.network.messages;

/**
 * Class ServerMessageAddResource
 * @author Group 12
 */

public class ServerMessageChooseLeaders extends ServerMessage{
    private final String[] leaders = new String[4];
    private final String[] icons = new String[4];

    public ServerMessageChooseLeaders(String[] leaders, String[] icons){
        this.type = MessageType.ChooseLeaders;
        System.arraycopy(leaders, 0, this.leaders, 0, leaders.length);
        System.arraycopy(icons, 0, this.icons, 0, leaders.length);

    }

    public String[] getLeaders(boolean string){return string ? this.leaders : this.icons;}
}
