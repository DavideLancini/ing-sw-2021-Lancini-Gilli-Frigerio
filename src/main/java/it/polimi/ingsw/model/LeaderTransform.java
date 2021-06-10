package it.polimi.ingsw.model;

/**
 * Class LeaderTransform extends LeaderCard
 *
 * @author Gruppo 12
 */
public class LeaderTransform extends LeaderCard {
    private CardColor[] requirements;

    public LeaderTransform(int vp,Resource type,CardColor[] requirements) {
        this.vp = vp;
        this.type = type;
        this.requirements = requirements;
    }

    /**
     *
     * @return array of requirements
     */
    public CardColor[] getRequirements(){return this.requirements;}


    public String view(){
        String string = "";
        string = string.concat("══════════════╗\n");
        string = string.concat("   [");
        for(CardColor each : requirements) {
            string = string.concat(each+";");
        }
        string = string.concat("\b]\n");
        string = string.concat("   "+Marble.WHITE+"="+this.getType()+"        \n");
        string = string.concat("  \u001b[38:5:221m {"+this.getVP()+"}\u001b[m       \n");
        string = string.concat("══════════════╝\n");
        return string;
    }

}
