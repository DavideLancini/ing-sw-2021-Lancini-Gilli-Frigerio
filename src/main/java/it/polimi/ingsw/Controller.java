package it.polimi.ingsw;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private PlayerBoard pb;
    private DevCardBoard board;
    private Market market;

    public Controller(PlayerBoard pb, DevCardBoard board, Market market){
        this.pb = pb;
        this.board = board;
        this.market = market;
    }

    public void activateLeader (int position) {
        LeaderCard leader = this.pb.getLeaderCard(position);
        if(!leader.getIsActive()) {leader.toggleActive();} //then send message of success
        else ; //send message back with failure notice

    }

    public void tryDepotConfiguration(Resource[] input){
        //input length is built to be 10, padded with nulls if necessary
        if(valid(Arrays.copyOfRange(input, 1, 2))
        && valid(Arrays.copyOfRange(input, 3, 5))
        && valid(Arrays.copyOfRange(input, 6, 7))
        && valid(Arrays.copyOfRange(input, 8, 9))
        ){
            // return depotisLegal
        }
        else{
            //return depotisIllegal
        }
    }

    private boolean valid(Resource[] subset){
        Resource type = null;
        for(Resource elem : subset){
            if(elem == null)continue;
            if(type == null){type = elem; continue;}
            if(type != null && !type.equals(elem))return false;
        }
        return true;
    }

    public void buyDevCard(Level level, CardColor color){
        DevCard newCard;

        ArrayList<Resource> cost = extractCost(board.getCard(color, level).getCost());

        try{ newCard = board.buy(level, color, cost);}
        catch (Exception e ){/* send error message back to user*/}
    }

    private ArrayList<Resource> extractCost(Resource[] cost){
        ArrayList<Resource> resources= new ArrayList<>();
        Resource[] depotCopy = {null, null, null, null, null, null, null, null, null, null};
        for(int i=0; i<10; i++){
            depotCopy[i] = pb.getDepot().getResource(i);
        }

        //check if there are enough resources
        ArrayList<Resource> aldepot = new ArrayList(Arrays.asList(depotCopy));
        ArrayList<Resource> alsb = new ArrayList(pb.getStrongbox().getResources());


        for(Resource elem : cost){
            if(!aldepot.remove(elem)){
                if(!alsb.remove(elem)){
                    //send error, not enough resources
                }
            }
        }


        //try extracting the resource from the depot, if unavaible extract from the strongbox
        for(Resource elem : cost){
            try{resources.add(pb.getDepot().extract(Arrays.asList(depotCopy).indexOf(elem)));}
            catch(Exception e){
                resources.add(elem);
                try {pb.getStrongbox().extract(elem);}
                catch (Exception e2) {}
            }
        }

        return resources;
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
        ArrayList<Resource> resources = new ArrayList<>();
        ArrayList<LeaderCard> leaders = new ArrayList<>(Arrays.asList(pb.getLeaderCard(0), pb.getLeaderCard(1)));

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