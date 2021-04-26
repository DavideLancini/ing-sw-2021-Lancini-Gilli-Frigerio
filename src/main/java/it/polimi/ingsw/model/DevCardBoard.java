package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;

public class DevCardBoard {
    //First index x for color, second index y for level
    private DevCardDeck[][] board;

    public DevCard[][] getTop(){
        DevCard[][] top = {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
        };

        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                top[i][j] = board[i][j].peek();
            }
        }

        return top;
    }

    public DevCard getCard(CardColor color, Level level){
        int x = color.ordinal();
        int y = level.ordinal();

        return this.board[x][y].peek();
    }

    public DevCard buy(CardColor color, Level level, Collection<Resource> cost) throws Exception {

        DevCard card = this.getCard(color, level);
        ArrayList<Resource> costcopy = new ArrayList(cost);

        for(Resource elem : card.getCost()){if(!costcopy.remove(elem))throw new Exception("cost requirements not matched");}
        //if(!cost.containsAll(Arrays.asList(card.getCost()))) throw new Exception("cost requirements not matched");

        return this.board[color.ordinal()][level.ordinal()].draw();
    }


}



