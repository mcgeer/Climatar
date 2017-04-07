package game.climatar.systems.ghg;

import com.badlogic.gdx.Gdx;

import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.systems.ghg.GHGSystemModel.GHGProperty;

@SetModel(GHGSystemModel.class)
public class GHGSystemController extends Controller {
	
	private GHGSystemView ghgSystemView;
	
	@Override
	protected void initialize() {
	}
	
	@Override
	protected void layoutView() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		ghgSystemView.setFrame(10.0f, width/2 - (width/2) * 1/5, width/2, height/2 );

	}

	public void show(){
		showView(ghgSystemView);
	}

	public void hide(){
		showView();
	}
	@Override
	protected void tick() {
		System.out.println("TICK");
		// Update the EmissionsPerUpdate value based on DeltaEmissions
		int emissionsPerUpdate = (Integer) getModel().get(GHGProperty.EMISSIONS_PER_UPDATE.id());
		int deltaEmissions = (Integer) getModel().get(GHGProperty.DELTA_EMISSIONS.id());
		
		emissionsPerUpdate += deltaEmissions;
		
		getModel().set(GHGProperty.EMISSIONS_PER_UPDATE.id(), emissionsPerUpdate);
	}
	
    /**
     * Set a change to be in effect on next update for a nations emissions
     * @param deltaEmissions Change being applied int in [-10, 10] - {0}
     */
    public void setDeltaEmissions(int deltaEmissions){
    	getModel().set(GHGProperty.DELTA_EMISSIONS.id(), deltaEmissions);
    }

	public int getEmissionPerUpdate() {
		return (Integer) getModel().get(GHGProperty.EMISSIONS_PER_UPDATE.id());
	}

}
