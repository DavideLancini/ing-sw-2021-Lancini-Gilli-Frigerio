package it.polimi.ingsw.view;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Log extends Thread{
    public static Logger logger = Logger.getLogger("MainLogger");

    public static void saveLog(){
        FileHandler file = null;
        try {
            file = new FileHandler("className.log");
        } catch (IOException error) {
            //Do Nothing
        }
        logger.addHandler(file);
    }

    public void run(){
        while(true){
            saveLog();
            try {
                sleep(2000);
            } catch (InterruptedException error) {
                saveLog();
                break;
            }
        }
    }
}

//Logger Levels
//Level.OFF     -> Nothing is logged
//Level.SEVERE  -> Fatal Error
//Level.WARNING -> Big Error
//Level.INFO    -> Important Messages that should always be in the console
//Level.CONFIG  -> Important Messages for debug
//Level.FINE    -> Recoverable Failure
//Level.FINER   -> Logging calls for entering, returning, or throwing an exception
//Level.FINEST  -> Highly detailed tracking
//Level.ALL     -> Everything is logged