package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.*;

public class ShowTable {
    private PlayerBoard[] pbs;


    /**
     * ShowTable constructor
     * @param game id of game to show
     */
    public ShowTable(Game game){
        this.pbs=game.getPbs();
    }

    /**
     * shows all table
     */
    public void ShowAll(){
        DevCard[][] TopDev=DevCardBoard.getTop(DevCardBoard.getBoard());
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
