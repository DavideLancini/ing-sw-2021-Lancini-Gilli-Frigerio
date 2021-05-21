package it.polimi.ingsw.network.components;
/**
 * Types of Messages the Network is able to send
 * @author Lancini Davide
 */
public enum MessageType {
    PING, CONFIRM, LeaderActivation, TakeResources, BuyDevCard, Produce, SetResource, TryDepotConfiguration, SellLeader;
}
