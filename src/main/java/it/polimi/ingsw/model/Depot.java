package it.polimi.ingsw.model;

public class Depot {
    private Resource contents[];

    public Resource getResource(int position){
        return contents[position];
    }

    public boolean setResource(Resource inputResources[]){
        if(checkValidity(inputResources)){
            this.contents = inputResources;
            return true;
        }
        else{
            return false;
        }
    }

    private void activateLeadRow1(){
        //TODO
    }
    private void activateLeadRow2(){
        //TODO
    }
    public void activateNextLeadRow(){
        //TODO
    }

    private boolean checkValidity(Resource inputResources[]){
        //todo
        return true;
    }
}
