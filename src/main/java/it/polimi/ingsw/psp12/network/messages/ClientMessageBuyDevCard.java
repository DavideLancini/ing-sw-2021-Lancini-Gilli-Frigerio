package it.polimi.ingsw.psp12.network.messages;

import it.polimi.ingsw.psp12.controller.Controller;
import it.polimi.ingsw.psp12.model.CardColor;
import it.polimi.ingsw.psp12.model.Level;
import it.polimi.ingsw.psp12.network.DisconnectedException;
/**
 * Class ClientMessageBuyDevCard
 * @author Group 12
 */
public class ClientMessageBuyDevCard extends ClientMessage {
    private final Level level;
    private final CardColor color;
    private final int column;

    /**
     * constructor
     * @param level level of devCard
     * @param color color of devCard
     * @param column playerBoard slot to put devCard
     */
    public ClientMessageBuyDevCard(Level level, CardColor color, int column) {
        this.level = level;
        this.color = color;
        this.type = MessageType.BuyDevCard;
        this.column = column;
    }

    public boolean resolve(Controller controller) throws DisconnectedException {
        return controller.buyDevCard(this.level, this.color, this.column);
    }
}
