package game.climatar.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import game.climatar.map.MapView;

public class MenuScreen implements Screen {
	
    private MenuScreenController controller;
    private Stage stage;

    private TitlePresentation titlePresentation;
	
    private float hudScale;
    private float fadeOutDuration;

    // for the background map
    private int[][] tileSpec;
	
    public MenuScreen(MenuScreenController menuScreenController, float hudScale, float fadeOutDuration, int[][] tileSpec) {
	this.controller = menuScreenController;
	this.hudScale = hudScale;
	this.fadeOutDuration = fadeOutDuration;
	this.tileSpec = tileSpec;
    }

    public void setHudScale(float hudScale) {
	titlePresentation.setHudScale(hudScale);
	this.hudScale = hudScale;
    }
	
    @Override
    public void show() {
	stage = new Stage(new ScreenViewport());

	titlePresentation = new TitlePresentation(controller, stage, hudScale, fadeOutDuration, tileSpec);
	titlePresentation.addTo(stage);

	Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
	stage.act();
	stage.draw();
    }

    @Override
    public void resize(int width, int height) {
	stage.getViewport().update(width, height, true);

	titlePresentation.setPosition(0, 0, width, height);
    }

    @Override
    public void pause() {
		
    }

    @Override
    public void resume() {
		
    }

    @Override
    public void hide() {
		
    }

    @Override
    public void dispose() {
		
    }


}
