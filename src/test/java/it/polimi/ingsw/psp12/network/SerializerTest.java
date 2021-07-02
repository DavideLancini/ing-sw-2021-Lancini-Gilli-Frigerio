package it.polimi.ingsw.psp12.network;

import it.polimi.ingsw.psp12.network.messages.*;
import it.polimi.ingsw.psp12.network.components.Serializer;
import org.junit.Test;

import static org.junit.Assert.*;

public class SerializerTest {
    @Test
    public void testSerialize(){
        ClientMessageLeaderActivation message = new ClientMessageLeaderActivation(1);

        String serialized = Serializer.serialize(message);
        ClientMessage deserialized = Serializer.deserializeMessage(serialized);

        assertSame(message.position, ((ClientMessageLeaderActivation)deserialized).position);

        ClientMessageTakeResources message2 = new ClientMessageTakeResources(false, 2);
        serialized = Serializer.serialize(message2);
        deserialized = Serializer.deserializeMessage(serialized);

        assertSame(message2.position, ((ClientMessageTakeResources)deserialized).position);

        ServerMessageError message3 = new ServerMessageError("Test123");
        serialized = Serializer.serialize(message3);
        ServerMessage deserialized2 = Serializer.deserializeServerMessage(serialized);

        assertSame(true, message3.getError().equals(((ServerMessageError)deserialized2).getError()));

    }

}
