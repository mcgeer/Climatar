package game.climatar.map;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.kotcrab.vis.ui.widget.VisTable;

import game.climatar.WorldSimulator;
import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;
import game.climatar.map.DrawableMap.MapSize;

@AllowController({WorldSimulator.class})
public class MapView extends View {

	private DrawableMap map;
	
	private VisTable table;
	
	@Override
	public void build(Group group) {
		WorldMap world = MapGenerator.generateMap(4, 14, 7, 4);
		
		map = new DrawableMap(world, 0.2f);
		
		table = new VisTable();
		table.add(map);
		
		group.addActor(table);
		
		group.addListener(new DragListener() {
			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				System.out.println("Drawing!");
				float dragDistanceX = getDeltaX();
				float dragDistanceY = getDeltaY();

				float posX = map.getX();
				float posY = map.getY();

				float maxWidth = map.getFrame().width / map.getScale();
				float maxHeight = map.getFrame().height / map.getScale();

				float textureWidth = map.getWidth();
				float textureHeight = map.getHeight();

				float newPosY = posY + dragDistanceY;

				if (map.getVerticalMapSize() == MapSize.SMALLER_THAN_VIEWPORT) {
					if (newPosY < 0)
						newPosY = 0;
					if (newPosY > maxHeight - textureHeight)
						newPosY = maxHeight - textureHeight;

					map.setY(newPosY);
				} else if (map.getVerticalMapSize() == MapSize.BIGGER_THAN_VIEWPORT) {
					if (newPosY > 0)
						newPosY = 0;
					if (newPosY < maxHeight - textureHeight)
						newPosY = maxHeight - textureHeight;

					map.setY(newPosY);
				}

				float newPosX = posX + dragDistanceX;
				if (map.getHorizontalMapSize() == MapSize.SMALLER_THAN_VIEWPORT) {
					if (newPosX < 0)
						newPosX = 0;
					if (newPosX > maxWidth - textureWidth)
						newPosX = maxWidth - textureWidth;

					map.setX(newPosX);
				} else if (map.getHorizontalMapSize() == MapSize.BIGGER_THAN_VIEWPORT) {
					if (newPosX > 0)
						newPosX = 0;
					if (newPosX < maxWidth - textureWidth)
						newPosX = maxWidth - textureWidth;

					map.setX(newPosX);
				}
			}
		});

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
