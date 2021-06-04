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


 /* obsolete, controller should handle it
    /**
     * WhiteTo
     *
     * @param white white marble taken form market
     * @return resource based on leader type
     * @throws Exception if marble is not white
     *//*
    public Resource WhiteTo(Marble white) throws Exception {/*return right resource for marble type given*//*
        if (white.equals(Marble.WHITE))
            return getType();
        else throw new Exception("not White");
    }
*/
    public String view(){
        String string = "";
        string = string.concat("══════════════╗\n");
        string = string.concat("   [");
        for(CardColor each : requirements) {
            string = string.concat(each+";");
        }
        string = string.concat("\b]\n");
        string = string.concat("   "+Marble.WHITE+"="+this.getType()+"\n");
        string = string.concat("  \u001b[38:5:221m {"+this.getVP()+"}\u001b[m\n");
        string = string.concat("══════════════╝\n");
        return string;
    }

    public void leaderCardView() {
        System.out.println("══════════════╗");
        System.out.print("   [");
        for(CardColor each : requirements) {
            System.out.print(each+";");
        }
        System.out.println("\b]");
        System.out.println("   "+Marble.WHITE+"="+this.getType());
        System.out.println("  \u001b[38:5:221m {"+this.getVP()+"}\u001b[m");
        System.out.println("══════════════╝");
    }
}
