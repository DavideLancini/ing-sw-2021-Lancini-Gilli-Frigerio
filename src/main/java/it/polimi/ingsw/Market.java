package it.polimi.ingsw;

public class Market {

    private Marble sideMarble;
    private Marble[][] marketBoard = new Marble[4][3] ;
    /** Return marble out of the market board  */
    public Marble getSideMarble() {
        return sideMarble;
    }
    /** Set marble out of the market board*/
    public void setSideMarble(Marble sideMarble) {
        //TODO: a marble cannot be set to EMPTY
        this.sideMarble = sideMarble;
    }
    /** Show the current market board */
    public Marble[][] getMarketBoard() {
        return marketBoard;
    }
    /** set the starting market, use only on game start */
    public void setMarketBoard(Marble[][] marketBoard) {
        this.marketBoard = marketBoard;
    }
    /** take 3 or 4 marbles from current market and insert sideMarble in the
     * new market
     */
    public Marble[] takeResources(boolean isRow,int position){
        Marble[] taken=new Marble[4];
        Marble oldSideMarble=sideMarble;
        if(isRow){
            for (int i=0;i<4;i++){
                taken[i] = marketBoard[position][i];
                if(i==0)
                    setSideMarble(marketBoard[position][i]); //the first position drops and goes in sideMarble
                else
                    marketBoard[position][i-1]=marketBoard[position][i]; //left scrolling all Marbles in the row
            }
            marketBoard[position][3]=oldSideMarble;// insert the old sideMarble in the market
            return taken;
        }
        for (int i=0;i<3;i++){
            taken[i] = marketBoard[i][position];
            if(i==0)
                setSideMarble(marketBoard[i][position]); //the first position drops and goes in sideMarble
                else
                    marketBoard[i-1][position]=marketBoard[i][position]; //up scrolling all Marbles in the column
            }
        marketBoard[2][position]=oldSideMarble;
        return taken;
    }
}
