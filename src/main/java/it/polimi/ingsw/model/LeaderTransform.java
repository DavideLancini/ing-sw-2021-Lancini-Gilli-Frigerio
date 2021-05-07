package it.polimi.ingsw.model;

/**
 * Class LeaderTransform extends LeaderCard
 *
 * @author Gruppo 12
 */
public class LeaderTransform extends LeaderCard {
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
}
