package game.climatar.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class MenuScreen implements Screen {

	private Stage stage;
	
	private VisTextButton startGameButton;
	private VisTextButton loadGameButton;
	private VisTable buttonsTable;

	private VisLabel titleLabel;
	
	private static final String TITLE_TEXT = "Climatar";

	private float hudScale;
	
	public MenuScreen(float hudScale) {
		this.hudScale = hudScale;
	}
	
	public void setHudScale(float hudScale) {
		this.hudScale = hudScale;
	}
	
	@Override
	public void show() {
		stage = new Stage(new ScreenViewport());
		
		buildMenuScreen();
	}

	private void buildMenuScreen() {
		stage.clear();
		
		float cellWidth = Gdx.graphics.getWidth();
		float cellHeight = Gdx.graphics.getHeight()/8f;
		float fontScale = 3f;
		
		GlyphLayout layout = new GlyphLayout(VisUI.getSkin().get(LabelStyle.class).font, TITLE_TEXT);
		titleLabel = new VisLabel(TITLE_TEXT);
		titleLabel.setFontScale(fontScale);
		titleLabel.setPosition(Gdx.graphics.getWidth()/2f - (layout.width*fontScale)/2, 2*Gdx.graphics.getHeight()/3);
		
		startGameButton = new VisTextButton("Start Game", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
			}
			
		});
		loadGameButton = new VisTextButton("Load Game");
		
		buttonsTable = new VisTable();
		buttonsTable.add(startGameButton).pad(0, 10f, 10f, 10f).height(cellHeight).width(cellWidth - 2 * 10f).row();
		buttonsTable.add(loadGameButton).pad(0, 10f, 10f, 10f).height(cellHeight).width(cellWidth - 2 * 10f).row();
		buttonsTable.pack();
		
		stage.addActor(buttonsTable);
		stage.addActor(titleLabel);
		
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
		
		buildMenuScreen();
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
