package it.polimi.ingsw.view;


import it.polimi.ingsw.Client;
import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.*;

import java.util.Collection;
import java.util.logging.Logger;

public class CLIActionManager extends Manager {


    private static Logger logger = Client.logger;
    private static ClientNetInterface net;
    private ClientController ClientController;

    public static void setLogger(Logger logger){
        CLIActionManager.logger=logger;
    }


    public CLIActionManager(ClientController clicont){
        this.ClientController = clicont;
    }

    private static int parseToInt(String s) throws NumberFormatException{
        try{
            return Integer.parseInt(s);
        }
        catch(NumberFormatException e) {throw new NumberFormatException("");}
    }

    public static ClientNetInterface Connect() throws DisconnectedException{
        String serverAddress;
        int serverPort;

        while(true){
            System.out.println( "serverAddress: " );
            serverAddress=Reader.in.nextLine();

            System.out.println( "serverPort: " );
            try {
                serverPort= parseToInt(Reader.in.nextLine());
            } catch (NumberFormatException e) {
                return null;
            }

            try {
                net = new ClientNetInterface(serverAddress,serverPort, Client.logger);
            } catch (DisconnectedException e) {
                //warning
            }
        }
    }

    public static void autoConnect() throws DisconnectedException{
        net = new ClientNetInterface("localhost",5555, Client.logger);
    }

    public void DisplayError(String error){
        System.out.println("Server reports an error: "+ error);
    }


    public ClientMessage ArrangeDepot(Collection<Resource> resources) {
        System.out.println("These are the resources to be arranged in your depot: ");

        int servant = 0;int shield = 0;int coin = 0;int stone = 0;
        for (Resource resource : resources) {
            switch(resource){
                case COIN: coin++; break;
                case SERVANT: servant++; break;
                case SHIELD: shield++; break;
                case STONE: stone++; break;
            }
        }
        if (servant > 0) System.out.print(servant+""+Resource.SERVANT);
        if (coin > 0) System.out.print(coin+""+Resource.COIN);
        if (shield > 0) System.out.print(shield+""+Resource.SHIELD);
        if (stone> 0) System.out.print(stone+""+Resource.STONE);
        System.out.println("");

        Resource[] newresources = new Resource[]{Resource.EMPTY, Resource.EMPTY, Resource.EMPTY, Resource.EMPTY, Resource.EMPTY, Resource.EMPTY};
        Resource choice[] = new Resource[3];

        for (int i = 0; i<3; i++){
            System.out.println("Choose which resource to put in row n° "+i+": ");
            System.out.println("1: "+Resource.SERVANT+"\t "+"2: "+Resource.COIN+"\t "+"3: "+Resource.SHIELD+"\t "+"4: "+Resource.STONE+"\t ");
            try {
                switch (parseToInt(Reader.in.nextLine())) {
                    case 1: choice[i] = Resource.SERVANT;
                    case 2: choice[i] = Resource.COIN;
                    case 3: choice[i] = Resource.SHIELD;
                    case 4: choice[i] = Resource.STONE;
                    default: throw new NumberFormatException("");
                }
            }
            catch(NumberFormatException e){
                System.out.println("Number invalid, please retry.");
                i--; continue;
            }
        }

        if(resources.remove(choice[0]))newresources[0] = choice[0];
        if(resources.remove(choice[1]))newresources[1] = choice[1];
        if(resources.remove(choice[1]))newresources[2] = choice[1];
        if(resources.remove(choice[2]))newresources[3] = choice[2];
        if(resources.remove(choice[2]))newresources[4] = choice[2];
        if(resources.remove(choice[2]))newresources[5] = choice[2];

        return new ClientMessageTryDepotConfiguration(newresources, resources.size());
    }



    public static boolean Online(){
        while(true){
            System.out.println( "1.[online]\n2.[local]");
            switch(Reader.in.nextLine()){
                case "1":
                    return true;
                default:
                    return false;
            }
        }
    }

    /**
     * CLI Interface for in-game actions
     * @param mainActionDone true if already took resources, bought a card or produced in this game turn
     * @return message to be sent back to server
     */
    public ClientMessage Turn(boolean mainActionDone){

        while (true){

            System.out.println( "1. Take resources from market" );
            System.out.println( "2. Buy Development card");
            System.out.println( "3. Activate productions");
            System.out.println( "4. Activate Leader card");
            System.out.println( "5. Sell Leader card");
            System.out.println( "6. Set optional resources");
            System.out.println( "7. End Turn");

            try {
                int choice = parseToInt(Reader.in.nextLine());

                switch (choice) {

                    case 1:
                        if(mainActionDone){
                            System.out.println("Already done an action this turn!");
                            break;
                        }

                        System.out.println("0 for row, 1 for column");
                        boolean isRow = parseToInt(Reader.in.nextLine()) == 1 ? false : true;
                        System.out.println("Enter number of "+(isRow ? "row" : "column")+":");
                        int position = parseToInt(Reader.in.nextLine());
                        return new ClientMessageTakeResources(isRow, position);

                    case 2:

                        if(mainActionDone){
                            System.out.println("Already done an action this turn!");
                            break;
                        }

                        System.out.println("Level:");
                        Level level = Level.values()[parseToInt(Reader.in.nextLine())-1];

                        System.out.println("Color: ");
                        for (int i = 0; i<CardColor.values().length; i++){System.out.println(""+i+". "+CardColor.values()[i].toString());};
                        CardColor color = CardColor.values()[parseToInt(Reader.in.nextLine())];

                        System.out.println("Column in which to put it: ");
                        int column = parseToInt(Reader.in.nextLine());

                        return new ClientMessageBuyDevCard(level, color, column);


                    case 3:

                        if(mainActionDone){
                            System.out.println("Already done an action this turn!");
                            break;
                        }

                        boolean[] activated = new boolean[6];
                        String[] text = {
                                "1 to produce with default production, 0 to skip it",
                                "1 to produce with DevCard in first column, 0 to skip it",
                                "1 to produce with DevCard in second column, 0 to skip it",
                                "1 to produce with DevCard in third column, 0 to skip it",
                                "1 to produce with first Leader Card, 0 to skip it",
                                "1 to produce with second Leader Card, 0 to skip it"
                        };

                        for(int i = 0; i<6; i++){
                            System.out.println(text[i]);
                            activated[i] = parseToInt(Reader.in.nextLine()) == 1 ? true : false;
                        }

                        return new ClientMessageProduce(activated);


                    case 4:
                        System.out.println("Enter number of Leader Card to be activated");
                        int pos = parseToInt(Reader.in.nextLine());
                        return new ClientMessageLeaderActivation(pos);

                    case 5:
                        System.out.println("Enter number of Leader Card to be sold");
                        int number = parseToInt(Reader.in.nextLine());
                        return new ClientMessageSellLeader(number);

                    case 6:
                        System.out.println("Enter number of resource to be set. 0 to 1 for defaultProduciton input, 2 for output, 3 to 4 for LeaderProduction choice");
                        int res = parseToInt(Reader.in.nextLine());

                        System.out.println("Resource: ");
                        for (int i = 0; i < Resource.values().length -2; i++){System.out.println(""+i+". "+Resource.values()[i].toString());};
                        Resource resource = Resource.values()[parseToInt(Reader.in.nextLine())];

                        return new ClientMessageSetResource(resource, res);

                    case 7:
                        return new ClientMessageEndTurn();

                    default:
                        break;
                }


            }
            catch(Exception e){
                System.out.println("There's been an error: "+e);
                continue;
            }
        }

    }

    public ClientMessage ChooseLeaders(String[] leaders){
        System.out.println("Choose two of the following four Leaders:");
        int i = 0, j = 0;
        for (String each : leaders) {
            i++;
            System.out.println(i+":\n"+each);

        }
        i=0;
        do {
            try {
                System.out.println("Enter the number of the first chosen Leader:");
                i = parseToInt(Reader.in.nextLine());
                System.out.println("Enter the number of the second chosen Leader:");
                j = Reader.in.nextInt();
            }
            catch (NumberFormatException e) {
                System.out.println("There's been an error, please retry.");
                continue;}
        }
        while (!(i!=j && i <= 4 && j <= 4 && i >= 1 && j >= 1));
        return new ClientMessageChosenLeaders(i,j);
    }




    /**
     * MainMenu
     * @return selected action of player/client
     */
    public static String[] showMainMenu() {
        String action;
        String playerID;
        System.out.println("playerID:");
        playerID = Reader.in.nextLine();

        System.out.println("1. Join Game");
        System.out.println("2. Create Custom Game");
        System.out.println("3. Join Custom Game");
        System.out.println("4. Create Custom Rule Set");
        System.out.println("5. Settings");
        System.out.println("6. Credits");
        action = Reader.in.nextLine();


        return new String[]{action, playerID};
    }

    /**
     * Create match ask number of players for starting game
     * @param s
     * @return number of player
     */
    public static void createCustomMatch(String s) throws DisconnectedException {
        int numOfPlayers=0;
        boolean correctInput=false;
        while (!correctInput) {
            System.out.println("Number of players?");
            numOfPlayers = Reader.in.nextInt();
            if (numOfPlayers>4||numOfPlayers<1)
                System.out.println("selected number from 1 to 4");
            else
                correctInput=true;
        }
        ClientMessageCreateGame createMessage= new ClientMessageCreateGame(numOfPlayers,s);
        net.send(createMessage);
        ServerMessageView serverMessage= (ServerMessageView) net.receive();
        System.out.println(serverMessage.view);



        //TODO: create MessageCreateGame
        //net.send (message)
        //TODO: entra nella funzione InGame che da ora pilota il client

    }

    public static void joinMatch(String playerId) throws DisconnectedException {
        System.out.println("How many player? ");
        int gameMode = Reader.in.nextInt();
        ClientMessageJoinGame messageJoin = new ClientMessageJoinGame(playerId, gameMode);
        net.send(messageJoin);

        ClientController controller = new ClientController(true);
        controller.setup(net);
        controller.main();
    }
}
