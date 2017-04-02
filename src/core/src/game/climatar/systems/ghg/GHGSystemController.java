package game.climatar.systems.ghg;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import game.climatar.map.Nation;

public class GHGSystemController {

    HashMap<Nation, GHGSystemModel> modelLink;
    HashMap<Nation, GHGSystemView> viewLink;

    private int initialValueFN = 50, initialValueEN = 35, initialValueWN = 25, initialValueAN = 10;

    public GHGSystemController(){
        modelLink = new HashMap<Nation, GHGSystemModel>();
        viewLink = new HashMap<Nation, GHGSystemView>();

        //Initialize Fire
        modelLink.put(Nation.FIRE, new GHGSystemModel(initialValueFN));
        viewLink.put(Nation.FIRE, new GHGSystemView());
        //Initialize Air
        modelLink.put(Nation.AIR, new GHGSystemModel(initialValueFN));
        viewLink.put(Nation.AIR, new GHGSystemView());
        //Initialize Water
        modelLink.put(Nation.WATER, new GHGSystemModel(initialValueWN));
        viewLink.put(Nation.WATER, new GHGSystemView());
        //Initialize Earth
        modelLink.put(Nation.EARTH, new GHGSystemModel(initialValueEN));
        viewLink.put(Nation.EARTH, new GHGSystemView());
}

}
