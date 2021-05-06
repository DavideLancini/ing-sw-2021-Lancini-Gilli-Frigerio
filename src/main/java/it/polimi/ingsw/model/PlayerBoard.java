package it.polimi.ingsw.model;

/**
 * Class PlayerBoard
 * @author Gruppo 12
 */
public class PlayerBoard {

    private int faithTrack;
    private Strongbox strongbox;
    private LeaderCard[] leaderCards;
    private Depot depot;
    //DevCard[colonna][livello]
    private DevCard[][] devCards;
    /**
     * class constructor
     * devCards and leaderCards set to empty and faith track to =
     */
    public PlayerBoard(){
        this.devCards = new DevCard[][]{{null,null,null},{null,null,null},{null,null,null}};

        this.leaderCards = new LeaderCard[]{null, null};

        this.faithTrack=0;
    }

    /**
     * addFaith
     * @param newFaith Faith to add
     * @return Return new faith for easy access
     */
    public int addFaith(int newFaith){
        this.faithTrack += newFaith;
        return this.faithTrack;
    }

    /**
     * getFaith
     * @return current Faith
     */
    public int getFaith(){
        return this.faithTrack;
    }

    /**
     * getLeaderCards
     * @param position left card = 0 right card = 1
     * @return selected leaderCard
     */
    public LeaderCard getLeaderCard (int position) {
        return this.leaderCards[position];
    }

    /**
     * getStrongbox
     * @return Player Strongbox
     */
    public Strongbox getStrongbox(){
        return this.strongbox;
    }

    /**
     * getDevCard
     * @param column selected slot 0 to 2 (3 available)
     * @param level level of devCard
     * @return devCard
     */
    public DevCard getDevCard(int column, int level){return this.devCards[column][level];}

    //TODO Exceptions and handling

    /**
     * addDevCard
     * @param newDevCard devCard bought from devCardDeck
     * @param position selected slot to place new devCard
     * @throws Exception if new devCard level is lower then current devCard in position slot
     */
    public void addDevCard(DevCard newDevCard, int position) throws Exception{
        Level level = newDevCard.getLevel();
        DevCard[] deck = this.devCards[position];
        //Check new DevCard is whitin rules or throws an exceptions
        switch(level){
            case ONE:
                if (deck[0] == null) {deck[0] = newDevCard; break;}
                else throw new Exception("");
            case TWO:
                if (deck[1] == null && deck[0].getLevel() == Level.ONE) {deck[1] = newDevCard; break;}
                else throw new Exception("");
            case THREE:
                if (deck[2] == null && deck[1].getLevel() == Level.TWO) {deck[2] = newDevCard; break;}
                else throw new Exception("");
        }

    }
}
