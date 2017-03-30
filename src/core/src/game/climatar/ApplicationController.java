package game.climatar;

import com.badlogic.gdx.Game;

import game.climatar.systems.ghg.GHGSystemView;
import game.climatar.systems.political.PoliticalSystemView;
import game.climatar.systems.weather.WeatherSystemView;

public class ApplicationController extends Game {
	
	private GHGSystemView ghgView;
	private WeatherSystemView weatherView;
	private PoliticalSystemView politicalView;
	
	public ApplicationController() {
		
	}
	
	@Override
	public void create() {
		
	}
	
}
