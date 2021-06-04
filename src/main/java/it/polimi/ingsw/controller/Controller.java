package it.polimi.ingsw.controller;


import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.ConnectionInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.ClientMessageTryDepotConfiguration;
import it.polimi.ingsw.network.messages.ServerMessageError;
import it.polimi.ingsw.network.messages.ServerMessageMarketReturn;
import it.polimi.ingsw.network.messages.ServerMessageOK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private PlayerBoard pb;
    private DevCardBoard board;
    private Market market;
    private ConnectionInterface net;

    public Controller(ConnectionInterface net, PlayerBoard pb, DevCardBoard board, Market market){
        this.pb = pb;
        this.board = board;
        this.market = market;
        this.net = net;
    }

    public Controller(PlayerBoard pb, DevCardBoard dcb, Market market) {
        this.pb = pb;
        this.board = dcb;
        this.market = market;
    }


    public void sellLeader (int position) throws DisconnectedException {
        if (!pb.getLeaderCard(position).getIsActive()) {
            pb.sellLeader(position);

            net.send(new ServerMessageOK());
        }
        else {
            net.send(new ServerMessageError("Cannot sell Active Leaders"));
        }
        return;
    }


    public void activateLeader (int position) throws DisconnectedException {

        LeaderCard leader = this.pb.getLeaderCard(position);

        if(!checkRequirements(leader)) {
            net.send(new ServerMessageError("Leader requirements not met"));

            return;
        }

        if(!leader.getIsActive()) {

            if (leader instanceof LeaderDepot)((LeaderDepot)leader).toggleActive(pb.getDepot());
            else leader.toggleActive();

            net.send(new ServerMessageOK());
        }

        else {
            net.send(new ServerMessageError("Leader already active"));

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
                       if(pb.getDevCard(i,j) != null) devCards.add(pb.getDevCard(i,j));
                }
            }

            for(CardColor each : requirements){
                for(int i=0; i<devCards.size(); i++) {
                    DevCard every = devCards.get(i);
                    if (every.getColor().equals(each)){
                        if(!devCards.remove(every)) return false;
                    }
                }
            }
            return true;

        }

    }



    public boolean tryDepotConfiguration(Resource[] input, int discardAmount) throws DisconnectedException {
        //input length is built to be 6
        if(valid(Arrays.copyOfRange(input, 1, 2))
        && valid(Arrays.copyOfRange(input, 3, 5))
        && different(input[0], input[1], input[3])
        ){
            net.send(new ServerMessageOK());
            //TODO: return depotisLegal
            //TODO: add faith to other players (discardAmount)
            return true;
        }
        else{
            net.send(new ServerMessageError("Invalid Depot Configuration"));
            return false;
        }
    }

    private boolean valid(Resource[] subset){
        Resource type = Resource.EMPTY;
        for(Resource elem : subset){
            if(elem == Resource.EMPTY)continue;
            if(type == Resource.EMPTY){type = elem; continue;}
            if(type != Resource.EMPTY && !type.equals(elem))return false;
        }
        return true;
    }

    private boolean different(Resource a, Resource b, Resource c){
        if(a == b && a != Resource.EMPTY) return false;
        else if(a == c && a != Resource.EMPTY) return false;
        else if(c == b && b != Resource.EMPTY) return false;
        else return true;
    }



    public boolean buyDevCard(Level level, CardColor color, int column) throws DisconnectedException {
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
            net.send(new ServerMessageError("Insufficient resources"));

            return false;
        }
        return true;
    }

    /**
     * @param activated array of booleans corrisponding to which productions are to be activated this action, 0 being the default,
     *                 1 to 3 the corresponding cards, and 4 to 5 the leader cards
     * @return
     */

    public boolean produce(boolean[] activated) throws DisconnectedException {
        ArrayList<Resource> totalinput = new ArrayList<>();
        ArrayList<Resource> totaloutput = new ArrayList<>();
        int totalfaith = 0;
        Resource[] choice=new Resource[2];
        Production[] productions = new Production[6];
        productions[0] = pb.getDefaultProduction();

        for(int i = 0; i<2 && activated[1+i]; i++){
            DevCard each = pb.getDevCard(i);
            if(each != null) productions[i+1] = each.getProduction();

            else {
                net.send(new ServerMessageError("Invalid Production"));

                return false;
            }
        }

        for(int i = 0; i<2 && activated[4+i]; i++){
            LeaderCard each = pb.getLeaderCard(i);
            if(each.getIsActive() && each instanceof LeaderProduction){
                //TODO: messagge setChoice()
                productions[i+4] = ((LeaderProduction) each).getProduction();
                choice[i]=((LeaderProduction) each).getChoice();
            }

            else {
                net.send(new ServerMessageError("Invalid Production"));

                return false;
            }
        }


        try {
            for(int i = 0; i<activated.length; i++) {
                if(activated[i]){
                    totalinput.addAll(Arrays.asList(productions[i].getInput()));
                    totaloutput.addAll(Arrays.asList(productions[i].getOutput()));
                    if (i>=4){
                        totaloutput.add(choice[i-4]);
                    }
                    totalfaith += productions[i].getFaith();
                }
            }

            totalinput = extractCost(totalinput.toArray(new Resource[totalinput.size()])  );

            pb.getStrongbox().deposit(totaloutput);
            pb.addFaith(totalfaith);

        }
        catch (Exception e){
            net.send(new ServerMessageError("Insufficient resources"));

            return false;
        }
        return true;
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

    public boolean takeResources(boolean isRow, int position) throws DisconnectedException {
        Marble[] marbles = null;

        if((isRow && position <= 3) || (!isRow && position <= 4) && position > 0) {
            try {marbles = this.market.takeResources(isRow, position);}
            catch (Exception e) {
                net.send(new ServerMessageError(e.getMessage()));
                return false;
            }
        }
        else {

            net.send(new ServerMessageError("Unable to acquire Market Resources."));
            return false;
        }

        Collection<Resource> resources;

        resources = this.convert(marbles);

        //Empties depot to be reorganized
        try {
            for (int i = 0; i < 6; i++) {
                Resource resource = pb.getDepot().extract(i);
                if (resource != Resource.EMPTY) resources.add(resource);
            }
        }
        catch(Exception e){
            //TODO: critical error, resources will get lost in process
            Server.logger.info("Critical error");
        }

        //Automatically deposits resources in leader depot slots
        try {
            for (int i = 0; i<resources.size(); i++) {
                Resource each = ((ArrayList<Resource>)resources).get(i);
                if (pb.getDepot().getLeaderType(0) == each) {
                    if (pb.getDepot().getResource(6) == Resource.EMPTY) {pb.getDepot().deposit(each, 6); each = Resource.EMPTY; continue;}
                    if (pb.getDepot().getResource(7) == Resource.EMPTY) {pb.getDepot().deposit(each, 7); each = Resource.EMPTY; continue;}
                }
                if (pb.getDepot().getLeaderType(1) == each) {
                    if (pb.getDepot().getResource(8) == Resource.EMPTY) {pb.getDepot().deposit(each, 8); each = Resource.EMPTY; continue;}
                    if (pb.getDepot().getResource(9) == Resource.EMPTY) {pb.getDepot().deposit(each, 9); each = Resource.EMPTY; continue;}
                }
            }
        }
        catch(Exception e){

            net.send(new ServerMessageError("Error during Leader Depot resource placement."));
            return false;
        }


        net.send(new ServerMessageMarketReturn(resources));
        Server.logger.info("About to try depot configuration");
        try{
            ((ClientMessageTryDepotConfiguration)(net.receive())).resolve(this);
        }
        catch (Exception e){}//TODO: temp
        Server.logger.info("Correctly tried depot configuration");
        return true;
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