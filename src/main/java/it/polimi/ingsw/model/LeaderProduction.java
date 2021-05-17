package it.polimi.ingsw.model;

/**
 * Class LeaderProduction extends LeaderCard
 *
 * @author Gruppo 12
 */
public class LeaderProduction extends LeaderCard {
    public LeaderProduction(int vp,Resource type,Resource[] require,Production production) {
        this.setVP(vp);
        this.setType(type);
        this.setRequire(require);
        this.setProduction(production);
    }

    public void leaderCardView() {
        System.out.print("Requires[");
        new ResourceCounter(this.getRequire());
        System.out.println("]");
        this.getProduction().productionView();
        System.out.println("\b+"+this.getChoice());
        System.out.println("\u001b[38:5:221m {"+this.getVP()+"}\u001b[m");
    }
}
