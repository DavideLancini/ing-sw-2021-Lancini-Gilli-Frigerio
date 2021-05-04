package it.polimi.ingsw;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
}



class BoardController extends Controller{
    private PlayerBoard pb;

    public BoardController(PlayerBoard pb){
        this.pb = pb;
    }

    public void ActivateLeader (int position) {
        LeaderCard leader = this.pb.getLeaderCard(position);
        if(!leader.getIsActive()) {leader.toggleActive();} //then send message of success
        else ; //send message back with failure notice

    }

}




class MarketController extends Controller{
    private PlayerBoard pb;
    private Market market;

    public MarketController(PlayerBoard pb, Market market){
        this.market = market;
        this.pb = pb;
    }

    public void takeResources(boolean isRow, int position){
        Marble[] marbles = null;

        if((isRow && position <= 3) || (!isRow && position <= 4) && position > 0) {
            try {marbles = this.market.takeResources(isRow, position);}
            catch (Exception e) {} //send back failure message
        }
        else {} //send back failure message

        Collection<Resource> resources;

        resources = this.convert(marbles);

        //send back to client message with resources serialized
    }

    private Collection<Resource> convert (Marble[] marbles){
        ArrayList<Resource> resources = new ArrayList<Resource>();
        ArrayList<LeaderCard> leaders = new ArrayList<LeaderCard>(Arrays.asList(pb.getLeaderCard(0), pb.getLeaderCard(1)));

        //filter leader cards for only actives and Transform type
        List<LeaderCard> listleaders = leaders.stream().filter(x -> x.getIsActive() && x instanceof LeaderTransform).collect(Collectors.toList());


        for(Marble elem : marbles){
            switch(elem){
                case RED:
                    pb.addFaith(1);
                    break;
                case BLUE:
                    resources.add(Resource.SHIELD);
                    break;
                case GRAY:
                    resources.add(Resource.STONE);
                    break;
                case YELLOW:
                    resources.add(Resource.COIN);
                    break;
                case PURPLE:
                    resources.add(Resource.SERVANT);
                    break;

                case WHITE:
                    if(listleaders.size() == 1){
                        resources.add(listleaders.get(0).getType());
                    }
                    //TODO: in case of two leaders, prop choice for each white resource to user.

                    break;
            }
        }

        return  resources;
    }

}