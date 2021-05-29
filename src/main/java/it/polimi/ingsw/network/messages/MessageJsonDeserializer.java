package it.polimi.ingsw.network.messages;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import it.polimi.ingsw.network.Message;

import java.lang.reflect.Type;

public class MessageJsonDeserializer implements JsonDeserializer<Message> {
    public Message deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context){
        String classtype = "it.polimi.ingsw.network.Message" + json.getAsJsonObject().get("type").getAsString();

        try{
            return context.deserialize(json, Class.forName(classtype));
        }
        catch (ClassNotFoundException e) {throw new RuntimeException(e);}
    }
}
