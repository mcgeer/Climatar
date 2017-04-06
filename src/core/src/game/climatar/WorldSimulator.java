package game.climatar;

import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.map.Nation;
import game.climatar.news.NewsEventControl;
import game.climatar.systems.ghg.GHGController;
import game.climatar.systems.political.PoliticalController;
import game.climatar.systems.weather.WeatherController;
import game.climatar.GameState.WorldProperty;

@SetModel(GameState.class)
public class WorldSimulator extends Controller{

    //Actively logged SubSystems
    private boolean ghgIsActive, weatherIsActive, politicalIsActive;

    //Sub-system State Controllers
    private GHGController ghgSystems;
    private WeatherController weatherSystems;
    private PoliticalController politicalSystems;

    //News Events
    private NewsEventControl newsController;

    //GameStateTEMP
    private GameState gameState;

    /**
     * Create a World Simulator, Controlling all aspects of the world, Call Simulate after Creation!
     * @param monitoringGHG Is the GHG system being monitored
     * @param monitoringWeather Is the Weather system being monitored
     * @param monitoringPolitical Is the Political system being monitored
     */
    public WorldSimulator(Nation player, boolean monitoringGHG, boolean monitoringWeather, boolean monitoringPolitical){
        //Set up what systems are being used
        ghgIsActive = monitoringGHG;
        weatherIsActive = monitoringWeather;
        politicalIsActive = monitoringPolitical;

        //Set up the Game State
        gameState.init(player);

    }

    /**
     * Update Function to handle all requests to step the state of the world
     */
    public void Simulate(){
        //GENERATE NEWS
        //REACT TO NEWS

        //UPDATE Sub Systems
        //if(ghgIsActive)
        //    gameState.updateWorldGHG(ghgSystems.getEmissionsPerUpdate());
        //TODO if(weatherIsActive)
        //TODO    weatherSystems.Update();
        //if(politicalIsActive)
        //    gameState.updateWorldPlayerPolitics(politicalSystems.getTotalRelations());



    }

    //========================================================
    //=============== Set Activated Systems ==================

    /**
     * Enable/Disable GHG Sub System
     * @param ghgIsActive Enable/Disable
     */
    public void setGhgIsActive(boolean ghgIsActive){
        this.ghgIsActive = ghgIsActive;
    }

    /**
     * Enable/Disable Weather Sub System
     * @param weatherIsActive Enable/Disable
     */
    public void setWeatherIsActive(boolean weatherIsActive){
        this.weatherIsActive = weatherIsActive;
    }

    /**
     * Enable/Disable Political Sub System
     * @param politicalIsActive Enable/Disable
     */
    public void setPoliticalIsActive(boolean politicalIsActive){
        this.politicalIsActive = politicalIsActive;
    }

    //========================================================
    //=================---- Overrides ----====================
    @Override
    protected void layoutView() {

    }

    @Override
    protected void tick() {
        //UPDATE WORLD STATE

        //TODO add conds for GS updates
        getModel().set(WorldProperty.TOTAL_GHG.id(), ghgSystems.getEmissionsPerUpdate());
        getModel().set(WorldProperty.AVG_RELATIONS.id(), politicalSystems.getTotalRelations());
        //getModel().set(WorldProperty.AVG_TEMP.id(), )
    }
}
