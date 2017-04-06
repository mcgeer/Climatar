package game.climatar.systems.weather;
 
import game.climatar.architecture.Model;
import game.climatar.map.Nation;
 
public class WeatherSystemModel extends Model {
 
    enum WeatherProperty {
	DELTA_TEMPERATURE,
	TEMPERATURE,
	DELTA_PRECIPITATION,
	PRECIPITATION;
 
	public String id() {
	    return this.name();
	}
    }
 
    public void init(double initialTemperature,
		     double initialPrecipitation) {
 
	set(WeatherProperty.TEMPERATURE.id(), initialTemperature);
	set(WeatherProperty.DELTA_TEMPERATURE.id(), 0);
	set(WeatherProperty.PRECIPITATION.id(), initialPrecipitation);
	set(WeatherProperty.DELTA_PRECIPITATION.id(), 0);
    }
}
