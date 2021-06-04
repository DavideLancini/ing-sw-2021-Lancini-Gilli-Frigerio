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
        String string = new String();
        string.concat("══════════════╗\n");
        string.concat("   [5" + this.requirements + "]\n");
        string.concat("   "+getType() + " " + getType()+"\n");
        string.concat("   \u001b[38:5:221m{" + this.getVP() + "}\u001b[m\n");
        string.concat("══════════════╝\n");

        return string;
    }

    public void leaderCardView() {
        System.out.println("══════════════╗");
        System.out.println("   [5" + this.requirements + "]");
        System.out.println("   "+getType() + " " + getType());
        System.out.println("   \u001b[38:5:221m{" + this.getVP() + "}\u001b[m");
        System.out.println("══════════════╝");
    }
}
