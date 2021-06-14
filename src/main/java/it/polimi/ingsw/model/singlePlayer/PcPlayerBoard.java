package it.polimi.ingsw.model.singlePlayer;


import it.polimi.ingsw.model.CardColor;
import it.polimi.ingsw.model.DevCardBoard;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.network.ConnectionInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.ServerMessageView;

import java.util.ArrayList;
import java.util.Arrays;

public class PcPlayerBoard {
    public ActionPile actionPile;
    private int darkFaith;
    private DevCardBoard board;

    public PcPlayerBoard(DevCardBoard board){
        this.darkFaith=0;
        this.actionPile= new ActionPile();
        this.board = board;
    }

    public String turn() throws EndGameException{
        ActionToken pcTurn=this.actionPile.getFirst();

        switch (pcTurn.getType()){
            case AddFaith:
                this.darkFaith += 2;
                if(this.darkFaith >= 24) throw new EndGameException(pcTurn.view()+darkFaithView());
                break;

            case ShufflePile:
                this.darkFaith += 1;
                if(this.darkFaith >= 24) throw new EndGameException(pcTurn.view()+darkFaithView());
                this.actionPile.setupActionPile();
                break;

            case RemoveDevCard:
                int i = 2;
                CardColor color = ((ActionRemove) pcTurn).getCardColor();

                Level level = Level.ONE;
                while(i>0){
                    if(this.board.getCard(color, level) == null) {
                        if(level == Level.ONE) level = Level.TWO;
                        else if(level == Level.TWO) level = Level.THREE;
                        else throw new EndGameException(pcTurn.view()+darkFaithView());
                    }
                    else{
                        try {
                            this.board.buy(level, color, new ArrayList<>(Arrays.asList(this.board.getCard(color, level).getCost())));
                        }
                        catch(Exception e){continue;}
                        i--;
                    }
                }

                break;

            default:
                throw new IllegalStateException("Unexpected value: " + pcTurn.getType());
        }

        return pcTurn.view()+darkFaithView();

    }


    public String darkFaithView(){
        return "DARK FAITH: \uD83D\uDD47\u001b[0m" + this.darkFaith;
    }

    public int getDarkFaith() {
        return this.darkFaith;
    }
}
