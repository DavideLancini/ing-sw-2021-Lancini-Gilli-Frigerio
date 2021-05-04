package it.polimi.ingsw.model;

public class LeaderTransform extends LeaderCard {

    public Resource WhiteTo(Marble white) /*throws Exception*/ {/*return right resource for marble type given*/
        //if (white.equals(Marble.WHITE))
            return getType();
        //else throw new Exception("not White");
    }
}
