package it.polimi.ingsw.network;

import it.polimi.ingsw.network.messages.MessageLeaderActivation;
import it.polimi.ingsw.network.messages.MessageTakeResources;
import it.polimi.ingsw.network.components.Serializer;
import it.polimi.ingsw.network.messages.ServerMessageError;
import org.junit.Test;

import static org.junit.Assert.*;

public class SerializerTest {
    @Test
    public void testSerialize(){
        Serializer serializer = new Serializer();
        MessageLeaderActivation message = new MessageLeaderActivation(1);

        String serialized = Serializer.serialize(message);
        Message deserialized = (Message) Serializer.deserializeMessage(serialized);

        assertSame(message.position, ((MessageLeaderActivation)deserialized).position);

        MessageTakeResources message2 = new MessageTakeResources(false, 2);
        serialized = Serializer.serialize(message2);
        deserialized = (Message) Serializer.deserializeMessage(serialized);

        assertSame(message2.position, ((MessageTakeResources)deserialized).position);

        ServerMessageError message3 = new ServerMessageError("Test123");
        serialized = Serializer.serialize(message3);
        ServerMessage deserialized2 = (ServerMessage) Serializer.deserializeServerMessage(serialized);

        assertSame(true, message3.getError().equals(((ServerMessageError)deserialized2).getError()));

    }

}
