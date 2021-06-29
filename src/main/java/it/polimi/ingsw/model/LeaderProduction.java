package it.polimi.ingsw.model;

/**
 * Class LeaderProduction extends LeaderCard
 *
 * @author Group 12
 */
public class LeaderProduction extends LeaderCard {
    private Production production;
    private Resource choice = Resource.EMPTY;
    private final CardColor requirements;
    /**
     * constructor
     * @param vp victory points
     * @param type resource type input of production
     * @param requirements requirements to activate
     * @param production production of this card
     * @param imagePath path of associated imagine
     */
    public LeaderProduction(int vp, Resource type, CardColor requirements, Production production,String imagePath){
        this.vp = vp;
        this.type = type;
        this.requirements = requirements;
        this.production = production;
        this.imagePath=imagePath;
    }




    public Production getProduction() {
        return this.production;
    }

    public CardColor getRequirements(){return this.requirements;}

    /**
     * setChoice
     *
     * @param choice resource player wants to produce
     */
    public void setChoice(Resource choice) {
        this.choice = choice;
    }

    public Resource getChoice() {
        return choice;
    }


    public String view(){
        String string = "";
        string = string.concat("══════════════╗\n");
        string = string.concat("   [" + requirements.toString()+Level.TWO+ "]      \n");
        string = string.concat("["+this.type+"]->["+this.choice+"]+ 1"+Resource.FAITH + "\n");
        string = string.concat("  \u001b[38:5:221m {"+this.getVP()+"}\u001b[m        \n");
        string = string.concat("══════════════╝\n");
        return string;
    }

}
