package game.climatar;

import com.kotcrab.vis.ui.VisUI;

import game.climatar.architecture.Main;
import game.climatar.menu.TitleController;

public class ApplicationController extends Main {

	private TitleController titleController;
	
	@Override
	public void create() {
		VisUI.load(); // load the UI skin (now it can be used everywhere)

		/*
		 * Manages all controllers declared in this class
		 * => Initializes the controller
		 * => Initializes the controller's views
		 * => Binds the controller with the view
		 */
		initialize(this);

		titleController.openTitleView();
		
		setViewController(titleController);
	}

}
