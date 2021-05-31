package it.polimi.ingsw.view.server;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.DevCard;
import it.polimi.ingsw.model.DevCardBoard;
import it.polimi.ingsw.model.Market;
import it.polimi.ingsw.model.PlayerBoard;

public class ShowAll {

    /**
     * Shows all table
     * @param game game id
     */
    public static void ShowAll(Game game){

        for(int i=0;i<game.getPlayers().length;i++){
            ShowPlayerBoard(i, game);
        }
        ShowMarket(game);
        ShowDevTop(game);
    }

    /**
     * shows only one player
     * @param pos selected player
     */
    public static void ShowPlayerBoard(int pos, Game game){
        game.getPlayers()[pos].playerBoard.playerBoardView();
    }

    /**
     * shows only market
     */
    public static void ShowMarket(Game game){
        game.market.marketView();
    }

    /**
     * shows buyable only devCards
     */
    public static void ShowDevTop(Game game){
        game.devCardBoard.topView();
    }
}
