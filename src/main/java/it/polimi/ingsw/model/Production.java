package it.polimi.ingsw.model;

import java.util.Arrays;
/**
 * Class Production
 * @author Gruppo 12
 */
public class Production {
    private Resource[] input;
    private Resource[] output;

    /**
     * class constructor
     * @param input resources needed for production
     * @param output resources returned after production
     */
    public Production(Resource[] input, Resource[] output){
        this.input = input;
        this.output = output;
    }

    /**
     * @return input
     */
    public Resource[] getInput(){
        return this.input;
    }

    /**
     * @return output
     */
    public Resource[] getOutput(){
        return this.output;
    }

    /**
     * produce
     * @param inResource resource needed for production
     * @return output
     * @throws Exception if inResource doesnt macth input of production
     */
    public Resource[] produce(Resource[] inResource) throws Exception{
        Resource[] a = Arrays.copyOf(this.input, this.input.length);
        Resource[] b = Arrays.copyOf(inResource, inResource.length);
        Arrays.sort(a);
        Arrays.sort(b);
        if(!Arrays.equals(a,b)) throw new Exception("");
        return this.output;
    }
}