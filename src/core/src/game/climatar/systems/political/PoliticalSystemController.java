package game.climatar.systems.political;

import java.util.HashMap;
import game.climatar.map.Nation;

/**
 * Control all political systems
 */
public class PoliticalSystemController {

    //Refs to models and views
    private HashMap<Nation, PoliticalSystemModel> modelLink;
    private HashMap<Nation, PoliticalSystemView> viewLink;

    /**
     * New Political controller for all nations
     */
    public PoliticalSystemController(){
        modelLink = new HashMap<Nation, PoliticalSystemModel>();
        viewLink = new HashMap<Nation, PoliticalSystemView>();

        //Fire Nation
        modelLink.put(Nation.FIRE, new PoliticalSystemModel(Nation.FIRE, 5000));
        viewLink.put(Nation.FIRE, new PoliticalSystemView());

        //Air Nation
        modelLink.put(Nation.AIR, new PoliticalSystemModel(Nation.AIR, 1000));
        viewLink.put(Nation.AIR, new PoliticalSystemView());

        //Water Nation
        modelLink.put(Nation.WATER, new PoliticalSystemModel(Nation.WATER, 1000));
        viewLink.put(Nation.WATER, new PoliticalSystemView());

        //Earth Nation
        modelLink.put(Nation.EARTH, new PoliticalSystemModel(Nation.EARTH, 8000));
        viewLink.put(Nation.EARTH, new PoliticalSystemView());
    }

    /**
     * Get total relation from n
     * @param n Nation being updated
     * @return Total relation [0, 300]
     */
    public double update(Nation n){
        return modelLink.get(n).Update();
    }

    /**
     * Change wallet of n by delta
     * @param n Nation under effect
     * @param delta Change in Economic stance
     */
    public void deltaWallet(Nation n, int delta){
        modelLink.get(n).deltaWallet(delta);
    }

    /**
     * Update relations between n1 and n2 to change by delta
     * @param n1 Nation one whose relation is changing
     * @param n2 Nation two whose relation is changing
     * @param delta Change in relation
     */
    public void updateRelations(Nation n1, Nation n2, double delta){
        if(n1.equals(n2))
            return;
        modelLink.get(n1).deltaRelation(n2, delta);
        modelLink.get(n2).deltaRelation(n1, delta);
    }


}
