package game.climatar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.kotcrab.vis.ui.VisUI;

import game.climatar.menu.MenuScreenController;

public class ApplicationController extends Game {
	private static final Color BACKGROUND_COLOUR = Color.BLACK;
	
	private MenuScreenController menuScreenController;

	private float hudScale;
	
	public ApplicationController(float hudScale) {
		this.hudScale = hudScale;
	}
	
	@Override
	public void create() {
		VisUI.load(); // load the UI skin
		
		menuScreenController = new MenuScreenController();
		menuScreenController.setHudScale(hudScale);
		
		setScreen(menuScreenController.getView());
	}
	
	@Override
	public void render() {
		// Clear screen
		Gdx.gl.glClearColor(BACKGROUND_COLOUR.r, BACKGROUND_COLOUR.g, BACKGROUND_COLOUR.b, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		// Render current screen.
		super.render();
	}
	
}
