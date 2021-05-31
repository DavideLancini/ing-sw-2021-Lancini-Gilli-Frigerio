package it.polimi.ingsw.view.server;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.DevCard;
import it.polimi.ingsw.model.DevCardBoard;
import it.polimi.ingsw.model.Market;
import it.polimi.ingsw.model.PlayerBoard;

public class ShowAll {
    private PlayerBoard[] pbs;
    /**
     * Shows all table
     * @param game game id
     */
    public  ShowAll(Game game){

        DevCard[][] TopDev= DevCardBoard.getTop(DevCardBoard.getBoard());
        for(int i=0;i<pbs.length;i++){
            pbs[i].playerBoardView();
        }
        Market.marketView();
        DevCardBoard.topView(TopDev);
    }

    /**
     * shows only one player
     * @param pos selected player
     */
    public void ShowPlayerBoard(int pos){
        this.pbs[pos].playerBoardView();
    }

    /**
     * shows only market
     */
    public void ShowMarket(){
        Market.marketView();
    }

    /**
     * shows buyable only devCards
     */
    public void ShowDevTop(){
        DevCard[][] TopDev=DevCardBoard.getTop(DevCardBoard.getBoard());
        DevCardBoard.topView(TopDev);
    }
}
