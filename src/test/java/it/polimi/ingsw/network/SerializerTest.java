package it.polimi.ingsw.network;

import it.polimi.ingsw.network.messages.ClientMessageLeaderActivation;
import it.polimi.ingsw.network.messages.ClientMessageTakeResources;
import it.polimi.ingsw.network.components.Serializer;
import it.polimi.ingsw.network.messages.ServerMessageError;
import org.junit.Test;

import static org.junit.Assert.*;

public class SerializerTest {
    @Test
    public void testSerialize(){
        Serializer serializer = new Serializer();
        ClientMessageLeaderActivation message = new ClientMessageLeaderActivation(1);

        String serialized = Serializer.serialize(message);
        ClientMessage deserialized = (ClientMessage) Serializer.deserializeMessage(serialized);

        assertSame(message.position, ((ClientMessageLeaderActivation)deserialized).position);

        ClientMessageTakeResources message2 = new ClientMessageTakeResources(false, 2);
        serialized = Serializer.serialize(message2);
        deserialized = (ClientMessage) Serializer.deserializeMessage(serialized);

        assertSame(message2.position, ((ClientMessageTakeResources)deserialized).position);

        ServerMessageError message3 = new ServerMessageError("Test123");
        serialized = Serializer.serialize(message3);
        ServerMessage deserialized2 = (ServerMessage) Serializer.deserializeServerMessage(serialized);

        assertSame(true, message3.getError().equals(((ServerMessageError)deserialized2).getError()));

    }

}
