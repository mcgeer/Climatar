package game.climatar.systems.ghg;

import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.systems.ghg.GHGSystemModel.GHGProperty;

/**
 * GHG controller which controls the controllers for the air/earth/water/fire
 * nations
 */

public class GHGController extends Controller {
	// constants
	private static final int INITIAL_VALUE_FN = 50;
	private static final int INITIAL_VALUE_EN = 35;
	private static final int INITIAL_VALUE_WN = 25;
	private static final int INITIAL_VALUE_AN = 10;

	// child controllers
	GHGSystemController earthNationController;
	GHGSystemController fireNationController;
	GHGSystemController waterNationController;
	GHGSystemController airNationController;
	
	@Override
	protected void initialize() {
		((GHGSystemModel) earthNationController.getModel()).init(INITIAL_VALUE_EN);
		((GHGSystemModel) fireNationController.getModel()).init(INITIAL_VALUE_FN);
		((GHGSystemModel) airNationController.getModel()).init(INITIAL_VALUE_AN);
		((GHGSystemModel) waterNationController.getModel()).init(INITIAL_VALUE_WN);
	}
	
	@Override
	protected void layoutView() {
		
	}

	@Override
	protected void tick() {
		// set the model's total emissions (by all nations)
		int totalEmissionsPerUpdate = 0;
		
		totalEmissionsPerUpdate += earthNationController.getEmissionPerUpdate();
		totalEmissionsPerUpdate += fireNationController.getEmissionPerUpdate();
		totalEmissionsPerUpdate += airNationController.getEmissionPerUpdate();
		totalEmissionsPerUpdate += waterNationController.getEmissionPerUpdate();
	}

	/**
	 *
	 * @return
	 */
	public int getEmissionsPerUpdate(){
		return (int) getModel().get(GHGProperty.TOTAL_EMISSIONS_PER_UPDATE.id());
	}


}
