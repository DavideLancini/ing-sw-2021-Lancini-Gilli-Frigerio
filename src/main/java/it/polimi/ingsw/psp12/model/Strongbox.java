package it.polimi.ingsw.psp12.model;

import java.util.ArrayList;
import java.util.Collection;
/**
 * Class Strongbox
 * @author Group 12
 */
public class Strongbox {
    private final Collection<Resource> resources;

    /**
     * class constructor
     * creates new collection of resources
     */
    public Strongbox(){
        this.resources = new ArrayList<>();
    }

    //Temporary, would be better without giving access
    public Collection<Resource> getResources() {return this.resources;}


    /**
     * extracts resource from StrongBox
     * @param elem singular resource to remove from strongbox
     * @throws Exception if the element isn't contained within resources collection
     */
    public void extract(Resource elem) throws Exception{
        if(this.resources.contains(elem)){this.resources.remove(elem);}
        else throw new Exception("");

    }


    /**
     * deposit resources in StrongBox
     * @param new_resources resource from production to deposit
     */
    public void deposit(Collection<Resource> new_resources) {
        this.resources.addAll(new_resources);
    }

    /**
     * research specific resource in strongBox
     * @param subset resources wanted
     * @return true if subset is contained in the strongBox else false
     */
    public boolean contains(Collection<Resource> subset){
        ArrayList<Resource> main = new ArrayList<>(this.resources);

        try{
            for(Resource elem : subset){if(!main.remove(elem))throw new Exception("");}
        }
        catch (Exception e){return false;}
        return true;
    }

    /**
     * view
     * @return string to show
     */
    public String StrongboxView(){
        String string="╔════════════╗\n╚═════╚╝═════╝\n  ";
        string=string.concat(ResourceCounter.count(this.resources));
        for(int i = 0; i<4-ResourceCounter.countTypes(this.resources); i++){string = string.concat("  ");}


        string=string.concat("\n╚════════════╝");
        return string;
    }
}
