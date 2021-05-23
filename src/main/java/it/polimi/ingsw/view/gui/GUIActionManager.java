package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.view.Manager;

public class GUIActionManager extends Manager {
    private ClientController ClientController;

    public GUIActionManager(ClientController clicont){
        this.ClientController = clicont;
    }

}
