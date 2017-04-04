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
		 * Manages all controllers declared in this class => Initializes the
		 * controller, its views, binds the controller with the view, and binds
		 * the model in the controller with the view
		 */
		initialize(this);

		titleController.openTitleView();

		setViewController(titleController);
	}

}
