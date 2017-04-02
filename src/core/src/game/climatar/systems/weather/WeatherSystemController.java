package game.climatar.systems.weather;



public class WeatherSystemController {

    private Integer BadPercipitation = 20;
    private double BadTemperature = 50;


    public boolean IsSafeTemperature(nation Nation){
        if (GetTemperatureLevel(Nation)>BadTemperature){
            return false;
        }
        else
            return true;

    }

    public boolean IsDrought(nation Nation) {
        if (GetPercipitationLevel(Nation) < BadPercipitation)
            return true;

        else
            return false;
    }

    public Double GetTemperatureLevel(nation Nation){
        return Nation.temperature;
    }

    public Integer GetPercipitationLevel(nation Nation){
        return Nation.Percipitation;
    }
}
