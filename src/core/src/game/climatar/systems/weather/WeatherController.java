package game.climatar.systems.weather;

import java.util.HashMap;

import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.map.Nation;
import game.climatar.systems.political.PoliticalSystemController;
import game.climatar.systems.weather.WeatherSystemController;

public class WeatherController extends Controller {
    
    // initial conditions
    private static final double INITIAL_TEMPERATURE_FN = 0;
    private static final double INITIAL_TEMPERATURE_EN = 0;
    private static final double INITIAL_TEMPERATURE_WN = 0;
    private static final double INITIAL_TEMPERATURE_AN = 0;

    private static final double INITIAL_PRECIPITATION_FN = 0;
    private static final double INITIAL_PRECIPITATION_EN = 0;
    private static final double INITIAL_PRECIPITATION_WN = 0;
    private static final double INITIAL_PRECIPITATION_AN = 0;

    WeatherSystemController earthNationController;
    WeatherSystemController fireNationController;
    WeatherSystemController waterNationController;
    WeatherSystemController airNationController;

    private HashMap<Nation, WeatherSystemController> weatherSystems = new HashMap<Nation, WeatherSystemController>();

    @Override
    protected void initialize() {
	//Setup Controllers
        ((WeatherSystemModel) earthNationController.getModel())
	    .init(INITIAL_TEMPERATURE_FN, INITIAL_PRECIPITATION_FN);
        ((WeatherSystemModel) fireNationController.getModel())
	    .init(INITIAL_TEMPERATURE_EN, INITIAL_PRECIPITATION_EN);
        ((WeatherSystemModel) airNationController.getModel())
	    .init(INITIAL_TEMPERATURE_WN, INITIAL_PRECIPITATION_WN);
        ((WeatherSystemModel) waterNationController.getModel())
	    .init(INITIAL_TEMPERATURE_AN, INITIAL_PRECIPITATION_AN);

        //Setup Map
        weatherSystems.put(Nation.FIRE, fireNationController);
        weatherSystems.put(Nation.EARTH, earthNationController);
        weatherSystems.put(Nation.AIR, airNationController);
        weatherSystems.put(Nation.WATER, waterNationController);
    }
    public WeatherSystemController getEarthSubControl(){
		return earthNationController;
	}
	public WeatherSystemController getFireSubControl(){
		return fireNationController;
	}
	public WeatherSystemController getWaterSubControl(){
		return waterNationController;
	}
	public WeatherSystemController getAirSubControl(){
		return airNationController;

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
    public WeatherSystemController getWeatherSystemController(Nation n) {
    	return weatherSystems.get(n);
    }
}
