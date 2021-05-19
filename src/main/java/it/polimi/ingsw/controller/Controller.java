package it.polimi.ingsw.controller;


import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.*;

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

        if(!checkRequirements(leader)) {
            ServerMessageError message = new ServerMessageError("Leader requirements not met");
            return; /*TODO: send message back with failure notice*/
        }

        if(!leader.getIsActive()) {

            if (leader instanceof LeaderDepot)((LeaderDepot)leader).toggleActive(pb.getDepot());
            else leader.toggleActive();

            ServerMessageOK message = new ServerMessageOK();
        } //TODO: then send message of success

        else {
            ServerMessageError message = new ServerMessageError("Leader already active"); //TODO: send message back with failure notice

        }

    }

    private boolean checkRequirements(LeaderCard leader){
        if(leader instanceof LeaderProduction){
            for(int i=0; i<3; i++) {
                DevCard card = this.pb.getDevCard(i, 1);
                if(card != null && card.getColor() == ((LeaderProduction) leader).getRequirements())return true;
            }
            return  false;

        }


        else if(leader instanceof LeaderDepot){
            int check = 0;
            Resource requirements = ((LeaderDepot) leader).getRequirements();

            for (int i = 0; i<10; i++){
                if(pb.getDepot().getResource(i) == requirements) check++;
            }
            if (check>=5) return true;
            else {
                ArrayList<Resource> left = null;
                for(int i = 0; i< 5-check; i++) left.add(requirements);
                if(this.pb.getStrongbox().contains(left)) return  true;
                else{return false;}
            }

        }

        else {

            CardColor[] requirements;

            if(leader instanceof  LeaderTransform) requirements = ((LeaderTransform) leader).getRequirements();
            else requirements = ((LeaderSale) leader).getRequirements();

            ArrayList<DevCard> devCards = new ArrayList<>();
            for (int i = 0; i<3; i++){
                for (int j = 0; j<3; j++){
                       devCards.add(pb.getDevCard(i,j));
                }
            }

            for(CardColor each : requirements){
                if(!devCards.remove(each))return false;
            }
            return true;

        }

    }



    public void tryDepotConfiguration(Resource[] input){
        //input length is built to be 10, padded with nulls if necessary
        if(valid(Arrays.copyOfRange(input, 1, 2))
        && valid(Arrays.copyOfRange(input, 3, 5))
        && valid(Arrays.copyOfRange(input, 6, 7))
        && valid(Arrays.copyOfRange(input, 8, 9))
        ){
            ServerMessageOK message = new ServerMessageOK();
            //TODO: return depotisLegal
        }
        else{
            ServerMessageError message = new ServerMessageError("Invalid Depot Configuration");
            //TODO: return depotisIllegal
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

    public void buyDevCard(Level level, CardColor color, int column){
        DevCard newCard;
        Resource[] stdCost = board.getCard(color, level).getCost();

        //LeaderSale effect
        if (pb.getLeaderCard(0) != null && pb.getLeaderCard(0).getIsActive() && pb.getLeaderCard(0) instanceof LeaderSale){
            stdCost = ((LeaderSale) pb.getLeaderCard(0)).downPrice(stdCost);
        }
        if (pb.getLeaderCard(1) != null &&pb.getLeaderCard(1).getIsActive() && pb.getLeaderCard(1) instanceof LeaderSale){
            stdCost = ((LeaderSale) pb.getLeaderCard(0)).downPrice(stdCost);
        }


        // Buy Card
        try{
            ArrayList<Resource> cost = extractCost(stdCost);
            newCard = board.buy(level, color, cost);
            pb.addDevCard(newCard, column);
        }

        catch (Exception e ){
            ServerMessageError message = new ServerMessageError("Insufficient resources");
            /*TODO: send error message back to user, insufficient resources*/
        }
    }

    /**
     * @param activated array of booleans corrisponding to which productions are to be activated this action, 0 being the default,
     *                 1 to 3 the corresponding cards, and 4 to 5 the leader cards
     */

    public void produce(boolean[] activated){
        ArrayList<Resource> totalinput = new ArrayList<>();
        ArrayList<Resource> totaloutput = new ArrayList<>();
        int totalfaith = 0;

        Production[] leaderproductions = new Production[]{null, null};
        LeaderCard[] leaders = {pb.getLeaderCard(0), pb.getLeaderCard(1)};
        for(int i = 0; i<2 && activated[4+i]; i++){
            LeaderCard each = pb.getLeaderCard(i);
            if(each.getIsActive() && each instanceof LeaderProduction) leaderproductions[i] = ((LeaderProduction) each).getProduction();

            else {
                ServerMessageError message = new ServerMessageError("Invalid Production");
                /*TODO: send error message back to user*/
            }
        }

        Production[] productions = new Production[]{pb.getDefaultProduction(), pb.getDevCard(0).getProduction(), pb.getDevCard(1).getProduction(),
                pb.getDevCard(2).getProduction(), leaderproductions[0], leaderproductions[1]};


        try {
            for(int i = 0; i<activated.length; i++) {
                if(activated[i]){
                    totalinput.addAll(Arrays.asList(productions[i].getInput()));
                    totaloutput.addAll(Arrays.asList(productions[i].getOutput()));
                    totalfaith += productions[i].getFaith();
                }
            }

            totalinput = extractCost(totalinput.toArray(new Resource[totalinput.size()])  );

            pb.getStrongbox().deposit(totaloutput);
            pb.addFaith(totalfaith);

        }
        catch (Exception e){
            ServerMessageError message = new ServerMessageError("Insufficient resources");
            /*TODO: send error message back to user, insufficient resources*/
        }
    }


    /**
     *
     * @param position 0 to 1 for defaultProduciton input, 2 for output, 3 to 4 for LeaderProduction choice
     * @param resource Resource to be set
     */
    public void setResource(int position, Resource resource){
        if(position == 0 || position == 1) {pb.getDefaultProduction().setInput(resource, position); return;}
        if(position == 2) {pb.getDefaultProduction().setOutput(resource); return;}

        LeaderCard leader = pb.getLeaderCard(position-3);
        if((position == 3 || position == 4) && leader instanceof LeaderProduction) ((LeaderProduction) leader).setChoice(resource);
    }













    private ArrayList<Resource> extractCost(Resource[] cost) throws Exception{
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
                   throw new Exception("not enough resources in storage");
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
        else {ServerMessageError message = new ServerMessageError("Unable to acquire Market Resources.");} //TODO: send back failure message

        Collection<Resource> resources;

        resources = this.convert(marbles);

        //TODO: send back to client message with resources serialized
        ServerMessageMarketReturn message = new ServerMessageMarketReturn(resources);
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