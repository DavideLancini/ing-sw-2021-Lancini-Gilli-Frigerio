package it.polimi.ingsw.model;

import javax.swing.*;

/**
 * Class LeaderCard
 * @author Gruppo 12
 */
public class LeaderCard {


    protected int vp;
    protected boolean isActive = false;
    protected Resource type;
    protected String imagePath;

    /**
     * getVP
     *
     * @return victory points
     */
    public int getVP() {
        return this.vp;
    }


    /**
     * getType
     *
     * @return resource LeaderCard works with
     */
    public Resource getType() {
        return this.type;
    }

    /**
     * getIsActive
     *
     * @return isActive status
     */
    public boolean getIsActive() {
        return this.isActive;
    }

    /**
     * toggleActive
     * modify isActive status
     */
    public void toggleActive() {
        this.isActive = !this.isActive;
    }


    /**
     * LeaderCard view
     * overridden in each subclass
     * @return string to show
     */
    public String view(){return null;}

    public  ImageIcon getImage(){
        ImageIcon card =new ImageIcon(imagePath);
        return card;
    };
}







