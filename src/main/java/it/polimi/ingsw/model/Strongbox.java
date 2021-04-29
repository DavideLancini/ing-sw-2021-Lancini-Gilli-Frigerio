package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;

public class Strongbox {
    private Collection<Resource> resources;

    public Strongbox(){
        this.resources = new ArrayList<Resource>();
    }

    //TODO: Handle Exception
    //removes cost elements from resources if all are contained within, otherwise throws exception
    public void extract(Collection<Resource> cost) throws Exception{
        if (this.contains(cost)){
            for(Resource elem : cost) this.resources.remove(elem);
        }
        else throw new Exception("");
    }

    public Collection<Resource> deposit(Collection<Resource> new_resources) {
        this.resources.addAll(new_resources);
        return this.resources;
    }

    public boolean contains(Collection<Resource> subset){
        ArrayList<Resource> main = new ArrayList(this.resources);

        try{
            for(Resource elem : subset){if(!main.remove(elem))throw new Exception("");}
        }
        catch (Exception e){return false;}
        return true;
    }
}
