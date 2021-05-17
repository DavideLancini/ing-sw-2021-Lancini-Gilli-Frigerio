package it.polimi.ingsw.model;

/**
 * Class LeaderTransform extends LeaderCard
 *
 * @author Gruppo 12
 */
public class LeaderTransform extends LeaderCard {
    public LeaderTransform(int vp,Resource type,Resource[] require) {
        this.setVP(vp);
        this.setType(type);
        this.setRequire(require);
    }
    /**
     * WhiteTo
     *
     * @param white white marble taken form market
     * @return resource based on leader type
     * @throws Exception if marble is not white
     */
    public Resource WhiteTo(Marble white) throws Exception {/*return right resource for marble type given*/
        if (white.equals(Marble.WHITE))
            return getType();
        else throw new Exception("not White");
    }
    public void leaderCardView() {
        System.out.print("Requires[");
        new ResourceCounter(this.getRequire());
        System.out.println("]");
        System.out.println(" "+Marble.WHITE+"="+this.getType());
        System.out.println("\u001b[38:5:221m {"+this.getVP()+"}\u001b[m");
    }
}
