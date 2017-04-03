package game.climatar.systems.political;

import java.util.HashMap;
import game.climatar.map.Nation;

/**
 * Political System Model
 */
public class PoliticalSystemModel {
    //Nation who is modeled
    private Nation nation;
    //Economic stance
    private int wallet;
    //Relations with countries
    private HashMap<Nation, Double> relationsWith;

    /**
     * Create a new Political System Model
     * @param n Nation being modeled
     * @param wallet Initial Net Worth
     */
    public PoliticalSystemModel(Nation n, int wallet){
        relationsWith = new HashMap<Nation, Double>();
        this.wallet = wallet;
        nation = n;
        //All relations to n as neutral good (75%)
        for (Nation r: Nation.values()) {
            if(n.equals(r))
                continue;
            relationsWith.put(r, 75.0);
        }
    }

    /**
     * Return the average of relations for the nation with all others
     * @return total relations in the world summed against this nation
     */
    public Double Update(){
        Double ret = 0.0;
        for (Nation n: Nation.values()) {
            if(n.equals(nation))
                continue;
            ret += relationsWith.get(n);
        }

        return ret;
    }


    /**
     * Update the wallet of a nation
     * @param delta Change in money the country possesses
     */
    public void deltaWallet(int delta){
        wallet += delta;
    }

    /**
     * Update a relation to another Nation
     * @param n Relation being fetched
     * @param delta Change in relation with n
     */
    public void deltaRelation(Nation n, Double delta){
        if(n.equals(nation))
            throw new EnumConstantNotPresentException(Nation.class, "Relation between nations n and r, where n == r is invalid.");
        Double rel = relationsWith.get(n) + delta;
        //Limit the relation to be between 0 and 100 inclusive, it is a percent
        relationsWith.put(n, (rel < 0)? 0 : (rel > 100) ? 100 : rel );
    }


    /**
     * Get the nations wallet
     * @return nations economy
     */
    public int getWallet(){
        return wallet;
    }

    /**
     * Get the relations to another nation
     * @param n Nation in relation to this
     * @return
     */
    public Double getRelationWith(Nation n){
        if(n.equals(nation))
            throw new EnumConstantNotPresentException(Nation.class, "Relation between nations n and r, where n == r is invalid.");
        return relationsWith.get(n);
    }


}
