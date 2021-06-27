package it.polimi.ingsw.view;


import it.polimi.ingsw.Client;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.*;

import java.util.Collection;
import java.util.logging.Logger;

public class CLIActionManager extends Manager {


    private static Logger logger = Client.logger;
    private static ClientNetInterface net;

    /**
     * Read a number from user input
     * @return a valid number
     */
    private static int readInt(){
        while(true) {
            String in = Reader.in.nextLine();
            try {
                return Integer.parseInt(in);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please retry.");
            }
        }
    }

    /**
     * Read a valid number from user input contained in range
     * @param min minimum number accepted
     * @param max maximum number accepted
     * @return a valid number
     */
    private static int readInt(int min, int max){
        while(true) {
            String in = Reader.in.nextLine();
            try {
                int read = Integer.parseInt(in);
                if(read >= min && read <= max) return read;
                else System.out.println("Number out of range, please retry.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please retry.");
            }
        }
    }


    public void view(ServerMessageView s){System.out.println(s.getView(true));}



 /*   CREDO CI SIA QUALCOSA DI SBAGLIATO CON QUESTO METODO, FINCHE' NON E' UTILIZZATO TANTO VALE TENERLO COMMENTATO
 public static ClientNetInterface connect() throws DisconnectedException{
        String serverAddress;
        int serverPort;

        while(true){
            System.out.println( "serverAddress: " );
            serverAddress=Reader.in.nextLine();

            System.out.println( "serverPort: " );
            try {
                serverPort= readInt();
            } catch (NumberFormatException e) {
                return null;
            }

            try {
                net = new ClientNetInterface(serverAddress,serverPort, Client.logger);
            } catch (DisconnectedException e) {
                //warning
            }
        }
    }*/



    public void displayError(String error){
        System.out.println("Server reports an error: "+ error);
        System.out.println("Press Enter to continue.");
        Reader.in.nextLine();
    }


    public ClientMessage arrangeDepot(Collection<Resource> resources) {

        Resource[] newResources = new Resource[]{Resource.EMPTY,Resource.EMPTY,Resource.EMPTY,Resource.EMPTY,Resource.EMPTY,Resource.EMPTY};
        Resource[] choice = new Resource[3];

        System.out.println("These are the resources to be arranged in your depot: ");
        System.out.println(ResourceCounter.count(resources));

        for (int i = 1; i < 4; i++) {
            System.out.print("Choose  which resources to put in row n° " + i + "  (Max "+i+" resources): ");
            for (int j = 1; j <= Resource.values().length - 1; j++) {
                System.out.print("" + j + ". " + Resource.values()[j - 1].toString() + "   ");
                    }
                    System.out.println();
                    choice[i-1] = Resource.values()[readInt(1,5) - 1];
                }

        Manager.assignDepot(resources, newResources, choice);

        return new ClientMessageTryDepotConfiguration(newResources, resources.size());
    }



    public boolean online(){
        System.out.println( "1.[online]\n2.[local]");
        return "1".equals(Reader.in.nextLine());
    }

    @Override
    public void waitForTurn() {
        System.out.println("Waiting for your turn...\n\n");
    }

    /**
     * CLI Interface for in-game actions
     * @param mainActionDone true if already took resources, bought a card or produced in this game turn
     * @return message to be sent back to server
     */
    public ClientMessage turn(boolean mainActionDone){

        while (true){

            if(!mainActionDone) {
                System.out.println("1. Take resources from market");
                System.out.println("2. Buy Development card");
                System.out.println("3. Activate productions");
            }
            System.out.println( "4. Activate Leader card");
            System.out.println( "5. Sell Leader card");
            System.out.println( "6. Set optional resources");
            System.out.println( "7. End Turn");

            try {
                int choice = readInt(1,7);

                switch (choice) {

                    case 1: //TAKE RESOURCES FROM MARKET
                        if(mainActionDone){
                            System.out.println("Already done an action this turn!");
                            break;
                        }

                        System.out.println("ROW: [1], COLUMN: [2]");
                        boolean isRow = readInt(1,2) == 1;
                        System.out.println("Enter number of "+(isRow ? "row" : "column")+":");
                        int position = isRow? readInt(1,3) : readInt(1,4);
                        return new ClientMessageTakeResources(isRow, position);

                    case 2: // BUY DEVCARD

                        if(mainActionDone){
                            System.out.println("Already done an action this turn!");
                            break;
                        }

                        System.out.println("Level:");
                        Level level = Level.values()[readInt(1,Level.values().length) - 1];

                        System.out.println("Color: ");
                        for (int i = 1; i <= CardColor.values().length; i++) {
                            System.out.print("" + i + ". " + CardColor.values()[i-1].toString()+" ");
                        }
                        System.out.println();
                        CardColor color = CardColor.values()[readInt(1,CardColor.values().length)-1];

                        System.out.println("Column in which to put it: ");
                        int column = readInt(1,3)-1;
                        return new ClientMessageBuyDevCard(level, color, column);



                    case 3: //PRODUCE

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
                            activated[i] = readInt(0,1) == 1;
                        }

                        return new ClientMessageProduce(activated);


                    case 4: //ACTIVATE LEADER
                        System.out.println("Enter number of Leader Card to be activated (1 or 2):");
                        int pos = readInt(1,2);
                        return new ClientMessageLeaderActivation(pos-1);

                    case 5: //SELL LEADER
                        System.out.println("Enter number of Leader Card to be sold: (1 or 2)");
                        int number = readInt(1,2);
                        return new ClientMessageSellLeader(number-1);

                    case 6: //SET RESOURCE
                        System.out.println("Enter number of resource to be set. 1 to 2 for defaultProduciton input, 3 for output, 4 to 5 for LeaderProduction choice");
                        int res = readInt(1,5);

                        System.out.print("Resource: ");
                        for (int i = 1; i <= Resource.values().length -2; i++){System.out.print(""+i+". "+Resource.values()[i-1].toString()+"   ");}
                        System.out.println();
                        Resource resource = Resource.values()[readInt()-1];


                        return new ClientMessageSetResource(resource, res-1);

                    case 7: //ENDTURN
                        waitForTurn();
                        return new ClientMessageEndTurn();

                    default:
                        break;
                }


            }
            catch(Exception e){
                System.out.println("There's been an error: "+e);
            }
        }

    }

    public ClientMessage chooseLeaders(ServerMessageChooseLeaders message){
        String[] leaders = message.getLeaders(true);
        System.out.println("Choose two of the following four Leaders:");
        int i = 0, j;
        String[] sleaders = new String[4];
        for (String each : leaders) {
            i++;
            sleaders[i-1] = ""+i+":\t\t\t\n"+each;
        }
        System.out.println(ViewHelper.displayS2S(sleaders));

        System.out.println("Enter the number of the first chosen Leader:");
        i = readInt(1,4);
        System.out.println("Enter the number of the second chosen Leader:");
        j = readInt(1,4);

        return new ClientMessageChosenLeaders(i-1,j-1);
    }




    /**
     * MainMenu
     * @return selected action of player/client
     */
    public String[] showMainMenu() {
        String action;
        String playerID;
        System.out.println("playerID:");
        playerID = Reader.in.nextLine();
        if(playerID.equals(""))playerID = "player"+(int)Math.floor(Math.random()*1000000);

        System.out.println("1. Join Game");
        System.out.println("2. Create Custom Game");
        System.out.println("3. Join Custom Game");
        System.out.println("4. Create Custom Rule Set");
        System.out.println("5. Settings");
        System.out.println("6. Credits");
        action = String.valueOf(readInt(1,7));


        return new String[]{action, playerID};
    }

    public ClientMessage addResource(){
        System.out.print("[Select your starting resource]: ");
        for (int i = 1; i <= Resource.values().length -2; i++){System.out.print(""+i+". "+Resource.values()[i-1].toString()+"   ");}
        System.out.println();
        Resource resource = Resource.values()[readInt(1,4)-1];
      return new ClientMessagePlaceResource(resource);
    }

    public ClientMessage chooseResource(Resource[] choice){
        System.out.println("Select which resource to convert a white marble to:\t[0]. "+choice[0]+"\t[1]. "+choice[1]);
        boolean position = readInt(0,1) == 1;

        return new ClientMessageChosenWhite(position);
    }


    /**
     * Create match ask number of players for starting game
     * @param s Player Id
     */
    public void createCustomMatch(String s) throws DisconnectedException {
        System.out.println("Number of players?");
        int numOfPlayers = readInt(1,4);

        ClientMessageCreateGame createMessage= new ClientMessageCreateGame(numOfPlayers,s);
        net.send(createMessage);
        ServerMessageView serverMessage= (ServerMessageView) net.receive();
        System.out.println(serverMessage.getView(true));



        //TODO: create MessageCreateGame
        //net.send (message)
        //TODO: entra nella funzione InGame che da ora pilota il client

    }

    public int joinMatch() {
        System.out.println("How many players?");
        return  readInt(1,4);
    }
}
