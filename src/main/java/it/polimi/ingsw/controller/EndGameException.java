package it.polimi.ingsw.controller;

public class EndGameException extends Exception {
    private final String message;
    public EndGameException(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }

}
