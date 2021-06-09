package it.polimi.ingsw.model.singlePlayer;


import it.polimi.ingsw.model.DevCardBoard;
import it.polimi.ingsw.network.ConnectionInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.ServerMessageView;

public class PcPlayerBoard {
    public ActionPile actionPile;
    private int darkFaith;

    public PcPlayerBoard(){
        darkFaith=0;
        actionPile= new ActionPile();
    }

    public boolean turn(DevCardBoard devCardBoard, ConnectionInterface net) {
        boolean endGame=false;
        ActionToken pcTurn=this.actionPile.getFirst();

        switch (pcTurn.getType()){
            case AddFaith:
                darkFaith=darkFaith+2;
                break;
            case ShufflePile:
                darkFaith=darkFaith+1;
                this.actionPile.shufflePile();
                break;
            case RemoveDevCard:
                endGame=pcTurn.resolve(devCardBoard);//card color to remove
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + pcTurn.getType());
        }
        try {
            net.send(new ServerMessageView(pcTurn.view()+DarkFaithView()));
        } catch (DisconnectedException e) {
            e.printStackTrace();
        }
        return endGame;
    }

    public String DarkFaithView(){
        return "DARKFAITH: \uD83D\uDD47\u001b[0m" + darkFaith;
    }

    public int getDarkFaith() {
        return darkFaith;
    }
}
