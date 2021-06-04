package it.polimi.ingsw.model;

import java.util.Arrays;
/**
 * Class Production
 * @author Gruppo 12
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
     */
    public void productionView(){
        System.out.print("[");
        new ResourceCounter(this.input).count();
        System.out.print("]→[");
        new ResourceCounter(this.output).count();
        if(this.faithOutput!=0)
        System.out.print(this.faithOutput +""+Resource.FAITH);
        System.out.println("]");
    }

    public String view() {
        String string="";
        string=string.concat("[");
        ResourceCounter input=new ResourceCounter(this.input);
        string=string.concat(input.getString());
        string=string.concat("]→[");
        ResourceCounter output=new ResourceCounter(this.output);
        string=string.concat(output.getString());
        if(this.faithOutput!=0)
           string=string.concat(this.faithOutput +""+Resource.FAITH);
        string=string.concat("]");
        return string;
    }
}



