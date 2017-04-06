package game.climatar.systems.weather;

import game.climatar.architecture.Model;

public class WeatherSystemModel extends Model{

    enum WeatherProperty {
        CURRENT_TEMP, CURRENT_PERCIP, CHANGE_IN_TEMP, CHANGE_IN_PERCIP;

        public String id() {
            return this.name();
        }
    }

    /**
     * Initializes this WeatherSystemModel with an initial temperature and percipitation
     */

    public void inittemp(double initialtemp) {
        set(WeatherProperty.CURRENT_TEMP.id(), initialtemp);
    }

    public void initpercip(int initialpercipitation){
        set(WeatherProperty.CURRENT_PERCIP.id(),initialpercipitation);
    }

}
