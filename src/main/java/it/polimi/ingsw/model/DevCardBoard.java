package it.polimi.ingsw.model;

import it.polimi.ingsw.view.ViewHelper;

import java.util.*;

/**
 * Class DevCardBoard
 * @author Gruppo 12
 */
public class DevCardBoard {

    //First index x for color, second index y for level
    private  DevCardDeck[][] board;//X:0=BLUE 1=YELLOW 2=GREEN 3= PURPLE
                                  //Y:0=ONE 1=TWO 2=THREE

    /**
     * class constructor
     * @param board set initial decks of DevCards
     */
    public DevCardBoard(DevCardDeck[][] board){
        shuffleDecks(board);
        this.board = new DevCardDeck[][]{{null,null,null},
                                                 {null,null,null},
                                                 {null,null,null},
                                                 {null,null,null}};
        int i=0;

        for(DevCardDeck[] row : board){
            System.arraycopy(row, 0, this.board[i], 0, row.length);
            i++;
        }

    }

    public DevCardDeck[][] getBoard() {
        return this.board;
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

        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                top[i][j] = this.board[i][j].peek();
            }
        }

        return top;
    }

    public String topView() {

        String[] columns = new String[4];
        DevCard[][] top = this.getTop();
        for (int i = 0; i < 4; i++) {
            String string = "═════════════════╗\n";
            for (int j = 0; j < 3; j++) {

                if(top[i][j] != null)string=string.concat(top[i][j].devCardView());
                else string = string.concat(
                        "   \\ /\t\t\t\n" +
                        "    X\t\t\t\n" +
                        "   / \\\t\t\t\n"
                );


                if(j!=2)string=string.concat("----------------\n");
            }
            string=string.concat("═════════════════╝\n");
            columns[i] = string;
        }
        return ViewHelper.displayS2S(columns);
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

        return this.board[x][y].peek();
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

        ArrayList<Resource> costcopy = new ArrayList<>(cost);

        for(Resource elem : card.getCost()){if(!costcopy.remove(elem))throw new Exception("cost requirements not matched");}

        return this.board[color.ordinal()][level.ordinal()].draw();
    }

    private void shuffleDecks(DevCardDeck[][] board){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) Collections.shuffle(board[i][j].getDeck());
        }
    }

}



