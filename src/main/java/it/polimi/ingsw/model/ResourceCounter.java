package it.polimi.ingsw.model;

import java.util.Collection;

/**
 * Class ResourceCounter
 */
public class ResourceCounter {
    /**
     * count number of resource type in "resources"
     * @param resources resources to count
     * @return string to show
     */
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
    /**
     * count number of resource type in "resources"
     * @param resources resources to count
     * @return string to show
     */
    public static String count(Collection<Resource> resources){
        return count(resources.toArray(new Resource[]{}));
    }

    /**
     * count number of resource type in "resources"
     * @param resources resources to count
     * @return number of resource types
     */
    public static int countTypes(Resource[] resources){
        int number = 0;
        int servant = 0, shield = 0, coin = 0, stone = 0;
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
            number++;
        }
        if (coin > 0) {
            number++;
        }
        if (shield > 0) {
            number++;
        }
        if (stone> 0) {
            number++;
        }
        return number;
    }
    /**
     * count number of resource type in "resources"
     * @param resources resources to count
     * @return number of resource types
     */
    public static int countTypes(Collection<Resource> resources){
        return countTypes(resources.toArray(Resource[]::new));
    }

}
