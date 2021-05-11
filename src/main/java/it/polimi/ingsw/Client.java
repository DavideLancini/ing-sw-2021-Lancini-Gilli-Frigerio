package it.polimi.ingsw;

import static it.polimi.ingsw.view.cli.MainMenu.*;

public class Client {

    public static void main( String[] args ) {
        String action;
        boolean isON = true;
        while(isON){
            action = showMainMenu();
            switch (action){
                case "1":
                    //enter create match
                    break;
                case "2":
                    //enter join match
                    break;
                case "3":
                    //enter view open match
                    break;
                case "4":
                    //enter create custom rule set
                    break;
                case "5":
                    //enter settings
                    break;
                case "6":
                    //enter credits
                    break;
                case "7":
                    isON = false;
                    break;
                default:
                    // don't do anything and show again the main menu
                    break;
            }
        }
    }
}
