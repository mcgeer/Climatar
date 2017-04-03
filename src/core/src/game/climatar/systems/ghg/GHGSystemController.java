package game.climatar.systems.ghg;

import java.util.HashMap;
import game.climatar.map.Nation;

public class GHGSystemController {

    //Refs to models and views
    private HashMap<Nation, GHGSystemModel> modelLink;
    private HashMap<Nation, GHGSystemView> viewLink;

    private int initialValueFN = 50, initialValueEN = 35, initialValueWN = 25, initialValueAN = 10;

    /**
     * Generate a new GHGSystem Controller for each Nation
     */
    public GHGSystemController(){
        modelLink = new HashMap<Nation, GHGSystemModel>();
        viewLink = new HashMap<Nation, GHGSystemView>();

        //Initialize Fire
        modelLink.put(Nation.FIRE, new GHGSystemModel(initialValueFN));
        viewLink.put(Nation.FIRE, new GHGSystemView());
        //Initialize Air
        modelLink.put(Nation.AIR, new GHGSystemModel(initialValueAN));
        viewLink.put(Nation.AIR, new GHGSystemView());
        //Initialize Water
        modelLink.put(Nation.WATER, new GHGSystemModel(initialValueWN));
        viewLink.put(Nation.WATER, new GHGSystemView());
        //Initialize Earth
        modelLink.put(Nation.EARTH, new GHGSystemModel(initialValueEN));
        viewLink.put(Nation.EARTH, new GHGSystemView());
    }

    /**
     * Update the emissions in the system
     * @return Total pollution being added to the world
     */
    public int Update(){
        int ret = 0;

        //Update all Nations GHG Levels
        for (Nation n: Nation.values()) {
            ret += modelLink.get(n).Update();
            //TODO Update View Labels
        }

        return  ret;
    }

    /**
     * Set a change to be in effect on next update for a nations emissions
     * @param n Nation who is consequently having a change in emissions
     * @param deltaEmissions Change being applied int in [-10, 10] - {0}
     */
    public void setDeltaEmissions(Nation n, int deltaEmissions){
        modelLink.get(n).updateGHGPerUpdate(deltaEmissions);
    }

    public int GetGHG(Nation n){
        return modelLink.get(n).Update();
    }


}
