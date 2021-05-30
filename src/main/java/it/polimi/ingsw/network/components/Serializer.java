package it.polimi.ingsw.network.components;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.network.ClientMessage;
import it.polimi.ingsw.network.ServerMessage;

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


}
