package it.polimi.ingsw.model;

import java.util.Collection;
import java.util.Collections;

public class ResourceCounter {
    private Resource[] resources;



    private String string;

    public ResourceCounter(Resource[] resources){
        this.resources=resources;
        string="";
        int servant = 0;int shield = 0;int coin = 0;int stone = 0;
        for (Resource resource : resources) {
            if (resource == Resource.STONE)
                stone++;
            else if (resource == Resource.SERVANT)
                servant++;
            else if (resource == Resource.COIN)
                coin++;
            else if (resource == Resource.SHIELD)
                shield++;
        }
        if (servant > 0) {
            string = string.concat(servant + "" + Resource.SERVANT);
        }
        if (coin > 0) {
            string = string.concat(coin + "" + Resource.COIN);
        }
        if (shield > 0) {
            string = string.concat(shield + "" + Resource.SHIELD);
        }
        if (stone> 0) {
            string = string.concat(stone + "" + Resource.STONE);
        }

    }
    public ResourceCounter(Collection<Resource> resources){
        string="";
        int servant = 0;int shield = 0;int coin = 0;int stone = 0;
        for (Resource resource : resources) {
            if (resource == Resource.STONE)
                stone++;
            else if (resource == Resource.SERVANT)
                servant++;
            else if (resource == Resource.COIN)
                coin++;
            else if (resource == Resource.SHIELD)
                shield++;
        }
        if (servant > 0) {
            System.out.print(servant + "" + Resource.SERVANT);
            string = string.concat(servant + "" + Resource.SERVANT);
        }
        if (coin > 0) {
            System.out.print(coin + "" + Resource.COIN);
            string = string.concat(coin + "" + Resource.COIN);
        }
        if (shield > 0) {
            System.out.print(shield + "" + Resource.SHIELD);
            string = string.concat(shield + "" + Resource.SHIELD);
        }
        if (stone> 0) {
            System.out.print(stone + "" + Resource.STONE);
            string = string.concat(stone + "" + Resource.STONE);
        }

    }
    public void count() {
        int servant = 0;
        int shield = 0;
        int coin = 0;
        int stone = 0;
        for (Resource resource : this.resources) {
            if (resource == Resource.STONE)
                stone++;
            else if (resource == Resource.SERVANT)
                servant++;
            else if (resource == Resource.COIN)
                coin++;
            else if (resource == Resource.SHIELD)
                shield++;
        }
        if (servant > 0) {
            System.out.print(servant + "" + Resource.SERVANT);
            string = string.concat(servant + "" + Resource.SERVANT);
        }
        if (coin > 0) {
            System.out.print(coin + "" + Resource.COIN);
            string = string.concat(coin + "" + Resource.COIN);
        }
        if (shield > 0) {
            System.out.print(shield + "" + Resource.SHIELD);
            string = string.concat(shield + "" + Resource.SHIELD);
        }
        if (stone > 0) {
            System.out.print(stone + "" + Resource.STONE);
            string = string.concat(stone + "" + Resource.STONE);
        }
    }
    public String getString() {
        return string;
    }
}
