package it.polimi.ingsw.network.messages;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import it.polimi.ingsw.network.ServerMessage;

import java.lang.reflect.Type;

public class ServerMessageJsonDeserializer implements JsonDeserializer<ServerMessage> {
    public ServerMessage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context){
        String classtype = "it.polimi.ingsw.network.ServerMessage" + json.getAsJsonObject().get("type").getAsString();

        try{
            return context.deserialize(json, Class.forName(classtype));
        }
        catch (ClassNotFoundException e) {throw new RuntimeException(e);}
    }
}
