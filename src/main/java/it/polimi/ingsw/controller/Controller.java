package it.polimi.ingsw.controller;


import it.polimi.ingsw.Server;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.ConnectionInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Controller
 * @author Gruppo 12
 */
public class Controller {

    private final PlayerBoard pb;
    private final DevCardBoard board;
    private final Market market;
    private ConnectionInterface net;
    private Game game;

    /**
     * Constructor of controller
     * @param net network information
     * @param pb Player to control
     * @param board DevCards board
     * @param market current Market
     * @param game current Game
     */
    public Controller(ConnectionInterface net, PlayerBoard pb, DevCardBoard board, Market market, Game game){
        this.pb = pb;
        this.board = board;
        this.market = market;
        this.net = net;
        this.game = game;
    }
//Used in tests only i think
    public Controller(PlayerBoard pb, DevCardBoard dcb, Market market) {
        this.pb = pb;
        this.board = dcb;
        this.market = market;
    }

    /**
     * sellLeader deletes one leaderCard for 1 faith
     * @param position selection of card (1 or 2)
     * @throws DisconnectedException if the is a disconnection during the game
     */
    public void sellLeader (int position) throws DisconnectedException {
        if(position != 0 && position!= 1){net.send(new ServerMessageError("Invalid Position.")); return;}

        if (!pb.getLeaderCard(position).getIsActive()) {
            pb.sellLeader(position);

            net.send(new ServerMessageOK());
        }
        else {
            net.send(new ServerMessageError("Cannot sell Active Leaders"));
        }
    }

    /**
     * activateLeader activates one leader card if requirements are met
     * @param position selection of card (1 or 2)
     * @throws DisconnectedException if the is a disconnection during the game
     */
    public void activateLeader (int position) throws DisconnectedException {
        Server.logger.info("Activating leader");
        if(position != 0 && position!= 1){net.send(new ServerMessageError("Invalid Position.")); return;}

        LeaderCard leader = this.pb.getLeaderCard(position);

        if(leader == null) {
            net.send(new ServerMessageError("You don't have a Leader Card at this position."));

            return;
        }
        Server.logger.info("Checking requirements");
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

    /**
     * checks requirement during a leader card activation
     * @param leader card to be activated
     * @return true if requirement are met
     */
    private boolean checkRequirements(LeaderCard leader){

        if(leader instanceof LeaderProduction){
            Server.logger.info("Found LeaderProduction");
            for(int i=0; i<3; i++) {
                DevCard card = this.pb.getDevCard(i, 1);
                if(card != null && card.getColor() == ((LeaderProduction) leader).getRequirements())return true;
            }
            return  false;

        }


        else if(leader instanceof LeaderDepot){
            Server.logger.info("Found LeaderDepot");
            int check = 0;
            Resource requirements = ((LeaderDepot) leader).getRequirements();

            for (int i = 0; i<10; i++){
                if(pb.getDepot().getResource(i) == requirements) check++;
            }
            if (check>=5) return true;
            else {
                ArrayList<Resource> left = new ArrayList<>();
                for(int i = 0; i< 5-check; i++) left.add(requirements);
                return this.pb.getStrongbox().contains(left);
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
            Server.logger.info("Requirements: "+ Arrays.toString(requirements));
            Server.logger.info("Devcards: "+devCards.toString());

            for(CardColor each : requirements){
                boolean found = false;
                Server.logger.info("Requirement: "+each.toString());
                for(int i=0; i<devCards.size(); i++) {
                    DevCard every = devCards.get(i);
                    if (every.getColor().equals(each)){
                        if(!devCards.remove(every)) return false;
                        else {found = true; break;}
                    }
                    Server.logger.info("Devcards: "+devCards.toString());
                }
                if(!found) return false;
            }
            return true;

        }

    }


    /**
     * tryDepotConfiguration is used while taking resources from Market
     * @param input resources to be tested
     * @param discardAmount number of resources not deposited  and faith to give to other players
     * @return true if resources configuration is within Game's rules
     * @throws DisconnectedException if there is a disconnection during the game
     */
    public boolean tryDepotConfiguration(Resource[] input, int discardAmount) throws DisconnectedException {
        //input length is built to be 6
        if(valid(Arrays.copyOfRange(input, 1, 2))
        && valid(Arrays.copyOfRange(input, 3, 5))
        && different(input[0], input[1], input[3])
        ){
            net.send(new ServerMessageOK());
            Server.logger.info(pb.getDepot().depotView());
            pb.getDepot().setContents(input);
            Server.logger.info(pb.getDepot().depotView());
            game.discardsToFaith(pb, discardAmount);

            return true;
        }
        else{
            net.send(new ServerMessageError("Invalid Depot Configuration"));
            return false;
        }
    }

    /**
     * checks if resources in one shelf are equals or nulls
     * @param subset resources to be tested
     * @return true if is all within Game's rules
     */
    private boolean valid(Resource[] subset){
        Resource type = Resource.EMPTY;
        for(Resource elem : subset){
            if(elem == Resource.EMPTY)continue;
            if(type == Resource.EMPTY){type = elem; continue;}
            if(!type.equals(elem))return false;
        }
        return true;
    }

    /**
     * checks if resources are different in all three shelves
     * @param a first shelf resource type
     * @param b second shelf resource type
     * @param c third shelf resource type
     * @return true if is all within Game's rules
     */
    private boolean different(Resource a, Resource b, Resource c){
        if(a == b && a != Resource.EMPTY) return false;
        else if(a == c && a != Resource.EMPTY) return false;
        else return c != b || b == Resource.EMPTY;
    }


    /**
     * buy devCard from the development cards board
     * @param level level of card the player wants to buy
     * @param color color of card the player wants to buy
     * @param column in which available slot of player board the player wants to place the card
     * @return true if buying is successful
     * @throws DisconnectedException if the is a disconnection during the game
     */
    public boolean buyDevCard(Level level, CardColor color, int column) throws DisconnectedException {
        DevCard newCard;

        if(board.getCard(color, level) == null){
            net.send(new ServerMessageError("There are no more cards of this type."));
            return false;
        }

        if(!pb.checkAddable(board.getCard(color, level), column)){
            net.send(new ServerMessageError("Cannot add the card in this column."));
            return false;
        }

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
        net.send(new ServerMessageOK());
        return true;
    }

    /**
     * @param activated array of booleans corrisponding to which productions are to be activated this action, 0 being the default,
     *                 1 to 3 the corresponding cards, and 4 to 5 the leader cards
     * @return true if action was performed successfully
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
                net.send(new ServerMessageError("Invalid Production. There is no Development Card in position" +(i+1)+ "."));

                return false;
            }
        }

        for(int i = 0; i<2; i++){
            LeaderCard each = pb.getLeaderCard(i);
            if(activated[4+i] && each != null && each.getIsActive() && each instanceof LeaderProduction){
                productions[i+4] = ((LeaderProduction) each).getProduction();
                choice[i]=((LeaderProduction) each).getChoice();
            }

            else {
                net.send(new ServerMessageError("Invalid Production. There is no leader in position " + (i+1)+" capable of producing."));

                return false;
            }
        }


        try {
            for(int i = 0; i<activated.length; i++) {
                if(activated[i]){
                    Server.logger.info("Producing "+i+"with input "+ Arrays.toString(productions[i].getInput()) +" and output "+ Arrays.toString(productions[i].getOutput()));
                    totalinput.addAll(Arrays.asList(productions[i].getInput()));
                    totaloutput.addAll(Arrays.asList(productions[i].getOutput()));
                    if (i>=4){
                        totaloutput.add(choice[i-4]);
                    }
                    totalfaith += productions[i].getFaith();
                }
            }
            Server.logger.info("Total required of: "+totalinput);
            extractCost(totalinput.toArray(new Resource[0]));
            Server.logger.info("Extracted.");

            pb.getStrongbox().deposit(totaloutput);
            Server.logger.info("Deposited.");
            pb.addFaith(totalfaith);

        }
        catch (Exception e){
            Server.logger.info(e.getMessage());
            net.send(new ServerMessageError("Your resources are insufficient for the indicated productions."));
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
        Server.logger.info("Extraction started.");
        ArrayList<Resource> resources= new ArrayList<>();
        Resource[] depotCopy = {null, null, null, null, null, null, null, null, null, null};
        for(int i=0; i<10; i++){
            depotCopy[i] = pb.getDepot().getResource(i);
        }

        //check if there are enough resources
        ArrayList<Resource> aldepot = new ArrayList<>(Arrays.asList(depotCopy));
        ArrayList<Resource> alsb = new ArrayList<>(pb.getStrongbox().getResources());


        for(Resource elem : cost){
            if(!aldepot.remove(elem)){
                if(!alsb.remove(elem)){
                   throw new Exception("not enough resources in storage");
                }
            }
        }


        //try extracting the resource from the depot, if unavaible extract from the strongbox
        for(Resource elem : cost){
            try{
                int index = Arrays.asList(depotCopy).indexOf(elem);
                resources.add(pb.getDepot().extract(index));
                depotCopy[index] = Resource.EMPTY;
            }
            catch(Exception e){
                pb.getStrongbox().extract(elem);
                resources.add(elem);
            }
        }

        return resources;
    }

    /**
     * market action
     * @param isRow true if player want to take marbles from a row
     * @param position number of row(1 to 3) / column (1 to 4)
     * @return true if successful
     * @throws DisconnectedException if the is a disconnection during the game
     */
    public boolean takeResources(boolean isRow, int position) throws DisconnectedException {
        Marble[] marbles;

        if((isRow && position <= 3) || (!isRow && position <= 4) && position > 0) {
            try {marbles = this.market.takeResources(isRow, position);}
            catch (Exception e) {
                net.send(new ServerMessageError(e.getMessage()));
                return false;
            }
        }
        else {

            net.send(new ServerMessageError("Invalid position request."));
            return false;
        }

        Collection<Resource> resources;

        resources = this.convert(marbles);
        Server.logger.info("Marbles converted, collected: "+resources);
        Server.logger.info(pb.getDepot().depotView());
        //Empties depot to be reorganized
        try {
            for (int i = 0; i < 6; i++) {
                Resource resource = pb.getDepot().extract(i);
                if (resource != Resource.EMPTY) resources.add(resource);
            }
            Server.logger.info("Depot Emptied, total collected: "+resources);
        }
        catch(Exception e){
            //TODO: critical error, resources will get lost in process
            Server.logger.info("Critical error");
        }
        Server.logger.info(pb.getDepot().depotView());


        //Automatically deposits resources in leader depot slots
        try {
            for (int i = 0; i<resources.size(); i++) {
                Resource each = ((ArrayList<Resource>)resources).get(i);
                if (pb.getDepot().getLeaderType(0) == each) {
                    if (pb.getDepot().getResource(6) == Resource.EMPTY) {pb.getDepot().deposit(each, 6); continue;}
                    if (pb.getDepot().getResource(7) == Resource.EMPTY) {pb.getDepot().deposit(each, 7); continue;}
                }
                if (pb.getDepot().getLeaderType(1) == each) {
                    if (pb.getDepot().getResource(8) == Resource.EMPTY) {pb.getDepot().deposit(each, 8); continue;}
                    if (pb.getDepot().getResource(9) == Resource.EMPTY) {pb.getDepot().deposit(each, 9);
                    }
                }
            }
        }
        catch(Exception e){

            net.send(new ServerMessageError("Error during Leader Depot resource placement."));
            return false;
        }


        do {
            net.send(new ServerMessageMarketReturn(resources));
            Server.logger.info("About to try depot configuration");
        }
        while(!((ClientMessageTryDepotConfiguration) (net.receive())).resolve(this));

        Server.logger.info("Correctly tried depot configuration");
        return true;
    }

    /**
     * converts marbles in resources
     * @param marbles taken from market
     * @return resources to place in the deposit
     * @throws DisconnectedException if the is a disconnection during the game
     */
    private Collection<Resource> convert (Marble[] marbles) throws DisconnectedException {
        ArrayList<Resource> resources = new ArrayList<>();
        ArrayList<LeaderCard> leaders = new ArrayList<>(Arrays.asList(pb.getLeaderCard()));

        //filter leader cards for only actives and Transform type
        List<LeaderCard> listleaders = leaders.stream().filter(x -> x!=null && x.getIsActive() && x instanceof LeaderTransform).collect(Collectors.toList());


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
                    else if (listleaders.size() == 2){
                        net.send(new ServerMessageTwoMarbleLeaders(listleaders.get(0).getType(), listleaders.get(1).getType()));
                        ClientMessageChosenWhite message = (ClientMessageChosenWhite) net.receive();
                        resources.add(listleaders.get(message.getPosition() ? 1 : 0).getType());
                    }

                    break;
            }
        }

        return  resources;
    }

}