package it.polimi.ingsw.model;

/**
 * Class LeaderProduction extends LeaderCard
 *
 * @author Gruppo 12
 */
public class LeaderProduction extends LeaderCard {
    private Production production;
    private Resource choice;
    private CardColor requirements;

    public LeaderProduction(int vp, Resource type, CardColor requirements, Production production){
        this.vp = vp;
        this.type = type;
        this.requirements = requirements;
        this.production = production;
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
        string = string.concat("   [" + requirements.toString()+"+"+Level.TWO+ "]\n");
        string = string.concat(this.production.view());
        string = string.concat("+["+Resource.EMPTY+"]\n");
        string = string.concat("  \u001b[38:5:221m {"+this.getVP()+"}\u001b[m\n");
        string = string.concat("══════════════╝\n");
        return string;
    }

    public void leaderCardView() {
        System.out.println("══════════════╗");
        System.out.println("   [" + requirements.toString()+"+"+Level.TWO+ "]");
        this.production.productionView();
        System.out.println(" +["+Resource.EMPTY+"]");
        System.out.println("  \u001b[38:5:221m {"+this.getVP()+"}\u001b[m");
        System.out.println("══════════════╝");
    }
}
