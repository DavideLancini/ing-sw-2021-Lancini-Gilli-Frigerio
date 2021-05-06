package it.polimi.ingsw.model;
/**
 * Class Market
 * @author Gruppo 12
 */
public class Market {

    private Marble sideMarble;
    private Marble[][] marketBoard = new Marble[3][4] ;

    /**
     * getSideMarble
      * @return Marble outside the current marketBoard
     */
    public Marble getSideMarble() {
        return sideMarble;
    }

    /**
     * setSideMarble
     * @param  sideMarble selected marble type
     */
    public void setSideMarble(Marble sideMarble) {
        this.sideMarble = sideMarble;
    }

    /**
     * getMarketBoard
     * @return the current market board
     */
    public Marble[][] getMarketBoard() {
        return this.marketBoard;
    }

    /**
     * setMarketBoard
     * set the starting market, use only on game start
     * @param inputMarketBoard initial marketBoard
     */
    public void setMarketBoard(Marble[][] inputMarketBoard) {
        this.marketBoard = inputMarketBoard;
    }

    /**
     * takeResources
     * take 3 or 4 marbles from current market and insert sideMarble in the new market
     * @param isRow if true user selected a row
     * @param position selection of first,second,third row/column
     * @return 3(column)/4(row) marbles selected
     * @throws Exception if position is out of marketBroad (position<0 or >2 for row or >3 for column)
     */
    public Marble[] takeResources(boolean isRow,int position) throws Exception{
        Marble[] takenRow=new Marble[4];
        Marble[] takenCol=new Marble[3];
        Marble oldSideMarble=this.sideMarble;
        position=position-1;

        if(isRow){
            if (position>2||position<0) throw new Exception("out of market");
            for (int i=0;i<4;i++){
                takenRow[i] = this.marketBoard[position][i];
                if(i==0)
                    this.sideMarble= this.marketBoard[position][i]; //the first position drops and goes in sideMarble
                else
                    this.marketBoard[position][i-1]=this.marketBoard[position][i]; //left scrolling all Marbles in the row
            }
            this.marketBoard[position][3]=oldSideMarble;// insert the old sideMarble in the market
            return takenRow;
        }
        else {
            if (position>3||position<0) throw new Exception("out of market");
            for (int i = 0; i < 3; i++) {
                takenCol[i] = this.marketBoard[i][position];
                if (i == 0)
                    this.sideMarble= this.marketBoard[i][position]; //the first position drops and goes in sideMarble
                else
                    this.marketBoard[i - 1][position] = this.marketBoard[i][position]; //up scrolling all Marbles in the column
            }
            this.marketBoard[2][position] = oldSideMarble;
            return takenCol;
        }
    }
}
