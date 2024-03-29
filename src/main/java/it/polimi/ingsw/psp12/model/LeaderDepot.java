package it.polimi.ingsw.psp12.model;

/**
 * Class LeaderDepot extends LeaderCard
 *
 * @author Group 12
 */
public class LeaderDepot extends LeaderCard {
    //hardcoded to 5 of the same resource, possible to be changed
    private final Resource requirements;

    /**
     * constructor
     * @param vp victory points
     * @param type resource type that can be stored
     * @param requirements requirements to activate
     * @param imagePath path to path of associated imagine
     */
    public LeaderDepot(int vp, Resource type, Resource requirements, String imagePath) {
        this.vp = vp;
        this.type = type;
        this.requirements = requirements;
        this.imagePath=imagePath;
    }

    /**
     * activateDepot
     *
     * @param depot player personal depot
     */
    public void toggleActive(Depot depot) {
        depot.activateLeader(getType());
        super.toggleActive();
    }

    public Resource getRequirements(){return  this.requirements;}


    public String view(){
        String string = "";
        string = string.concat("══════════════╗\n");
        string = string.concat("   [5" + this.requirements + "]      \n");
        string = string.concat("   "+getType() + " " + getType()+"      \n");
        string = string.concat("   \u001b[38:5:221m{" + this.getVP() + "}\u001b[m        \n");
        string = string.concat("══════════════╝\n");

        return string;
    }

}
