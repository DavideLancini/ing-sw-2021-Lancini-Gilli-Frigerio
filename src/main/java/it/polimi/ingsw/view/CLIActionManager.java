package it.polimi.ingsw.view;


import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.model.CardColor;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.model.Reader;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.ClientMessage;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.*;

import java.util.Collection;
import java.util.logging.Logger;

public class CLIActionManager extends Manager {

    private static Logger logger;
    private ClientController ClientController;

    public static void setLogger(Logger logger){
        CLIActionManager.logger=logger;
    }


    public CLIActionManager(ClientController clicont){
        this.ClientController = clicont;
    }

    private static int parseToInt(String s) throws Exception{
        try{
            return Integer.parseInt(s);
        }
        catch(Exception e) {throw new Exception("");}
    }

    public static ClientNetInterface Connect() {
        ClientNetInterface net = new ClientNetInterface();
        String serverAddress;
        int serverPort;
        int localPort;

        while(true){
            System.out.println( "serverAddress: " );
            serverAddress=Reader.in.nextLine();

            System.out.println( "serverPort: " );
            try {
                serverPort= parseToInt(Reader.in.nextLine());
            } catch (Exception e) {
                return null;
            }

            System.out.println( "localPort: " );
            try {
                localPort= parseToInt(Reader.in.nextLine());
            } catch (Exception e) {
                return null;
            }

            net.setParameters(serverAddress, serverPort, localPort);
            try {
                net.connect();
                return net;
            } catch (DisconnectedException e) {
                //Do nothing, just restart the loop
            }
        }
    }

    public void DisplayError(String error){
        System.out.println("Server reports an error: "+ error);
    }


    public void ArrangeDepot(Collection<Resource> resources) throws DisconnectedException {
        System.out.println("These are the resources to be arranged in your depot: ");
        int i = 0;
        for(Resource each : resources){System.out.println(""+(i++)+". "+each.toString());}

        //TODO: collect new arrangement
        Resource[] newresources = null;
        ClientController.tryDepot(newresources);
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

    /**
     * MainMenu
     * @return selected action of player/client
     */
    public static String showMainMenu(){
        System.out.println( "1. Create Match" );
        System.out.println( "2. Join Match" );
        System.out.println( "3. View Public Match" );
        System.out.println( "4. Create Custom Rule Set" );
        System.out.println( "5. Settings" );
        System.out.println( "6. Credits" );

        String action;
        action = Reader.in.nextLine();

        return action;
    }

    /**
     * Create match ask number of players for starting game
     * @param net connection
     * @return number of player
     */
    public static void createMatch(ClientNetInterface net) throws DisconnectedException {
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
        ClientMessageCreateGame createMessage= new ClientMessageCreateGame(numOfPlayers);
        net.send(createMessage);
        ServerMessageOK ok=new ServerMessageOK();
        net.send(ok);



        //TODO: create MessageCreateGame
        //net.send (message)
        //TODO: entra nella funzione InGame che da ora pilota il client

    }
    public static void joinMatch(){}

}
