package game.climatar.play;

import com.badlogic.gdx.Gdx;

import game.climatar.architecture.Controller;
import game.climatar.map.MapView;

public class PlayController extends Controller {
	
	MapView mapView;
	
	@Override
	protected void layoutView() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
		mapView.setFrame(0, 0, width, height);
		
		showView(mapView);
	}

	@Override
	protected void tick() {
		
	}
	

}
