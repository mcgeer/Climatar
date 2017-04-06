package game.climatar.systems.political;

import java.util.HashMap;
import game.climatar.map.Nation;

/**
 * Political System Model
 */
public class PoliticalSystemModel {
    //Economic stance
    private int wallet;
    //Relations with the people of
    private double relations;
    /**
     * Create a new Political System Model
     * @param n Nation being modeled
     * @param wallet Initial Net Worth
     */
    public PoliticalSystemModel(Nation n, int wallet){
        this.wallet = wallet;
        this.relations = 70.0;
    }

    /**
     * Return the average of relations for the nation with all others
     * @return total relations in the world summed against this nation
     */
    public Double Update(){
        return relations;
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
     * @param delta Change in relation with n
     */
    public void deltaRelation(Double delta){
        Double rel = relations + delta;
        //Limit the relation to be between 0 and 100 inclusive, it is a percent
        relations = (rel < 0)? 0 : (rel > 100) ? 100 : rel;
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
     * @return Return the relations within the country with the people
     */
    public Double getRelation(){
        return this.relations;
    }


}
