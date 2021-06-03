package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.CardColor;
import it.polimi.ingsw.model.Level;

public class ClientMessageBuyDevCard extends ClientMessage {
    private Level level;
    private CardColor color;
    private int column;

    public ClientMessageBuyDevCard(Level level, CardColor color, int column) {
        this.level = level;
        this.color = color;
        this.type = MessageType.BuyDevCard;
        this.column = column;
    }

    public boolean resolve(Controller controller) {
        return controller.buyDevCard(this.level, this.color, this.column);
    }
}
