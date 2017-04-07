package game.climatar.systems.weather;

import java.util.HashMap;

import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.map.Nation;
import game.climatar.systems.political.PoliticalSystemController;
import game.climatar.systems.weather.WeatherSystemController;

public class WeatherController extends Controller {
    
    // initial conditions
    private static final double INITIAL_TEMPERATURE_FN = 32;
    private static final double INITIAL_TEMPERATURE_EN = 28;
    private static final double INITIAL_TEMPERATURE_WN = -15;
    private static final double INITIAL_TEMPERATURE_AN = 12;

    private static final double INITIAL_PRECIPITATION_FN = 50;
    private static final double INITIAL_PRECIPITATION_EN = 35;
    private static final double INITIAL_PRECIPITATION_WN = 30;
    private static final double INITIAL_PRECIPITATION_AN = 20;

    WeatherSystemController earthNationController;
    WeatherSystemController fireNationController;
    WeatherSystemController waterNationController;
    WeatherSystemController airNationController;

    private HashMap<Nation, WeatherSystemController> weatherSystems = new HashMap<Nation, WeatherSystemController>();
    private boolean isActive = true;

    @Override
    protected void initialize() {
	//Setup Controllers
        ((WeatherSystemModel) earthNationController.getModel())
	    .init(INITIAL_TEMPERATURE_EN, INITIAL_PRECIPITATION_EN);
        ((WeatherSystemModel) fireNationController.getModel())
	    .init(INITIAL_TEMPERATURE_FN, INITIAL_PRECIPITATION_FN);
        ((WeatherSystemModel) airNationController.getModel())
	    .init(INITIAL_TEMPERATURE_AN, INITIAL_PRECIPITATION_AN);
        ((WeatherSystemModel) waterNationController.getModel())
	    .init(INITIAL_TEMPERATURE_WN, INITIAL_PRECIPITATION_WN);

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

    public void Activiate(){
        for (Nation n : Nation.values()) {
            weatherSystems.get(n).show();
        }
        isActive = true;
    }

    public void DeActivate(){
        for (Nation n : Nation.values()) {
            weatherSystems.get(n).hide();
        }
        isActive = false;
    }

    public void setPrecipitationDelta(Nation n, double delta){
        if(isActive)
            weatherSystems.get(n).setDeltaPrecipitation(delta);
    }

    public void setTemperatureDelta(Nation n, double delta){
        if(isActive)
            weatherSystems.get(n).setDeltaTemperature(delta);
    }

    /**
     * @param n Nation being fetched
     * @return Controller for Nation n
     */
    public WeatherSystemController getWeatherSystemController(Nation n) {
    	return weatherSystems.get(n);
    }
}
