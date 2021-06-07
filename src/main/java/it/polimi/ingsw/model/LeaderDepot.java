package it.polimi.ingsw.model;

/**
 * Class LeaderDepot extends LeaderCard
 *
 * @author Gruppo 12
 */
public class LeaderDepot extends LeaderCard {
    //hardcoded to 5 of the same resource, possible to be changed
    private Resource requirements;

    public LeaderDepot(int vp, Resource type, Resource requirements) {
        this.vp = vp;
        this.type = type;
        this.requirements = requirements;
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
        string = string.concat("   [5" + this.requirements + "]\n");
        string = string.concat("   "+getType() + " " + getType()+"\n");
        string = string.concat("   \u001b[38:5:221m{" + this.getVP() + "}\u001b[m\n");
        string = string.concat("══════════════╝\n");

        return string;
    }

}
