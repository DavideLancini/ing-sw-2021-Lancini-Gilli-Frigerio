package it.polimi.ingsw.view.cli;

import it.polimi.ingsw.model.Reader;


/**
 * Main Menu Cli
 * @author Lancini Davide
 */
public class MainMenu {
    /**
     * Main Menu Cli
     * @author Lancini Davide
     * @return action selected
     */
    public static String showMainMenu(){
        System.out.println( "1. Create Match" );
        System.out.println( "2. Join Match" );
        System.out.println( "3. View Public Match" );
        System.out.println( "4. Create Custom Rule Set" );
        System.out.println( "5. Settings" );
        System.out.println( "6. Credits" );

        String action;
        action = Reader.in.nextLine();

        return action;
    }

}
