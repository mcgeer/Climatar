package game.climatar.menu;

import com.badlogic.gdx.Screen;

public class MenuScreenController {
	
	private MenuScreen menuScreenView;
	
	public MenuScreenController(float hudScale, float fadeDuration) {
		menuScreenView = new MenuScreen(this, hudScale, fadeDuration);
	}
	
	public void setHudScale(float hudScale) {
		menuScreenView.setHudScale(hudScale);
	}

	public Screen getView() {
		return menuScreenView;
	}

	public void openGameModeSelectView() {
		
	}

	public void openLoadViewScreen() {
		
	}

	public void overlordMode() {
		
	}
	
	public void survivalMode() {
		
	}

}
