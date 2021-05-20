package it.polimi.ingsw.model;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;



/**
 * Class StarGame
 * @author Gilli
 */
public class StartGame {
    /**
     * StartGame constructor
     */
    public StartGame(/*players*/) throws FileNotFoundException {
        createDevCardBoard();
        createLeaderDeck();
        createMarket();
       /* switch (players.length){
            case 1://startSinglePlayer
            case 2://give leaderCards LeaderCardDeck.drawTwo()
                    //give p2 1 resource
            case 3://give leaderCards LeaderCardDeck.drawTwo()
                    //give p2 1 resource
                    //give p3 1 resource and addFaith
            case 4://give leaderCards LeaderCardDeck.drawTwo()
                    //give p2 1 resource
                    //give p3 1 resource and addFaith
                    //give p4 2 resource and addFaith
            }
        */

    }
    /**
     * creates DevCardBoard
     * @throws FileNotFoundException if file.json is not found
     */
    private void createDevCardBoard() throws FileNotFoundException {
        DevCardDeck[][] board= new Gson().fromJson(new FileReader("src/main/resources/DevCardBOARD.json"),DevCardDeck[][].class);
        new DevCardBoard(board);
        //DevCardBoard.topView(DevCardBoard.getTop(DevCardBoard.getBoard()));
    }

    /**
     * creates LeaderCardDeck
     * @throws FileNotFoundException if files.json is not found
     */
    private void createLeaderDeck() throws FileNotFoundException {
        LeaderTransform[] leaderTransformDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/TransformCard.json"),LeaderTransform[].class);
        LeaderProduction[] leaderProductionDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/ProductionCard.json"),LeaderProduction[].class);
        LeaderSale[] leaderSaleDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/SaleCard.json"), LeaderSale[].class);
        LeaderDepot[] leaderDepotDeck=new Gson().fromJson(new FileReader("src/main/resources/LeaderCards/DepotCard.json"),LeaderDepot[].class);
        LeaderCard[] deck= new LeaderCard[16];
        System.arraycopy(leaderTransformDeck,0,deck,0,leaderTransformDeck.length);
        System.arraycopy(leaderProductionDeck,0,deck,4,leaderProductionDeck.length);
        System.arraycopy(leaderSaleDeck,0,deck,8,leaderSaleDeck.length);
        System.arraycopy(leaderDepotDeck,0,deck,12,leaderDepotDeck.length);
        new LeaderCardDeck(deck);
        // for (LeaderCard leaderCard : deck){leaderCard.leaderCardView();}
    }

    private void createMarket() throws FileNotFoundException {
        Marble[] marbles= new Gson().fromJson(new FileReader("src/main/resources/Marbles.json"),Marble[].class);
        new Market(marbles);
        //Market.marketView();
    }

}
