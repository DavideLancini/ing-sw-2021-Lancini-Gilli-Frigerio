package it.polimi.ingsw.model.singlePlayer;

public class EndGameException extends Exception {
    private String message;
    public EndGameException(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }

}
