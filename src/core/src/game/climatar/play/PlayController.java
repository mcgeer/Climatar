package game.climatar.play;

import game.climatar.architecture.Controller;
import game.climatar.map.MapView;
import game.climatar.systems.ghg.GHGController;

public class PlayController extends Controller {

	GHGController ghgController;
	
	MapView mapView;
	
	@Override
	protected void layoutView() {
		showView(mapView);
	}

	@Override
	protected void tick() {
		
	}
	

}
