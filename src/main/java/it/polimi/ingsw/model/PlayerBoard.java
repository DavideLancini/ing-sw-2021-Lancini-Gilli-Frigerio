package it.polimi.ingsw.model;

/**
 * Class PlayerBoard
 * @author Gruppo 12
 */
public class PlayerBoard {

    private int faithTrack;
    private Strongbox strongbox = new Strongbox();
    private LeaderCard[] leaderCards;
    private Depot depot= new Depot();
    //DevCard[colonna][livello]
    private DevCard[][] devCards;
    private DefaultProduction defaultProduction;

    /**
     * class constructor
     * devCards and leaderCards set to empty and faith track to 0
     */
    public PlayerBoard(){

        this.devCards = new DevCard[][]{{null,null,null},{null,null,null},{null,null,null}};

        this.leaderCards = new LeaderCard[]{null, null};

        this.faithTrack=0;

        this.defaultProduction = new DefaultProduction();
    }

    public PlayerBoard(LeaderCard[] leaders){
        this();
        System.arraycopy(leaders, 0, this.leaderCards, 0, 2);

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
     * getDefaultProduction
     * @return default Production
     */
    public DefaultProduction getDefaultProduction(){
        return this.defaultProduction;
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
     * Sell card for 1 faith
     * @param position 0 or 1
     */
    public void sellLeader (int position) {
        this.leaderCards[position] = null;
        addFaith(1);
    }


    /**
     * getStrongbox
     * @return Player Strongbox
     */
    public Strongbox getStrongbox(){
        return this.strongbox;
    }

    /**
     * getDepot
     * @return Player Depot
     */
    public Depot getDepot(){
        return this.depot;
    }

    /**
     * getDevCard
     * @param column selected slot 0 to 2 (3 available)
     * @param level level of devCard
     * @return devCard
     */
    public DevCard getDevCard(int column, int level){return this.devCards[column][level];}

    /**
     * Default to highest level card present
     * @param column selected slot 0 to 2 (3 available)
     * @return devCard
     */
    public DevCard getDevCard(int column){
        return this.devCards[column][2] != null ? this.devCards[column][2] : this.devCards[column][1] != null ? this.devCards[column][1] : this.devCards[column][0];
    }


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

    public void playerBoardView(){
        System.out.println("player");
        ShowFaithTrack();
        this.depot.depotView();
        this.strongbox.StrongboxView();
        devCardsView();

    }

    private void devCardsView() {
        boolean first=false;
        for(int i=0;i<3;i++){
            for(int j=2;j>-1;j--){
                if (first)
                devCards[i][j].coveredView();
                else if (devCards[i][j]!=null) {
                    devCards[i][j].toString();
                    first=true;
                    }
            }
            first=false;
            }
    }


    private void ShowFaithTrack() {
        System.out.println(faithTrack+""+Resource.FAITH);
    }

    public void setLeaders(LeaderCard[] leaderCards) {
        this.leaderCards = leaderCards;
    }
}
