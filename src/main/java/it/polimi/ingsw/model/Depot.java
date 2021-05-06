package it.polimi.ingsw.model;
/**
 * Class depot
 * @author Gruppo 12
 */
public class Depot {
    private Resource[] contents;
    private Resource[] leaderType;
    /**
     * getResource
     * @param position selected depot position resource to show
     * @return resource in selected depot position
     */
    public Resource getResource(int position){
        return this.contents[position];
    }

    /**
     * getLeaderType
     * @return resources in LeaderDepot
     */
    public Resource[] getLeaderType(){return this.leaderType;}
    /**
     * Class constructor
     * setting initial depot and leader depot to empty
     */
    public Depot(){
        this.contents = new Resource[]{null, null, null, null, null, null, null, null, null, null};

        this.leaderType = new Resource[]{null ,null};
    }
    /**
     * activateLeader
     * Enables leader depot
     * @param type Resource type of LeaderCardDepot
     */
    public void activateLeader(Resource type){
        if(this.leaderType[0] == null){this.leaderType[0] = type;}
        else if(this.leaderType[1] == null){this.leaderType[1] = type;}
    }
    /**
     * deposit
     * Deposit Resource taken form Market
     * @param resource resource to deposit
     * @param position selected slot to place resource into Depot. 0<=position<6 standard depot, 6<=position<10 leadercards depot.
     * @throws Exception if resource is null, if position is out of depot size or if slot alreadyin use
     */
    //TODO: Exception handling
    public void deposit(Resource resource, int position) throws Exception{
        if(position<0 || position>9 || resource == null) throw new Exception("invalid position or null resource");
        if(this.contents[position] != null){throw new Exception("slot already occupied");}
        else if( ( (position == 6 || position == 7) && !resource.equals(this.leaderType[0])) || ( (position == 8 || position == 9) && !resource.equals(this.leaderType[1]))) throw new Exception("invalid resource type");
        this.contents[position] = resource;
    }
    /**
     */
    //Check for input length and valid resource types is perfomed by controller
    public void setContents(Resource[] input){
        System.arraycopy(input, 0, contents, 0, 10);
    }

    /**
     * extract
     * take resource from depot to start production
     * @param position position of resource needed
     * @return resource
     */

    public Resource extract(int position){
        Resource resource = this.contents[position];
        this.contents[position] = null;
        return resource;
    }
}
