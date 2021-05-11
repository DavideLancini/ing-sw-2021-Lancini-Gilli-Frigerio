package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.Market;

public class MainMenu {

    public void showMenu(){
        System.out.println( "1. Create Match" );
        System.out.println( "2. Join Match" );
        System.out.println( "3. View Public Match" );
        System.out.println( "4. Create Custom Rule Set" );
        System.out.println( "5. Settings" );
        System.out.println( "6. Credits" );
    }

    public void showGame(){
        Market.marketView();
    }
}
