package it.polimi.ingsw.model;

/**
 * Class LeaderSale extends LeaderCard
 *
 * @author Gruppo 12
 */
public class LeaderSale extends LeaderCard {
    private CardColor[] requirements;

    public LeaderSale(int vp, Resource type, CardColor[] requirements) {
        this.vp = vp;
        this.type = type;
        this.requirements = requirements;
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

    /**
     * LeaderCard view
     */
    public void leaderCardView() {
        System.out.println("══════════════╗");
        System.out.print("   [");
        for (CardColor each : requirements) {
            System.out.print(each + ";");
        }
        System.out.println("\b]");

        System.out.println("   -" + this.getType());
        System.out.println("  \u001b[38:5:221m {" + this.getVP() + "}\u001b[m");
        System.out.println("══════════════╝");
    }
}
