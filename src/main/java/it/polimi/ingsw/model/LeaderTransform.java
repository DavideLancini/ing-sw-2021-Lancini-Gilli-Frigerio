package it.polimi.ingsw.model;

/**
 * Class LeaderTransform extends LeaderCard
 *
 * @author Gruppo 12
 */
public class LeaderTransform extends LeaderCard {
    private CardColor[] requirements;
    /**
     * constructor
     * @param vp victory points
     * @param type resource type to which white marbles are changed
     * @param requirements requirements to activate
     * @param imagePath path to path of associated imagine
     */
    public LeaderTransform(int vp,Resource type,CardColor[] requirements,String imagePath) {
        this.vp = vp;
        this.type = type;
        this.requirements = requirements;
        this.imagePath= imagePath;
    }


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
