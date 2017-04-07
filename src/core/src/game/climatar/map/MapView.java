package game.climatar.map;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.kotcrab.vis.ui.widget.VisTable;

import game.climatar.WorldSimulator;
import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;

@AllowController({WorldSimulator.class})
public class MapView extends View {

	private DrawableMap map;
	
	private VisTable table;
	
	@Override
	public void build(Group group) {
		
		WorldMap world = MapGenerator.generateMap(4, 14, 7, 4);
		
		map = new DrawableMap(world.terrain, 0.2f);
		
		table = new VisTable();
		table.add(map);
		
		group.addActor(table);
	}

	@Override
	public void layout(float x, float y, float width, float height) {
		map.setFrame(x, y, width, height);
		
		table.pack();
		table.invalidate();
		table.validate();
		table.layout();
	}

	@Override
	public void update(Model model) {
		
	}

	@Override
	public void dispose() {
		map.dispose();
	}
	
}
