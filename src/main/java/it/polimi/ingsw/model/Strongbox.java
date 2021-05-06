package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;
/**
 * Class Strongbox
 * @author Gruppo 12
 */
public class Strongbox {
    private Collection<Resource> resources;

    /**
     * class constructor
     * creates new collection of resources
     */
    public Strongbox(){
        this.resources = new ArrayList<>();
    }

    /**
     * extract
     * @param cost resources to remove from strongbox to buy new devCard
     * @throws Exception if all cost elements aren't contained within resources collection
     */
    //TODO: Handle Exception
    //removes cost elements from resources if all are contained within, otherwise throws exception
    public void extract(Collection<Resource> cost) throws Exception{
        if (this.contains(cost)){
            for(Resource elem : cost) this.resources.remove(elem);
        }
        else throw new Exception("");
    }

    /**
     * deposit
     * @param new_resources resource from production to deposit
     * @return current StrongBox
     */
    public Collection<Resource> deposit(Collection<Resource> new_resources) {
        this.resources.addAll(new_resources);
        return this.resources;
    }

    /**
     * contains
     * @param subset resources wanted
     * @return true if subset is contained in the strongBox else false
     */
    public boolean contains(Collection<Resource> subset){
        ArrayList<Resource> main = new ArrayList(this.resources);

        try{
            for(Resource elem : subset){if(!main.remove(elem))throw new Exception("");}
        }
        catch (Exception e){return false;}
        return true;
    }
}
