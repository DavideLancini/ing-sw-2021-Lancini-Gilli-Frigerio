package it.polimi.ingsw;

import it.polimi.ingsw.view.Log;
import it.polimi.ingsw.view.ServerView;

import java.util.logging.Level;

/**
 * Server App
 *
 * @author Group 12
 */
public class Server {
    public static void main( String[] args ){
        //Logger Setup
        Log.logger.setLevel(Level.ALL);
        //Loading parameters from the properties file or standard par. if FNF
        ServerView.loadParameters();
        //Main Cycle of the Server Interface
        boolean isON = true;

        //TURN SERVER ON RIGHT AWAY
        ServerView.toggleServer();

        while(isON){
            //Asking for a command
            switch (ServerView.serverMenu()) {
                case "1":
                    //Switch server ON/OFF
                    ServerView.toggleServer();
                    break;
                case "2":
                    //Edit server parameters, some parameters require a reboot (localport)
                    ServerView.editParameters();
                    break;
                case "3":
                    //Shutdown WITHOUT save
                    isON = false;
                    System.exit(0);
                    break;
                default:
                    //Invalid Command: just re-prompt the Menu
                    break;
            }
        }
    }
}