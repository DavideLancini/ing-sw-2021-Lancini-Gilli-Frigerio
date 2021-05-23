package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Resource;

import java.util.Collection;

public abstract class Manager {

    public void Turn(boolean action){}

    public void View(String view){}

    public void DisplayError(String error){}

    public void ArrangeDepot(Collection<Resource> resources){}

}
