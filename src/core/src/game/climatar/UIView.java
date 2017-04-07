package game.climatar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisTable;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;
import game.climatar.map.Nation;

@AllowController(WorldSimulator.class)
public class UIView extends View {

	private VisTable table;
	private VisImageButton[] nationButtons;
	
	@Override
	public void build(Group group) {
		table = new VisTable();
		nationButtons = new VisImageButton[Nation.values().length];
		
		Value width = new Value() {
			@Override
			public float get(Actor context) {
				float width = getFrame().getHeight();
				
				return width;
			}
		};
		
		Value height = new Value() {
			@Override
			public float get(Actor context) {
				float height = getFrame().getHeight() / Nation.values().length;
				
				return height / 4;
			}
		};
		
		int i = 0;
		for(Nation n : Nation.values()) {
			if(n == Nation.BLUE_LOTUS) continue;
			final Nation nation = n;
			final WorldSimulator sim = (WorldSimulator) getController();
			
			TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(n.getImageFileName())));
			nationButtons[i] = new VisImageButton(drawable);
			nationButtons[i].addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					sim.openNationView(nation);
				}
			});
			table.add(nationButtons[i]).width(width).height(height);
			i++;
		}
		
		group.addActor(table);
	}

	@Override
	public void layout(float x, float y, float width, float height) {
		
	}

	@Override
	public void update(Model model) {
		
	}

	@Override
	public void dispose() {
		
	}

}
