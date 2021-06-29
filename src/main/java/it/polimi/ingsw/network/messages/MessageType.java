package it.polimi.ingsw.network.messages;
/**
 * Types of Messages the Network is able to send
 * @author Group 12
 */
public enum MessageType {
    LeaderActivation, TakeResources, BuyDevCard, Produce, SetResource, TryDepotConfiguration, SellLeader, EndTurn, Error, OK, Turn, MarketReturn, View, LocalPort, CreateGame, JoinGame, ChooseLeaders, ChosenLeaders, AddResource, TwoMarbleLeaders, GameOver, ChosenWhite, PlaceResource
}
