package it.polimi.ingsw;

public class Depot {
    private Resource row1;
    private Resource[] row2 = new Resource[2];
    private Resource[] row3 = new Resource[3];
    private Resource[] leadRow1 = new Resource[2];
    private Resource[] leadRow2 = new Resource[2];

    //TODO remove e deposit sono necessari? No: correggere UML; Sì: aggiungerli
    public void move(){
        //TODO move (position1: int[2], position: int[2]): void
    }

    public Resource getResource(int[2] position){
        //TODO
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
}
