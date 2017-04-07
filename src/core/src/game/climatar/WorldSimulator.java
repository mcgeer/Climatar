package game.climatar;

import java.util.List;

import com.badlogic.gdx.Gdx;

import game.climatar.GameState.WorldProperty;
import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.map.MapView;
import game.climatar.map.Nation;
import game.climatar.news.ConseqType;
import game.climatar.news.NewsEventControl;
import game.climatar.systems.ghg.GHGController;
import game.climatar.systems.political.PoliticalController;
import game.climatar.systems.weather.WeatherController;

@SetModel(GameState.class)
public class WorldSimulator extends Controller {

	// Actively logged SubSystems
	private boolean ghgIsActive, weatherIsActive, politicalIsActive, isPaused, eventGen;

	// Sub-system State Controllers
	private GHGController ghgSystems;
	private WeatherController weatherSystems;
	private PoliticalController politicalSystems;

	// News Events
		private NewsEventControl newsController;
		private List <ConseqType> currentAE;
		private List <ConseqType> currentPE;
		

	private MapView mapView;
	
	/**
	 * Start a new game, Controlling all aspects of the world, Call Simulate
	 * after Creation!
	 * 
	 * @param monitoringGHG
	 *            Is the GHG system being monitored
	 * @param monitoringWeather
	 *            Is the Weather system being monitored
	 * @param monitoringPolitical
	 *            Is the Political system being monitored
	 */
	public void newGame(Nation player) {
		// Set up what systems are being used
		ghgIsActive = true;
		weatherIsActive = true;
		politicalIsActive = true;

		// Set up the Game State
		((GameState) getModel()).init(player);
	
		newsController= new NewsEventControl();
		
	}

	/**
	 * Update Function to handle all requests to step the state of the world
	 */
	public void Simulate() {
		// GENERATE NEWS
		// REACT TO NEWS
		if(isPaused==false){
		newsController.getActiveEvent();
		pauseGame();
		}
		
		// UPDATE Sub Systems
		// if(ghgIsActive)
		// gameState.updateWorldGHG(ghgSystems.getEmissionsPerUpdate());
		// TODO if(weatherIsActive)
		// TODO weatherSystems.Update();
		// if(politicalIsActive)
		// gameState.updateWorldPlayerPolitics(politicalSystems.getTotalRelations());
		

	}

	private void pauseGame() {
		// TODO Auto-generated method stub
		isPaused=true;
	}
	public void resumeGame(){
		isPaused=false;
	}

	// ========================================================
	// =============== Set Activated Systems ==================

	/**
	 * Enable/Disable GHG Sub System
	 * 
	 * @param ghgIsActive
	 *            Enable/Disable
	 */
	public void setGhgIsActive(boolean ghgIsActive) {
		this.ghgIsActive = ghgIsActive;
	}

	/**
	 * Enable/Disable Weather Sub System
	 * 
	 * @param weatherIsActive
	 *            Enable/Disable
	 */
	public void setWeatherIsActive(boolean weatherIsActive) {
		this.weatherIsActive = weatherIsActive;
	}

	/**
	 * Enable/Disable Political Sub System
	 * 
	 * @param politicalIsActive
	 *            Enable/Disable
	 */
	public void setPoliticalIsActive(boolean politicalIsActive) {
		this.politicalIsActive = politicalIsActive;
	}

	// ========================================================
	// =================---- Overrides ----====================
	@Override
	protected void layoutView() {
		float PAD = 50;
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		mapView.setFrame(PAD, PAD, width - PAD * 2, height - PAD * 2);

		showView(mapView);
	}

	@Override
	protected void tick() {
		// UPDATE WORLD STATE
		Simulate();
		if(isPaused==false){
		// TODO add conds for GS updates
			
		getModel().set(WorldProperty.TOTAL_GHG.id(), ghgSystems.getEmissionsPerUpdate());
		getModel().set(WorldProperty.AVG_RELATIONS.id(), politicalSystems.getTotalRelations());
		}
		else{
		//DO NOTHING	
		}
		// getModel().set(WorldProperty.AVG_TEMP.id(), )
	}

	@Override
	protected void nextTick() {
		if (isPlaying()) {
			super.nextTick();
		}
	}

	public boolean isPlaying() {
		return getModel().get(WorldProperty.PLAYING.id()) != null;
	}
}
