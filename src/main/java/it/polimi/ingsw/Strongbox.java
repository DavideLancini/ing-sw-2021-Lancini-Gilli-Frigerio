package it.polimi.ingsw;

import com.sun.management.GarbageCollectionNotificationInfo;

import java.util.Collection;

public class Strongbox {
    private Collection<Resource> resources;

    //TODO: Handle Exception
    //removes cost elements from resources if all are contained within, otherwise throws exception
    public void extract(Collection<Resource> cost) throws Exception{
        if (this.resources.containsAll(cost)){
            this.resources.remove(cost);
        }
        else throw new Exception("");
    }

    public Collection<Resource> deposit(Collection<Resource> new_resources) {
        this.resources.addAll(new_resources);
        return this.resources;
    }

    public boolean has(Collection<Resource> cost){
        return this.resources.containsAll(cost);
    }
}
