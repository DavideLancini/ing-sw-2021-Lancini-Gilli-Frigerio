package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.CardColor;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.network.DisconnectedException;
/**
 * Class ClientMessageBuyDevCard
 * @author Group 12
 */
public class ClientMessageBuyDevCard extends ClientMessage {
    private Level level;
    private CardColor color;
    private int column;

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
