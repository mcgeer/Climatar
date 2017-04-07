package game.climatar.systems.political;

import java.util.HashMap;

import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.map.Nation;

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
        ((PoliticalSystemModel) earthNationController.getModel()).init(Nation.EARTH, INITIAL_WALLET_EN);
        ((PoliticalSystemModel) fireNationController.getModel()).init(Nation.FIRE, INITIAL_WALLET_FN);
        ((PoliticalSystemModel) airNationController.getModel()).init(Nation.AIR, INITIAL_WALLET_AN);
        ((PoliticalSystemModel) waterNationController.getModel()).init(Nation.WATER, INITIAL_WALLET_WN);

        //Setup Map
        politicalSystems.put(Nation.FIRE, fireNationController);
        politicalSystems.put(Nation.EARTH, earthNationController);
        politicalSystems.put(Nation.AIR, airNationController);
        politicalSystems.put(Nation.WATER, waterNationController);
    }
 public PoliticalSystemController getEarthSubControl(){
		return earthNationController;
	}
	public PoliticalSystemController getFireSubControl(){
		return fireNationController;
	}
	public PoliticalSystemController getWaterSubControl(){
		return waterNationController;
	}
	public PoliticalSystemController getAirSubControl(){
		return airNationController;

	}

    @Override
    protected void layoutView() {

    }

    @Override
    protected void tick() {

    }

    public void setRelationDelta(Nation n, double delta){
        if(isActive())
            politicalSystems.get(n).setDeltaRelations(delta);
    }

    public void setWalletDelta(Nation n, int delta){
        if(isActive())
            politicalSystems.get(n).setDeltaWallet(delta);
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
