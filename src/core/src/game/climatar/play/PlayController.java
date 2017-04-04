package game.climatar.play;

import com.badlogic.gdx.Gdx;

import game.climatar.architecture.Controller;
import game.climatar.systems.ghg.GHGSystemView;

public class PlayController extends Controller {

	private GHGSystemView GHGSystemView;

	@Override
	protected void layoutView() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
		GHGSystemView.setFrame(0, 0, width/2, height/2);
		
		showView(GHGSystemView);
	}

	@Override
	protected void tick() {

	}
	
	public void showPlayView() {
		showView(GHGSystemView);
	}

}
