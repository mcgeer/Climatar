package game.climatar.systems.weather;

import game.climatar.architecture.Controller;

/**
 * Created by Leo_Yuan on 17/4/6.
 */

public class WeatherController extends Controller{
    // constants
    //Set the limit for bad conditions
    private static final int BadPercipitation = 25;
    private static final double BadTemperature = 50;



    //set initial values for each nation
    private int initialPercipitationFN = 50, initialPercipitationAN = 50, initialPercipitationWN = 50, initialPercipitationEN = 50;
    private double initialTemperatureFN = 35, initialTemperatureAN = 5, initialTemperatureWN = -10,initialTemperatureEN = 28;

    // child controllers
    WeatherSystemController earthNationWeatherController;
    WeatherSystemController fireNationWeatherController;
    WeatherSystemController waterNationWeatherController;
    WeatherSystemController airNationWeatherController;

    @Override
    protected void initialize() {
        System.out.println(earthNationWeatherController.getModel());
        ((WeatherSystemModel) earthNationWeatherController.getModel()).initpercip(initialPercipitationEN);
        ((WeatherSystemModel) earthNationWeatherController.getModel()).inittemp((initialTemperatureEN));
        ((WeatherSystemModel) fireNationWeatherController.getModel()).initpercip(initialPercipitationFN);
        ((WeatherSystemModel) fireNationWeatherController.getModel()).inittemp((initialTemperatureFN));
        ((WeatherSystemModel) airNationWeatherController.getModel()).initpercip(initialPercipitationAN);
        ((WeatherSystemModel) airNationWeatherController.getModel()).inittemp((initialTemperatureAN));
        ((WeatherSystemModel) waterNationWeatherController.getModel()).initpercip(initialPercipitationWN);
        ((WeatherSystemModel) waterNationWeatherController.getModel()).inittemp((initialTemperatureWN));
    }

    @Override
    protected void layoutView() {

    }

    @Override
    protected void tick() {
        // set the model's total emissions (by all nations)

        double fireTemp = fireNationWeatherController.getTemp();
        int firePercip = fireNationWeatherController.getPercip();
        double earthTemp=earthNationWeatherController.getTemp();
        int earthPercip = earthNationWeatherController.getPercip();
        double airTemp = airNationWeatherController.getTemp();
        int airPercip = airNationWeatherController.getPercip();
        double WaterTemp = waterNationWeatherController.getTemp();
        int WaterPercip = waterNationWeatherController.getPercip();

    }

    /**
     *
     * @return
     */


}
