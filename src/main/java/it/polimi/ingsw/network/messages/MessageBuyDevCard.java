package it.polimi.ingsw.network.messages;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.CardColor;
import it.polimi.ingsw.model.Level;
import it.polimi.ingsw.network.Message;

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

    public boolean resolve(Controller controller) {
        return controller.buyDevCard(this.level, this.color, this.column);
    }
}
