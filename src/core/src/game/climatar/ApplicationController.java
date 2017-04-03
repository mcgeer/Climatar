package game.climatar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.kotcrab.vis.ui.VisUI;

import game.climatar.menu.MenuScreenController;
import game.climatar.systems.ghg.GHGSystemController;
import game.climatar.systems.political.PoliticalSystemController;
import game.climatar.systems.weather.WeatherSystemController;

public class ApplicationController extends Game {
	
	public static final int WIDTH = 200;
	public static final int HEIGHT = WIDTH * 16 / 9;
	
	public static final float HUD_SCALE = 2f;
	
	private static final Color BG_COLOUR = Color.BLACK;
	
	private GHGSystemController ghgController;
	private WeatherSystemController weatherController;
	private PoliticalSystemController politicalController;
	
	private MenuScreenController menuScreenController;
	
	public ApplicationController(float hudScale) {
		ghgController = new GHGSystemController();
		weatherController = new WeatherSystemController();
		politicalController = new PoliticalSystemController();
		
		menuScreenController = new MenuScreenController(HUD_SCALE);
	}
	
	@Override
	public void create() {
		VisUI.load(); // load the UI skin
		setScreen(menuScreenController.getView());
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(BG_COLOUR.r, BG_COLOUR.g, BG_COLOUR.b, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		super.render();
	}
	
}
