package it.polimi.ingsw.psp12.network.components;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.psp12.model.DevCardBoard;
import it.polimi.ingsw.psp12.model.Market;
import it.polimi.ingsw.psp12.model.PlayerBoard;
import it.polimi.ingsw.psp12.network.messages.ClientMessage;
import it.polimi.ingsw.psp12.network.messages.ServerMessage;

/**
 * Class Serializer
 * Utility class to rapidly build and use gson
 * @author Group 12
 */
public class Serializer {
    /**
     * Serialize an object using gson library
     * @param o Object to be serialized
     * @return serialized object in String format
     */
    public static String serialize(Object o){
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(o);
    }

    /**
     * Deserialize instances of ClientMessage with custom Deserializer for abstract classes
     * @param s Object in json string format to be deserialized
     * @return ClientMessage Object deserialized
     */
    public static ClientMessage deserializeMessage(String s){
        Gson gson = new GsonBuilder().registerTypeAdapter(ClientMessage.class, new ClientMessageJsonDeserializer()).create();
        return gson.fromJson(s, ClientMessage.class);
    }

    /**
     * Deserialize instances of ClientMessage with custom Deserializer for abstract classes
     * @param s Object in json string format to be deserialized
     * @return ClientMessage Object deserialized
     */
    public static ServerMessage deserializeServerMessage(String s){
        Gson gson = new GsonBuilder().registerTypeAdapter(ServerMessage.class, new ServerMessageJsonDeserializer()).create();
        return gson.fromJson(s, ServerMessage.class);
    }

    /**
     * Deserialize instances of PlayerBoard
     * @param s Object in json string format to be deserialized
     * @return PlayerBoard Object deserialized
     */
    public static PlayerBoard deserializePB(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, PlayerBoard.class);
    }

    /**
     * Deserialize instances of Market
     * @param s Object in json string format to be deserialized
     * @return Market Object deserialized
     */
    public static Market deserializeMarket(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, Market.class);
    }

    /**
     * Deserialize instances of DevCardBoard
     * @param s Object in json string format to be deserialized
     * @return DevCardBoard Object deserialized
     */
    public static DevCardBoard deserializeDB(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, DevCardBoard.class);
    }
}
