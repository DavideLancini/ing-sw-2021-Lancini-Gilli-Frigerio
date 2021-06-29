package it.polimi.ingsw.model;

import java.util.Arrays;
/**
 * Class Production
 * @author Group 12
 */
public class Production {
    protected Resource[] input;
    protected Resource[] output;
    private int faithOutput;

    /**
     * class constructor
     * @param input resources needed for production
     * @param output resources returned after production
     */
    public Production(Resource[] input, Resource[] output){
        this.input = input;
        this.output = output;
        this.faithOutput = 0;
    }

    /**
     * class constructor
     * @param input resources needed for production
     * @param output resources returned after production
     * @param faith amount of faith produced
     */
    public Production(Resource[] input, Resource[] output, int faith){
        this.input = input;
        this.output = output;
        this.faithOutput = faith;
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
     * @return faith
     */
    public int getFaith(){
        return this.faithOutput;
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

    /**
     * productionView
     * @return string to show
     */
    public String view() {
        String string = ("["+ResourceCounter.count(this.input)+"]->["+ResourceCounter.count(this.output)+
                (this.faithOutput != 0 ? this.faithOutput+""+Resource.FAITH : "") + "]");

        for(int i = 0; i<4-ResourceCounter.countTypes(this.input)-ResourceCounter.countTypes(this.output)-(faithOutput>0?1:0); i++){string = string.concat("   ");}

        string = string.concat("\n");
        return string;
    }
}



