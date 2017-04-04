package game.climatar.menu;

import com.badlogic.gdx.Gdx;

import game.climatar.architecture.Controller;

public class TitleController extends Controller {

	// Model
	private MenuScreenModel model;
	
	// View
	private TitleView titleView;
	private GameModeSelectView gameModeSelectView;
	
	public void setHudScale(float hudScale) {
		titleView.setHudScale(hudScale);
	}

	public void openTitleView() {
		showView(titleView);
	}

	public void openGameModeSelectView() {
		showView(gameModeSelectView);
	}

	public void openLoadViewScreen() {

	}

	public void overlordMode() {

	}

	public void survivalMode() {

	}

	@Override
	public void tick() {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		titleView.setFrame(width/2, height/2, width/2, height/2);
		gameModeSelectView.setFrame(0, height/2, width/2, height/2);
	}

}
