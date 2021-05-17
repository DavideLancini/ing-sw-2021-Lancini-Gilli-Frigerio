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

    public void leaderCardView() {
        System.out.print("Requires[ DevCard of color " + requirements.toString() + "and level 2]");
        this.getProduction().productionView();
        System.out.println("\b+"+this.choice);
        System.out.println("\u001b[38:5:221m {"+this.getVP()+"}\u001b[m");
    }
}
