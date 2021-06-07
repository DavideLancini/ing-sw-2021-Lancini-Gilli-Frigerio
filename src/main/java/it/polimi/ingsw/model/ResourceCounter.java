package it.polimi.ingsw.model;

import java.util.Collection;


public class ResourceCounter {

    public  static String count(Resource[] resources){
        String string="";
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
       return string;
    }

    public static String count1(Collection<Resource> resources){
        String string1="";
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
            string1 = string1.concat(servant + "" + Resource.SERVANT);
        }
        if (coin > 0) {
            string1 = string1.concat(coin + "" + Resource.COIN);
        }
        if (shield > 0) {
            string1 = string1.concat(shield + "" + Resource.SHIELD);
        }
        if (stone> 0) {
            string1 = string1.concat(stone + "" + Resource.STONE);
        }
        return string1;

    }
}
