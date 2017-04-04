package game.climatar;

import com.kotcrab.vis.ui.VisUI;

import game.climatar.architecture.ControllerManager;
import game.climatar.menu.TitleController;
import game.climatar.play.PlayController;

public class ApplicationController extends ControllerManager {

	private TitleController titleController;
	private PlayController playController;

	@Override
	public void create() {
		VisUI.load(); // load the UI skin (now it can be used everywhere)

		/*
		 * Manages all controllers declared in this class => Initializes the
		 * controller, its views, binds the controller with the view, and binds
		 * the model in the controller with the view
		 */
		initialize(this);

		addViewController(titleController);
		addViewController(playController);
	}
	
	public void play() {
		removeViewController(titleController);
	}
	
	public void title() {
		removeViewController(titleController);
	}

}
