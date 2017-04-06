package game.climatar.systems.weather;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.widget.VisLabel;
import game.climatar.architecture.SetModel;
import game.climatar.architecture.Controller;
import game.climatar.map.Nation;
import game.climatar.systems.weather.WeatherSystemModel.WeatherProperty;

@SetModel(WeatherSystemModel.class)
public class WeatherSystemController extends Controller{

    private WeatherSystemView weatherSystemView;

    @Override
    protected void initialize() {
        getModel().set(WeatherSystemModel.WeatherProperty.CHANGE_IN_PERCIP.id(), 0);
        getModel().set(WeatherSystemModel.WeatherProperty.CHANGE_IN_TEMP.id(), 0);
    }



    //Set the limit for bad conditions
    private static final int BadPercipitation = 25;
    private static final double BadTemperature = 50;




    public boolean IsSafeTemperature(){
        if ((double) getModel().get(WeatherProperty.CURRENT_TEMP.id())>BadTemperature){
            return false;
        }
        else
            return true;

    }

    public boolean IsDrought() {
        if ((int) getModel().get(WeatherProperty.CURRENT_PERCIP.id()) < BadPercipitation)
            return true;

        else
            return false;
    }

    @Override
    protected void tick() {
        // Update the EmissionsPerUpdate value based on DeltaEmissions
        double currenttemp = (double) getModel().get(WeatherProperty.CURRENT_TEMP.id());
        double deltatemp = (double) getModel().get(WeatherProperty.CHANGE_IN_TEMP.id());
        int currentPerci = (int)getModel().get(WeatherProperty.CURRENT_PERCIP.id());
        int deltaPerci = (int)getModel().get(WeatherProperty.CHANGE_IN_PERCIP.id());


        currentPerci+=deltaPerci;
        currenttemp+=deltatemp;

        getModel().set(WeatherProperty.CURRENT_PERCIP.id(), currentPerci);
        getModel().set(WeatherProperty.CURRENT_TEMP.id(), currenttemp);
    }


    public double getTemp() {
        return (double) getModel().get(WeatherProperty.CURRENT_TEMP.id());
    }

    public int getPercip() {
        return (int) getModel().get(WeatherProperty.CURRENT_PERCIP.id());
    }


    @Override
    protected void layoutView() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        weatherSystemView.setFrame(0, 0, width/2, height/2);

        showView(weatherSystemView);
    }
}
