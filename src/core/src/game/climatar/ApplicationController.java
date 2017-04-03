package game.climatar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.kotcrab.vis.ui.VisUI;

import game.climatar.menu.MenuScreenController;

public class ApplicationController extends Game {
	
	public static final int WIDTH = 200;
	public static final int HEIGHT = WIDTH * 16 / 9;
	
	public static final float HUD_SCALE = 2f;
	private static final float FADE_OUT_DURATION = 0.2f;
	
	private static final Color BG_COLOUR = Color.BLACK;

	private MenuScreenController menuScreenController;
	
	public ApplicationController(float hudScale) {
		
		menuScreenController = new MenuScreenController(hudScale, FADE_OUT_DURATION);
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
