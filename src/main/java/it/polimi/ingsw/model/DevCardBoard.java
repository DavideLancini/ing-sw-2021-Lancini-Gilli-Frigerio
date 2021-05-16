package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;
/**
 * Class DevCardBoard
 * @author Gruppo 12
 */
public class DevCardBoard {
    //First index x for color, second index y for level
    private DevCardDeck[][] board;


    /**
     * class constructor
     * @param board set initial decks of DevCards
     */
    public DevCardBoard(DevCardDeck[][] board){

        this.board = new DevCardDeck[][]{{null,null,null},{null,null,null},{null,null,null},{null,null,null}};
        int i=0;

        for(DevCardDeck[] row : board){
            System.arraycopy(row, 0, this.board[i], 0, row.length);
            i++;
        }

    }

    /**
     * getTop
     * @return view of 12 DevCards you could buy
     */
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

    public static void topView(DevCard[][] top) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                top[i][j].devCardView();
                System.out.print("\u001b[13B ");
            }

        }
    }

    /**
     * getCard
     * @param color color of DevCard
     * @param level level of DevCard
     * @return show select card on top
     */
    public DevCard getCard(CardColor color, Level level){
        int x = color.ordinal();
        int y = level.ordinal();

        return board[x][y].peek();
    }

    /**
     * buy
     * @param color color of DevCard
     * @param level level of DevCard
     * @param cost resource given to pay DevCard
     * @return DevCard bought
     * @throws Exception resource given not matching cost
     */
    public DevCard buy(Level level, CardColor color, Collection<Resource> cost) throws Exception {

        DevCard card = this.getCard(color, level);
        ArrayList<Resource> costcopy = new ArrayList(cost);

        for(Resource elem : card.getCost()){if(!costcopy.remove(elem))throw new Exception("cost requirements not matched");}
        //if(!cost.containsAll(Arrays.asList(card.getCost()))) throw new Exception("cost requirements not matched");

        return board[color.ordinal()][level.ordinal()].draw();
    }


}



