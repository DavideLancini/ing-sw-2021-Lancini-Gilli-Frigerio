package it.polimi.ingsw.psp12.network.components;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import it.polimi.ingsw.psp12.network.messages.ClientMessage;

import java.lang.reflect.Type;

/**
 * Class ClientMessageJsonDeserializer
 * custom gson deserializer for subclasses of abstract class ClientMessage
 * @author Group 12
 */
public class ClientMessageJsonDeserializer implements JsonDeserializer<ClientMessage> {
    public ClientMessage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context){
        String classtype = "it.polimi.ingsw.psp12.network.messages.ClientMessage" + json.getAsJsonObject().get("type").getAsString();

        try{
            return context.deserialize(json, Class.forName(classtype));
        }
        catch (ClassNotFoundException e) {throw new RuntimeException(e);}
    }
}
