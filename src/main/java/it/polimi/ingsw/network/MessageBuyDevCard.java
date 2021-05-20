package it.polimi.ingsw.network;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.CardColor;
import it.polimi.ingsw.model.Level;

public class MessageBuyDevCard extends Message {
    private Level level;
    private CardColor color;
    private int column;

    public MessageBuyDevCard(Level level, CardColor color, int column) {
        this.level = level;
        this.color = color;
        this.type = MessageType.BuyDevCard;
        this.column = column;
    }

    public void resolve(Controller controller) {
        controller.buyDevCard(this.level, this.color, this.column);
    }
}
