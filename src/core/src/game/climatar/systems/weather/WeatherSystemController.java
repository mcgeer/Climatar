package game.climatar.systems.weather;

import com.badlogic.gdx.Gdx;
import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.systems.weather.WeatherSystemModel.WeatherProperty;

@SetModel(WeatherSystemModel.class)
public class WeatherSystemController extends Controller {

	private WeatherSystemView systemView;

	@Override
	protected void initialize() {
		getModel().set(WeatherProperty.DELTA_TEMPERATURE.id(), 0);
		getModel().set(WeatherProperty.TEMPERATURE.id(), 0);
		getModel().set(WeatherProperty.DELTA_PRECIPITATION.id(), 0);
		getModel().set(WeatherProperty.PRECIPITATION.id(), 0);
	}

	@Override
	protected void layoutView() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		showView(systemView);
		systemView.setFrame(0, 0, width / 2, height / 2);
	}

	@Override
	protected void tick() {
		// update the temperature
		double temperature = (Double) getModel().get(WeatherProperty.TEMPERATURE.id());
		double deltaTemperature = (Double) getModel().get(WeatherProperty.DELTA_TEMPERATURE.id());
		getModel().set(WeatherProperty.TEMPERATURE.id(), temperature + deltaTemperature);

		// update the precipitation
		double precipitation = (Double) getModel().get(WeatherProperty.PRECIPITATION.id());
		double deltaPrecipitation = (Double) getModel().get(WeatherProperty.DELTA_PRECIPITATION.id());
		getModel().set(WeatherProperty.TEMPERATURE.id(), precipitation + deltaPrecipitation);
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
}
