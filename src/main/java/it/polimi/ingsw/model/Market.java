package it.polimi.ingsw.model;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collections;

/**
 * Class Market
 * @author Gruppo 12
 */
public class Market {

    private static Marble sideMarble;
    private static Marble[][] marketBoard = new Marble[3][4] ;

    /**
     * Market constructor
     * @param marbles 13 marbles(4 white,2 blue,2 gray,2 yellow,2 purple,1 red)
     */
    public Market(Marble[] marbles) {
        Collections.shuffle(Arrays.asList(marbles));
       int k=0;
           for(int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                marketBoard[i][j]=marbles[k];
                k++;
            }
        }
       sideMarble=marbles[12];
    }

    /**
     * getSideMarble
      * @return Marble outside the current marketBoard
     */
    public static Marble getSideMarble() {
        return sideMarble;
    }

    /**
     * setSideMarble
     * @param  sideMarble selected marble type
     */
    public static void setSideMarble(Marble sideMarble) {
       Market.sideMarble = sideMarble;
    }

    /**
     * getMarketBoard
     * @return the current market board
     */
    public static Marble[][] getMarketBoard() {
        return marketBoard;
    }

    /**
     * setMarketBoard
     * set the starting market, use only on game start
     * @param inputMarketBoard initial marketBoard
     */
    public static void setMarketBoard(Marble[][] inputMarketBoard) {
        marketBoard = inputMarketBoard;
    }

    /**
     * takeResources
     * take 3 or 4 marbles from current market and insert sideMarble in the new market
     * @param isRow if true user selected a row
     * @param position selection of first,second,third row/column
     * @return 3(column)/4(row) marbles selected
     * @throws Exception if position is out of marketBroad (position<0 or >2 for row or >3 for column)
     */
    public static Marble[] takeResources(boolean isRow, int position) throws Exception{
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
     * CLI output
     */
    public static void marketView(){
        System.out.println(Marble.RESET+"market:");
        System.out.println("["+sideMarble+Marble.RESET+"]");
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                if (j<3)
                System.out.print(marketBoard[i][j]);
                else
                System.out.println(marketBoard[i][j]);
            }
        }
    }
}

