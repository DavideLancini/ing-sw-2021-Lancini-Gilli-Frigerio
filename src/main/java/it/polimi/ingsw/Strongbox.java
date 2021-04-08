package it.polimi.ingsw;

import com.sun.management.GarbageCollectionNotificationInfo;

import java.util.Collection;

public class Strongbox {
    private Collection<Resource> resources;

    //TODO: Handle Exception
    //removes cost elements from resources if all are contained within, otherwise throws exception
    public void extract(Collection<Resource> cost) throws Exception{
        if (resources.containsAll(cost)){
            resources.remove(cost);
        }
        else throw new Exception("");
    }

    public Collection<Resource> deposit(Collection<Resource> new_resources) {
        resources.addAll(new_resources);
        return resources;
    }

    public boolean has(Collection<Resource> cost){
        return resources.containsAll(cost);
    }
}
