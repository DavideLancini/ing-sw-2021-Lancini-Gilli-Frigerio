package it.polimi.ingsw.view;

import javax.lang.model.type.NullType;
import java.io.IOException;
import java.util.logging.FileHandler;

public class Log extends Thread{
    public static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("MainLogger");

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
