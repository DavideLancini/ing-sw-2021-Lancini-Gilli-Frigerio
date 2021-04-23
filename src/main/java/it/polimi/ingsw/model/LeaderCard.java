package it.polimi.ingsw.model;

import it.polimi.ingsw.Depot;
import it.polimi.ingsw.model.Marble;
import it.polimi.ingsw.model.Resource;

public abstract class LeaderCard {
    protected int VP;
    protected boolean isActive= false;
    protected Resource type;

    public void setVP(int VP) {
        this.VP = VP;
    }
    public int getVP(){
        return this.VP;
    }

    public void setType(Resource type) {
        this.type = type;
    }
    public Resource getType(){
        return this.type;
    }

    public boolean getIsActive(){
        return this.isActive;
    }

    public void toggleActive(){
        this.isActive = !this.isActive;
    }


}

class LeaderSale extends LeaderCard{

    public void DownPrice(Resource[] cost){
        var i = 0;
        var done = false;
        //remove 1 resource based on type
        while(cost[i]!=null && !done) {
            if (cost[i].equals(getType())) {
                cost[i] = null;
                done = true;
            }
            i++;
        }
    }
}

class LeaderDepot extends LeaderCard{

    public void activateDepot(Depot depot){
        depot.activateLeader(this.type);
    }
}

class LeaderTransform extends LeaderCard{

    public Resource WhiteTo(Marble white) throws Exception{/*return right resource for marble type given*/;
        if (white.equals(Marble.WHITE))
            return getType();
        else throw new Exception("not White");
    }
}

class LeaderProduction extends LeaderCard{
    public Production production;
    private Resource choice;

    public void setChoice(Resource choice) {
        this.choice = choice;
    }
}