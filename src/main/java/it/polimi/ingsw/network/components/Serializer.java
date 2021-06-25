package it.polimi.ingsw.network.components;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.model.DevCardBoard;
import it.polimi.ingsw.model.Market;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.network.messages.ClientMessage;
import it.polimi.ingsw.network.messages.ServerMessage;

public class Serializer {
    public static String serialize(Object o){
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(o);
    }

    public static ClientMessage deserializeMessage(String s){
        Gson gson = new GsonBuilder().registerTypeAdapter(ClientMessage.class, new ClientMessageJsonDeserializer()).create();
        return gson.fromJson(s, ClientMessage.class);
    }

    public static ServerMessage deserializeServerMessage(String s){
        Gson gson = new GsonBuilder().registerTypeAdapter(ServerMessage.class, new ServerMessageJsonDeserializer()).create();
        return gson.fromJson(s, ServerMessage.class);
    }


    public static PlayerBoard deserializePB(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, PlayerBoard.class);
    }

    public static Market deserializeMarket(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, Market.class);
    }

    public static DevCardBoard deserializeDB(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, DevCardBoard.class);
    }
}
