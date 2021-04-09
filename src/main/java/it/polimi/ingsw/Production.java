package it.polimi.ingsw;

import java.util.Arrays;

public class Production {
    private Resource[] input;
    private Resource[] output;

    public Production(Resource[] input, Resource[] output){
        this.input = input;
        this.output = output;
    }

    public Resource[] getInput(){
        return this.input;
    }

    public Resource[] getOutput(){
        return this.output;
    }


    public Resource[] produce(Resource[] inResource) throws Exception{
        Resource[] a = Arrays.copyOf(this.input, this.input.length);
        Resource[] b = Arrays.copyOf(inResource, inResource.length);
        Arrays.sort(a);
        Arrays.sort(b);
        if(!Arrays.equals(a,b)) throw new Exception("");
        return this.output;
    }
}