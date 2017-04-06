package game.climatar.news;

import game.climatar.GameState;
import game.climatar.architecture.Controller;

public class NewsEventControl extends Controller {
	private NewsEventGenerator pressMill;
	private GameState gs;

	NewsView view;

	@Override
	protected void initialize() {

	}

	@Override
	protected void layoutView() {
		showView(view);
	}

	public void generateNews() {
		pressMill = new NewsEventGenerator(gs);
	}

	@Override
	protected void tick() {
		// ControllerManager.delay();
	}
}
