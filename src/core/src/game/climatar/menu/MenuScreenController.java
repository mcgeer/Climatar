package game.climatar.menu;

import com.badlogic.gdx.Screen;

import game.climatar.map.MidpointDisplacement;

public class MenuScreenController {
	
    private MenuScreen menuScreenView;
	
    public MenuScreenController(float hudScale, float fadeDuration) {
	// generate a random map for the menu screen background
	int[][] tileSpec = MidpointDisplacement.getMap(7, 6, 10, 2f);
	menuScreenView = new MenuScreen(this, hudScale, fadeDuration, tileSpec);
    }
	
    public void setHudScale(float hudScale) {
	menuScreenView.setHudScale(hudScale);
    }

    public Screen getView() {
	return menuScreenView;
    }

    public void openGameModeSelectView() {
	// Just testing out the map don't freak out! - James
	    
    }

    public void openLoadViewScreen() {
		
    }

    public void overlordMode() {
		
    }
	
    public void survivalMode() {
		
    }

}
