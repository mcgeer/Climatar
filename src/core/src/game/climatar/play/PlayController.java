package game.climatar.play;

import game.climatar.architecture.Controller;
import game.climatar.systems.ghg.GHGSystemView;

public class PlayController extends Controller {

	private GHGSystemView GHGSystemView;

	public void showPlayView() {
		showView(GHGSystemView);
	}

	@Override
	protected void layoutView() {

	}

	@Override
	protected void tick() {

	}

}
