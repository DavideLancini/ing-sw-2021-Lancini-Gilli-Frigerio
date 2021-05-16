package it.polimi.ingsw.network;

import org.junit.Test;

import static org.junit.Assert.*;

public class SerializerTest {
    @Test
    public void testSerialize(){
        Serializer serializer = new Serializer();
        MessageLeaderActivation message = new MessageLeaderActivation(1);

        String serialized = Serializer.serialize(message);
        Message deserialized = (Message) Serializer.deserialize(serialized);

        assertSame(message.position, ((MessageLeaderActivation)deserialized).position);

        MessageTakeResources message2 = new MessageTakeResources(false, 2);
        serialized = Serializer.serialize(message2);
        deserialized = (Message) Serializer.deserialize(serialized);

        assertSame(message2.position, ((MessageTakeResources)deserialized).position);



    }

}
