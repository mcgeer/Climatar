package game.climatar.systems.weather;
import java.util.HashMap;

import com.kotcrab.vis.ui.widget.VisLabel;

import game.climatar.map.Nation;


public class WeatherSystemController {

    private HashMap<Nation, WeatherSystemModel> modelLink;
    private HashMap<Nation, WeatherSystemView> viewLink;

    //Set the limit for bad conditions
    private static final int BadPercipitation = 25;
    private static final double BadTemperature = 50;

    //set initial values for each nation
    private int initialPercipitationFN = 50, initialPercipitationAN = 50, initialPercipitationWN = 50, initialPercipitationEN = 50;
    private double initialTemperatureFN = 35, initialTempertureAN = 5, initialTemperatureWN = -10,initialTemperatureEN = 28;


    //initialize the controller
    public WeatherSystemController(){
        modelLink = new HashMap<Nation, WeatherSystemModel>();
        viewLink = new HashMap<Nation, WeatherSystemView>();

        //Initialize Fire
        modelLink.put(Nation.FIRE, new WeatherSystemModel(initialPercipitationFN, initialTemperatureFN));
        viewLink.put(Nation.FIRE, new WeatherSystemView());
        //Initialize Air
        modelLink.put(Nation.AIR, new WeatherSystemModel(initialPercipitationAN, initialTempertureAN));
        viewLink.put(Nation.AIR, new WeatherSystemView());
        //Initialize Water
        modelLink.put(Nation.WATER, new WeatherSystemModel(initialPercipitationWN, initialTemperatureWN));
        viewLink.put(Nation.WATER, new WeatherSystemView());
        //Initialize Earth
        modelLink.put(Nation.EARTH, new WeatherSystemModel(initialPercipitationEN, initialTemperatureEN));
        viewLink.put(Nation.EARTH, new WeatherSystemView());
    }


    public boolean IsSafeTemperature(Nation nation){
        if (GetTemperatureLevel(nation)>BadTemperature){
            return false;
        }
        else
            return true;

    }

    public boolean IsDrought(Nation nation) {
        if (GetPercipitationLevel(nation) < BadPercipitation)
            return true;

        else
            return false;
    }

    public Double GetTemperatureLevel(Nation nation){

        return modelLink.get(nation).gettemperature();


    }

    public int GetPercipitationLevel(Nation nation){
        return modelLink.get(nation).getPercipitation();
    }

    public void GetWeatherLabel (Nation nation){
        VisLabel weathertemperaturelabel = new VisLabel("Current Temperature: "+ GetTemperatureLevel(nation)+"Degree");
        VisLabel weatherPercipitationlabel = new VisLabel("Current Percipitation: "+GetPercipitationLevel(nation)+"mm");
    }
}
