package it.polimi.ingsw.model;

public class DefaultProduction extends Production{

    public DefaultProduction() {
        super(new Resource[]{Resource.EMPTY, Resource.EMPTY}, new Resource[]{Resource.EMPTY});
    }

    public void setInput(Resource a, Resource b){
        this.input = new Resource[]{a,b};
    }

    public void setInput(Resource a, int position){
        if(position == 0) {this.input = new Resource[]{a, this.input[1]};}
        else {this.input = new Resource[]{this.input[0], a};}
    }

    public void setOutput(Resource c){ this.output = new Resource[]{c};}

    public String view() {
        return "Default Production:\n  ["+this.input[0]+"]\n\t\t\t-> ["+this.output[0]+"]\n  ["+this.input[1]+"]\n";
    }
}
