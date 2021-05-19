package it.polimi.ingsw.network;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serializer {
    public static String serialize(Object o){
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(o);
    }

    public static Message deserializeMessage(String s){
        Gson gson = new GsonBuilder().registerTypeAdapter(Message.class, new MessageJsonDeserializer()).create();
        return gson.fromJson(s, Message.class);
    }

    public static ServerMessage deserializeServerMessage(String s){
        Gson gson = new GsonBuilder().registerTypeAdapter(ServerMessage.class, new ServerMessageJsonDeserializer()).create();
        return gson.fromJson(s, ServerMessage.class);
    }


}
