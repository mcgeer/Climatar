package game.climatar;

import com.kotcrab.vis.ui.VisUI;

import game.climatar.GameState.WorldProperty;
import game.climatar.architecture.ControllerManager;
import game.climatar.map.Nation;
import game.climatar.menu.TitleController;

public class ApplicationController extends ControllerManager {

	private TitleController titleController;
	private WorldSimulator worldSimulator;

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
	}
	
	public void newGame(Nation playerNation) {
		GameState state = new GameState();
		
		state.set(WorldProperty.NATION.id(), playerNation);
		
		play(state);
	}
	
	public void play(GameState state) {
		removeViewController(titleController);
		addViewController(worldSimulator);
	}
	
	public void title() {
		removeViewController(worldSimulator);
		addViewController(titleController);
	}

}
