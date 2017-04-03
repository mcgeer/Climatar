package game.climatar;

import java.util.Map;

import game.climatar.map.GameState;
import game.climatar.map.Nation;
import game.climatar.news.NewsEventControl;
import game.climatar.systems.ghg.GHGSystemController;
import game.climatar.systems.political.PoliticalSystemController;
import game.climatar.systems.weather.WeatherSystemController;

public class WorldSimulator {

    //Actively logged SubSystems
    private boolean ghgIsActive, weatherIsActive, politicalIsActive;

    //Sub-system State Controllers
    private GHGSystemController ghgSystems;
    private WeatherSystemController weatherSystems;
    private PoliticalSystemController politicalSystems;

    //News Events
    private NewsEventControl newsController;

    //GameState
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

        //System Links May not be used
        ghgSystems = new GHGSystemController();
        weatherSystems = new WeatherSystemController();
        politicalSystems = new PoliticalSystemController();

        //Set up the Game State
        gameState = new GameState(player);

    }

    /**
     * Update Function to handle all requests to step the state of the world
     */
    public void Simulate(){
        //GENERATE NEWS
        //REACT TO NEWS

        //UPDATE Sub Systems
        if(ghgIsActive)
            gameState.updateWorldGHG(ghgSystems.Update());
        //TODO if(weatherIsActive)
        //TODO    weatherSystems.Update();
        if(politicalIsActive)
            gameState.updateWorldPlayerPolitics(politicalSystems.update(gameState.getPlayer()));


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
}
