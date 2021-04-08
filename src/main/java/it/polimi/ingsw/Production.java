package it.polimi.ingsw;

import java.util.Arrays;

public class Production {
    private Resource[] input;
    private Resource[] output;

    public Resource[] getInputProduction(){
        return this.input;
    }

    public Resource[] getOutputProduction(){
        return this.output;
    }


    public Resource[] produce(Resource[] inResource) throws Exception{
        Arrays.sort(this.input);
        Arrays.sort(inResource);
        if(!Arrays.equals(this.input, inResource)) throw new Exception("");
        return this.output;
    }
}