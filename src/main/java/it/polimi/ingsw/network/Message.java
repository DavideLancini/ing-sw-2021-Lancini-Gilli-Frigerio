package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.components.MessageType;

public abstract class Message {

    protected String[] token = new String[12]; //used to identify player and match
    protected int index; //used to keep message in order (the socket will discard messages too old)
    protected MessageType type; //used to identify the type of message

    public void send(){}

    /**
     *
     * @param controller
     * @return true if an action has been correctly performed
     */
    public boolean resolve(Controller controller){return false;}
}

