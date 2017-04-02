package game.climatar.menu;

import com.badlogic.gdx.Screen;

public class MenuScreenController {
	
	private MenuScreen menuScreenView;
	
	private float hudScale;
	
	public void setHudScale(float hudScale) {
		menuScreenView.setHudScale(hudScale);
		this.hudScale = hudScale;
	}
	
	public MenuScreenController(float hudScale) {
		menuScreenView = new MenuScreen(hudScale);
		this.hudScale = hudScale;
	}

	public Screen getView() {
		return menuScreenView;
	}

}
