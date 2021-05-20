package it.polimi.ingsw.model;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;


/**
 * Class StarGame
 * @author Gilli
 */
public class StartGame {
    /**
     * creates DevCardBoard
     * @throws FileNotFoundException if file.json in not found
     */
    public void createDevCardBoard() throws FileNotFoundException {
        DevCardDeck[][] board= new Gson().fromJson(new FileReader("src/main/java/it/polimi/ingsw/DevCardBOARD.json"),DevCardDeck[][].class);
        new DevCardBoard(board);
        DevCardBoard.topView(DevCardBoard.getTop(DevCardBoard.getBoard()));
    }
}
