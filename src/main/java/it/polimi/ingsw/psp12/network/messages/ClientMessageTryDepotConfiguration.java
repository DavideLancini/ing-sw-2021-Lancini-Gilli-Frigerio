package it.polimi.ingsw.psp12.network.messages;

import it.polimi.ingsw.psp12.controller.Controller;
import it.polimi.ingsw.psp12.model.Resource;
import it.polimi.ingsw.psp12.network.DisconnectedException;

/**
 * Class ClientMessageTryDepotConfiguration
 * @author Group 12
 */
public class ClientMessageTryDepotConfiguration extends ClientMessage {
    private final Resource[] input = new Resource[6];
    private final int discardAmount;

    /**
     * constructor
     * @param input resources to deposit
     * @param discard number of resource not deposited
     */
    public ClientMessageTryDepotConfiguration(Resource[] input, int discard) {
        System.arraycopy(input, 0, this.input, 0, input.length);
        this.type = MessageType.TryDepotConfiguration;
        this.discardAmount = discard;
    }

    public boolean resolve(Controller controller) throws DisconnectedException {
        return controller.tryDepotConfiguration(this.input, discardAmount);
    }
}
