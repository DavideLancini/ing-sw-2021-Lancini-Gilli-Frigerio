package it.polimi.ingsw.model;

import java.util.Collection;
import java.util.Collections;

public class ResourceCounter {
    public ResourceCounter(Resource[] resources){
        int servant = 0;int shield = 0;int coin = 0;int stone = 0;int faith=0;
        for (Resource resource : resources) {
            if (resource == Resource.STONE)
                stone++;
            else if (resource == Resource.SERVANT)
                servant++;
            else if (resource == Resource.COIN)
                coin++;
            else if (resource == Resource.SHIELD)
                shield++;
            else if (resource == Resource.FAITH)
                faith++;
        }
        if (servant > 0) System.out.print(servant+""+Resource.SERVANT);
        if (coin > 0) System.out.print(coin+""+Resource.COIN);
        if (shield > 0) System.out.print(shield+""+Resource.SHIELD);
        if (stone> 0) System.out.print(stone+""+Resource.STONE);
        if (faith> 0) System.out.print(faith+""+Resource.FAITH);
    }
    public ResourceCounter(Collection<Resource> resources){
        int servant = 0;int shield = 0;int coin = 0;int stone = 0;int faith=0;
        for (Resource resource : resources) {
            if (resource == Resource.STONE)
                stone++;
            else if (resource == Resource.SERVANT)
                servant++;
            else if (resource == Resource.COIN)
                coin++;
            else if (resource == Resource.SHIELD)
                shield++;
            else if (resource == Resource.FAITH)
                faith++;
        }
        if (servant > 0) System.out.print(servant+""+Resource.SERVANT);
        if (coin > 0) System.out.print(coin+""+Resource.COIN);
        if (shield > 0) System.out.print(shield+""+Resource.SHIELD);
        if (stone> 0) System.out.print(stone+""+Resource.STONE);
        if (faith> 0) System.out.print(faith+""+Resource.FAITH);
    }
}
