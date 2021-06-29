package it.polimi.ingsw.model;
/**
 * Class depot
 * @author Group 12
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

    public Resource getLeaderType(int position){return this.leaderType[position];}


    /**
     * Class constructor
     * setting initial depot and leader depot to empty
     */
    public Depot(){
        this.contents = new Resource[]{Resource.EMPTY, Resource.EMPTY, Resource.EMPTY, Resource.EMPTY, Resource.EMPTY, Resource.EMPTY, Resource.EMPTY, Resource.EMPTY,Resource.EMPTY, Resource.EMPTY};

        this.leaderType = new Resource[]{Resource.EMPTY,Resource.EMPTY};
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
     * @throws Exception if resource is null, if position is out of depot size or if slot already in use
     */
    //TODO: Exception handling
    public void deposit(Resource resource, int position) throws Exception{
        if(position<0 || position>9 || resource == Resource.EMPTY || resource == null) throw new Exception("invalid position or null resource");
        if(this.contents[position] != Resource.EMPTY){throw new Exception("slot already occupied");}
        else if( ( (position == 6 || position == 7) && !resource.equals(this.leaderType[0])) || ( (position == 8 || position == 9) && !resource.equals(this.leaderType[1]))) throw new Exception("invalid resource type for leader depot");
        this.contents[position] = resource;
    }

    /**
     * Check for input length and valid resource types is performed by controller
     */

    public void setContents(Resource[] input){
        System.arraycopy(input, 0, this.contents, 0, input.length);
    }

    /**
     * extract
     * take resource from depot to start production
     * @param position position of resource needed
     * @return resource
     * @throws Exception if index is out of bounds
     */

    public Resource extract(int position) throws Exception{
        if(position < 0 || position > 9) throw new Exception("invalid position");
        Resource resource = this.contents[position];
        this.contents[position] = Resource.EMPTY;
        return resource;
    }

    /**
     * view for depot
     * @return string to show
     */
    public String depotView(){
        String string="";
        string=string.concat("   /╲\t\n");
        string=string.concat("  /"+contents[0]+" ╲\t\n");
        string=string.concat(" /"+contents[1]+" "+contents[2]+" ╲\n");
        string=string.concat("/"+contents[3]+" "+contents[4]+" "+contents[5]+" ╲\n");
        return string;
    }
}
