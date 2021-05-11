package it.polimi.ingsw.model;

/**
 * Class LeaderProduction extends LeaderCard
 *
 * @author Gruppo 12
 */
public class LeaderProduction extends LeaderCard {
    private Production production;
    private Resource choice;

    public Production getProduction() {
        return this.production;
    }

    /**
     * setChoice
     *
     * @param choice resource player wants to produce
     */
    public void setChoice(Resource choice) {
        this.choice = choice;
    }
}
