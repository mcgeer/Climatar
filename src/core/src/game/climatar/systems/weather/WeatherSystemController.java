package game.climatar.systems.weather;

import com.badlogic.gdx.Gdx;
import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.systems.weather.WeatherSystemModel.WeatherProperty;

@SetModel(WeatherSystemModel.class)
public class WeatherSystemController extends Controller {

	private WeatherSystemView systemView;
	private boolean isActive = true;

	@Override
	protected void initialize() {

	}

	@Override
	protected void layoutView() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		systemView.setFrame(10.0f, 0, width / 2, height / 2);
	}

	public void show(){

        showView(systemView);
    }

    public void hide(){
        showView();
    }

	@Override
	protected void tick() {
		if(isActive) {
			// update the temperature
			double temperature = (Double) getModel().get(WeatherProperty.TEMPERATURE.id());
			double deltaTemperature = (Double) getModel().get(WeatherProperty.DELTA_TEMPERATURE.id());
			getModel().set(WeatherProperty.TEMPERATURE.id(), temperature + deltaTemperature);

			// update the precipitation
			double precipitation = (Double) getModel().get(WeatherProperty.PRECIPITATION.id());
			double deltaPrecipitation = (Double) getModel().get(WeatherProperty.DELTA_PRECIPITATION.id());
			getModel().set(WeatherProperty.TEMPERATURE.id(), precipitation + deltaPrecipitation);
		}
	}

	public void setDeltaTemperature(double delta) {
		getModel().set(WeatherProperty.DELTA_TEMPERATURE.id(), delta);
	}

	public void setDeltaPrecipitation(double delta) {
		getModel().set(WeatherProperty.DELTA_PRECIPITATION.id(), delta);
	}

	public double getTemperature() {
		return (Double) getModel().get(WeatherProperty.TEMPERATURE.id());
	}

	public double getPrecipitation() {
		return (Double) getModel().get(WeatherProperty.PRECIPITATION.id());
	}

	public void setIsActive(boolean active) {
		this.isActive = active;
	}
}
