package game.climatar.menu;

import com.badlogic.gdx.Screen;

import game.climatar.view.Presentation;
import game.climatar.view.View;

public class MenuScreenController {
	
	// Model
	/* ? */
	
	// View
	private View menuView;
	private TitlePresentation titlePresentation;
	private GameModeSelectPresentation gameModeSelectPresentation;
	
	private Presentation lastPresentation = null;
	
	public MenuScreenController() {
		menuView = new View();
		
		titlePresentation = new TitlePresentation(this);
		gameModeSelectPresentation = new GameModeSelectPresentation(this);
		
		titlePresentation.addTo(menuView);
		gameModeSelectPresentation.addTo(menuView);
		
		showPresentation(titlePresentation);
	}
	
	public void setHudScale(float hudScale) {
		titlePresentation.setHudScale(hudScale);
	}

	public Screen getView() {
		return menuView;
	}

	private void showPresentation(Presentation presentation) {
		if(lastPresentation != null) {
			lastPresentation.hide();
		}
		
		presentation.show();
		lastPresentation = presentation;
	}
	
	public void openTitlePresentation() {
		showPresentation(titlePresentation);
	}
	
	public void openGameModeSelectPresentation() {
		showPresentation(gameModeSelectPresentation);
	}

    public void openLoadViewScreen() {
		
    }

    public void overlordMode() {
		
    }
	
    public void survivalMode() {
		
    }

}
