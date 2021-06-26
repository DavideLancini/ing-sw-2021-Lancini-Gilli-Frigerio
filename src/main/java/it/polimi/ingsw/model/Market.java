package it.polimi.ingsw.model;

import java.util.Arrays;
import java.util.Collections;

/**
 * Class Market
 * @author Gruppo 12
 */
public class Market {

    private Marble sideMarble;
    private Marble[][] marketBoard = new Marble[3][4] ;


    /**
     * Default market constructor
     */
    public Market() {
        this(new Marble[]{Marble.BLUE, Marble.BLUE, Marble.WHITE, Marble.WHITE, Marble.WHITE,
                Marble.WHITE, Marble.GRAY, Marble.GRAY, Marble.YELLOW, Marble.YELLOW, Marble.PURPLE, Marble.PURPLE, Marble.RED});
    }


    /**
     * Market constructor
     * @param marbles 13 marbles(4 white,2 blue,2 gray,2 yellow,2 purple,1 red)
     */
    public Market(Marble[] marbles) {
        Collections.shuffle(Arrays.asList(marbles));
       int k=0;
           for(int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                this.marketBoard[i][j]=marbles[k];
                k++;
            }
        }
       this.sideMarble=marbles[12];
    }

    /**
     * getSideMarble
      * @return Marble outside the current marketBoard
     */
    public Marble getSideMarble() {
        return this.sideMarble;
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
    public Marble[] takeResources(boolean isRow, int position) throws Exception{
        Marble[] takenRow=new Marble[4];
        Marble[] takenCol=new Marble[3];
        Marble oldSideMarble= sideMarble;
        position=position-1;

        if(isRow){
            if (position>2||position<0) throw new Exception("out of market");
            for (int i=0;i<4;i++){
                takenRow[i] = marketBoard[position][i];
                if(i==0)
                    sideMarble= marketBoard[position][i]; //the first position drops and goes in sideMarble
                else
                    marketBoard[position][i-1]= marketBoard[position][i]; //left scrolling all Marbles in the row
            }
            marketBoard[position][3]=oldSideMarble;// insert the old sideMarble in the market
            return takenRow;
        }
        else {
            if (position>3||position<0) throw new Exception("out of market");
            for (int i = 0; i < 3; i++) {
                takenCol[i] = marketBoard[i][position];
                if (i == 0)
                    sideMarble= marketBoard[i][position]; //the first position drops and goes in sideMarble
                else
                    marketBoard[i - 1][position] = marketBoard[i][position]; //up scrolling all Marbles in the column
            }
            marketBoard[2][position] = oldSideMarble;
            return takenCol;
        }
    }

    /**
     * marketView
     *  CLI output
     * @return String to show
     */
    public String view(){
        String string = "";
        string = string.concat(Marble.RESET+"Market:\n");
        string = string.concat("["+this.sideMarble+Marble.RESET+"]\n");
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 4; j++) {
                string = string.concat(this.marketBoard[i][j].toString());
            }
            string = string.concat("\n");
        }

        return string;
    }

}

