package game.climatar.systems.weather;

public class WeatherSystemModel {

    private int Percipitation;
    private double Temperature;


    public WeatherSystemModel(int initialpercipitation,double initialTemperature){
        Percipitation = initialpercipitation;
        Temperature = initialTemperature;

    }

    public double gettemperature(){

        return Temperature;
    }

    public int getPercipitation(){
        return Percipitation;

    }

    public void updateTemperature(double deltanewTemperature){
        Temperature += deltanewTemperature;
    }

    public void updatePercipitation(int deltaNewPercipitation){
        Percipitation += deltaNewPercipitation;
    }

    public double UpdateTemperature(){
        return Temperature;
    }

    public int UpdatePercipitation(){return Percipitation;}
}
