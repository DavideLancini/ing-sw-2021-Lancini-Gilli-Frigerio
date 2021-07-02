package it.polimi.ingsw.psp12.model;

/**
 * Class efaultProduction
 * @author Group 12
 */
public class DefaultProduction extends Production{
    /**
     * constructor
     */
    public DefaultProduction() {
        super(new Resource[]{Resource.EMPTY, Resource.EMPTY}, new Resource[]{Resource.EMPTY});
    }

    public void setInput(Resource a, Resource b){
        this.input = new Resource[]{a,b};
    }

    /**
     * set one input resource
     * @param a chosen resource
     * @param position =0 or 1 for input[position] to set
     */
    public void setInput(Resource a, int position){
        if(position == 0) {this.input = new Resource[]{a, this.input[1]};}
        else {this.input = new Resource[]{this.input[0], a};}
    }

    /**
     * set output
     * @param c resource wanted as output
     */
    public void setOutput(Resource c){ this.output = new Resource[]{c};}

    public String view() {
        return "Default Production:\n  ["+this.input[0]+"]\n\t\t\t-> ["+this.output[0]+"]\n  ["+this.input[1]+"]\n";
    }
}
