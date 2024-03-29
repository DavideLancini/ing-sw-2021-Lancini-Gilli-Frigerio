package it.polimi.ingsw.psp12.view;


import it.polimi.ingsw.psp12.model.*;
import it.polimi.ingsw.psp12.network.ClientNetInterface;
import it.polimi.ingsw.psp12.network.DisconnectedException;
import it.polimi.ingsw.psp12.network.messages.*;

import java.util.Collection;

/**
 * Class CLIActionManager
 * @author Group 12
 */
public class CLIActionManager extends Manager {
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

    /**
     * prints the view
     * @param view view
     */
    public void view(ServerMessageView view){System.out.println(view.getView(true));}


    public ClientNetInterface connect() throws DisconnectedException {
        String serverAddress;
        int serverPort;
        int localPort;

        System.out.println("serverAddress: ");
        serverAddress = Reader.in.nextLine();

        System.out.println("serverPort: ");
        serverPort = readInt();

        System.out.println("localPort: ");
        localPort = readInt();

        return new ClientNetInterface(serverAddress, serverPort, localPort);
 }

    @Override
    public void close() {
    }


    /**
     * display occurred error
     * @param error error description
     */
    public void displayError(String error){
        System.out.println("Server reports an error: "+ error);
        System.out.println("Press Enter to continue.");
        Reader.in.nextLine();
    }

    /**
     * Player select of to arrange the depot
     * @param resources resources to be arranged
     * @return new ClientMessageTryDepotConfiguration(newResources, resources.size())
     */
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


    /**
     * player selects to play online or local
     * @return true if player selected online
     */
    public boolean online(){
        System.out.println( "1. Online\n2. Local");
        return "1".equals(Reader.in.nextLine());
    }

    @Override
    public void waitForTurn() {
        System.out.println("Waiting for your turn...\n\n");
    }

    @Override
    public String showOfflineMenu() {
        String action;

        System.out.println("1. Offline Game");
        System.out.println("2. Credits");
        System.out.println("3. Exit");
        action = String.valueOf(readInt(1,3));

        return action;
    }

    @Override
    public void showCredits() {
        System.out.println("Created by:\nAndrea Gilli\nDavide Frigerio\nDavide Lancini\n\nBased on Masters of Renaissance by Cranio Games");
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
                            System.out.println("You already performed an action in this turn!");
                            break;
                        }

                        System.out.println("1. Select a row\n2. Select a column");
                        boolean isRow = readInt(1,2) == 1;
                        System.out.println("Enter number of "+(isRow ? "row" : "column")+":");
                        int position = isRow? readInt(1,3) : readInt(1,4);
                        return new ClientMessageTakeResources(isRow, position);

                    case 2: // BUY DEVCARD

                        if(mainActionDone){
                            System.out.println("You already performed an action in this turn!");
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

                        System.out.println("Choose in which column to place this card: ");
                        int column = readInt(1,3)-1;
                        return new ClientMessageBuyDevCard(level, color, column);



                    case 3: //PRODUCE

                        if(mainActionDone){
                            System.out.println("You already performed an action in this turn!");
                            break;
                        }

                        boolean[] activated = new boolean[6];
                        String[] text = {
                                "Enter 1 to produce with default production, enter 0 to skip it",
                                "Enter 1 to produce with Development Card in first column, enter 0 to skip it",
                                "Enter 1 to produce with Development Card in second column, enter 0 to skip it",
                                "Enter 1 to produce with Development Card in third column, enter 0 to skip it",
                                "Enter 1 to produce with first Leader Card, enter 0 to skip it",
                                "Enter 1 to produce with second Leader Card, enter 0 to skip it"
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

    /**
     * Player selects 2 leader card
     * @param message ServerMessage containing 4 leader cards
     * @return ClientMessageChosenLeaders(first selected,second selected)
     */
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
    public String[] showOnlineMenu() {
        String action;
        String playerID;
        System.out.println("Player ID:");
        playerID = Reader.in.nextLine();
        if(playerID.equals(""))playerID = "player"+(int)Math.floor(Math.random()*1000000);

        System.out.println("1. Join Game");
        System.out.println("2. Credits");
        System.out.println("3. Exit");
        action = String.valueOf(readInt(1,3));


        return new String[]{action, playerID};
    }

    /**
     * player selects 1 resource to deposit
     * @return ClientMessagePlaceResource(selected resource)
     */
    public ClientMessage addResource(){
        System.out.print("Select your starting resource: ");
        for (int i = 1; i <= Resource.values().length -2; i++){System.out.print(""+i+". "+Resource.values()[i-1].toString()+"      ");}
        System.out.println();
        Resource resource = Resource.values()[readInt(1,4)-1];
      return new ClientMessagePlaceResource(resource);
    }

    /**
     * player selects which active LeaderTransform to use(if 2 are active at same time)
     * @param choice first and second LeaderTransform resource type
     * @return ClientMessageChosenWhite(selected leaderTransform)
     */
    public ClientMessage chooseResource(Resource[] choice){
        System.out.println("Select which resource to convert a white marble to:\t[0]. "+choice[0]+"\t[1]. "+choice[1]);
        boolean position = readInt(0,1) == 1;

        return new ClientMessageChosenWhite(position);
    }

    /**
     * player selects the number of players in the game to join
     * @return number of players
     */
    public int joinMatch() {
        System.out.println("Enter the number of players for this game (Max 4 Players):");
        return  readInt(1,4);
    }
}
