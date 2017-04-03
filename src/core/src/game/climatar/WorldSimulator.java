package game.climatar;

import java.util.Map;

import game.climatar.map.GameState;
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
    public WorldSimulator(boolean monitoringGHG, boolean monitoringWeather, boolean monitoringPolitical){
        //Set up what systems are being used
        ghgIsActive = monitoringGHG;
        weatherIsActive = monitoringWeather;
        politicalIsActive = monitoringPolitical;

        //System Links May not be used
        ghgSystems = new GHGSystemController();
        weatherSystems = new WeatherSystemController();
        politicalSystems = new PoliticalSystemController();

        //Set up the Game State
        gameState = new GameState();
    }


    /**
     * Update Function to handle all requests to step the state of the world
     */
    public void Simulate(){

    }


}
