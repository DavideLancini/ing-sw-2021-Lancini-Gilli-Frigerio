package it.polimi.ingsw.psp12.model;

/**
 * Class LeaderSale extends LeaderCard
 *
 * @author Group 12
 */
public class LeaderSale extends LeaderCard {
    private final CardColor[] requirements;
    /**
     * constructor
     * @param vp victory points
     * @param type resource type that can be discounted
     * @param requirements requirements to activate
     * @param imagePath path of associated imagine
     */
    public LeaderSale(int vp, Resource type, CardColor[] requirements,String imagePath) {
        this.vp = vp;
        this.type = type;
        this.requirements = requirements;
        this.imagePath=imagePath;
    }

    /**
     * @return array of requirements
     */
    public CardColor[] getRequirements() {
        return this.requirements;
    }


    /**
     * DownPrice
     * remove 1 resource based on leader type from any devCard cost
     *
     * @param cost devCard cost
     */
    public Resource[] downPrice(Resource[] cost) {
        int i = 0, j = 0;
        boolean done = false;
        Resource[] reducedcost = new Resource[cost.length -1];

        //remove 1 resource based on type
        while (i < cost.length) {
            if (cost[i].equals(getType()) && !done) {
                done = true;
                i++;
                continue;
            }
            reducedcost[j] = cost[i];
            j++;
            i++;
        }
        return reducedcost;
    }

    public String view(){
        String string = "";
        string = string.concat("══════════════╗\n");
        string = string.concat("   [");
        for (CardColor each : requirements) {
            string = string.concat(each + ";");
        }
        string = string.concat("\b]   \n");

        string = string.concat("   -" + this.getType()+"       \n");
        string = string.concat("  \u001b[38:5:221m {" + this.getVP() + "}\u001b[m       \n");
        string = string.concat("══════════════╝\n");
        return string;
    }



}
