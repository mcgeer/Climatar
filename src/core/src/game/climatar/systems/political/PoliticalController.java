package game.climatar.systems.political;

import java.util.HashMap;
import java.util.Map;

import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.map.Nation;
import game.climatar.systems.ghg.GHGSystemController;

/**
 * Created by riley_000 on 2017-04-05.
 */

@SetModel(PoliticalSystemModel.class)
public class PoliticalController extends Controller {

    // Constants
    private static final int INITIAL_WALLET_FN = 5000;
    private static final int INITIAL_WALLET_EN = 8000;
    private static final int INITIAL_WALLET_WN = 1000;
    private static final int INITIAL_WALLET_AN = 1000;


    PoliticalSystemController earthNationController;
    PoliticalSystemController fireNationController;
    PoliticalSystemController waterNationController;
    PoliticalSystemController airNationController;


    private HashMap<Nation, PoliticalSystemController> politicalSystems = new HashMap<Nation, PoliticalSystemController>();

    @Override
    protected void initialize() {
        //Setup Controllers
        ((PoliticalSystemModel) earthNationController.getModel()).init(INITIAL_WALLET_EN);
        ((PoliticalSystemModel) fireNationController.getModel()).init(INITIAL_WALLET_FN);
        ((PoliticalSystemModel) airNationController.getModel()).init(INITIAL_WALLET_AN);
        ((PoliticalSystemModel) waterNationController.getModel()).init(INITIAL_WALLET_WN);

        //Setup Map
        politicalSystems.put(Nation.FIRE, fireNationController);
        politicalSystems.put(Nation.EARTH, earthNationController);
        politicalSystems.put(Nation.AIR, airNationController);
        politicalSystems.put(Nation.WATER, waterNationController);

    }

    @Override
    protected void layoutView() {

    }

    @Override
    protected void tick() {

    }

    /**
     * @param n Nation being fetched
     * @return Controller for Nation n
     */
    public PoliticalSystemController getPoliticalSystemController(Nation n){
        return politicalSystems.get(n);
    }

    /**
     * Average World Local reputations
     * @return AVG local reputations
     */
    public double getTotalRelations(){
        double totalEmissionsPerUpdate = 0;

        totalEmissionsPerUpdate += earthNationController.getRelations();
        totalEmissionsPerUpdate += fireNationController.getRelations();
        totalEmissionsPerUpdate += airNationController.getRelations();
        totalEmissionsPerUpdate += waterNationController.getRelations();

        return totalEmissionsPerUpdate * 0.25;
    }

}
