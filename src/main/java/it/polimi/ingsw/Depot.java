package it.polimi.ingsw;

import it.polimi.ingsw.model.Resource;

import java.util.Arrays;

public class Depot {
    private Resource[] contents;
    private Resource[] leaderType;

    public Resource getResource(int position){
        return this.contents[position];
    }

    public Resource[] getLeaderType(){return this.leaderType;}

    public void activateLeader(Resource type){
        if(this.leaderType[0] == null){this.leaderType[0] = type;}
        else if(this.leaderType[1] == null){this.leaderType[1] = type;}
    }

    //TODO: Exception handling
    public void deposit(Resource resource, int position) throws Exception{
        if(position<0 || position>9 || resource == null) throw new Exception("invalid position or null resource");
        if(this.contents[position] != null){throw new Exception("slot already occupied");}
        else if( ( (position == 6 || position == 7) && !resource.equals(this.leaderType[0])) || ( (position == 8 || position == 9) && !resource.equals(this.leaderType[1]))) throw new Exception("invalid resource type");
        this.contents[position] = resource;
    }

    //Check for input length and valid resource types is perfomed by controller
    public void setContents(Resource[] input){
        System.arraycopy(input, 0, contents, 0, 10);
    }

    public Resource extract(int position){
        Resource resource = this.contents[position];
        this.contents[position] = null;
        return resource;
    }
}
