package game.climatar;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;

@AllowController(WorldSimulator.class)
public class OverlayMenuView extends View {
	protected static final int PAD = 10;

	private Table table;

	private VisTextButton ghg;
	private VisTextButton political;
	private VisTextButton weather;
	
	private Value widthVal = new Value() {
		@Override
		public float get(Actor context) {
			return getFrame().width / 3 - PAD * 4/3;
		}
	};

	private Value heightVal = new Value() {
		@Override
		public float get(Actor context) {
			return getFrame().height;
		}
	};

	@Override
	public void build(Group group) {
		group.debug();
		table = new Table();
		
		Pixmap p = new Pixmap(1,1,Format.RGBA8888);
		p.setColor(0,0,0,0.6f);
		p.fill();
		
		table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(p))));
		ghg = new VisTextButton("Toggle\nGHG\nSystems", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				toggleGHGSystem();
			}
		});
		
		weather = new VisTextButton("Toggle\nWeather\nSystems", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				toggleWeatherSystem();
			}
		});
		
		political = new VisTextButton("Toggle\nPolitical\nSystems", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				togglePoliticalSystem();
			}
		});
		
		table.add(ghg).width(widthVal).height(heightVal).padLeft(PAD).padRight(PAD);
		table.add(weather).width(widthVal).height(heightVal).padRight(PAD);
		table.add(political).width(widthVal).height(heightVal).padRight(PAD);
		
		group.addActor(table);
	}

	protected void togglePoliticalSystem() {
		getWS().togglePoliticalSystem();
	}

	protected void toggleWeatherSystem() {
		getWS().toggleWeatherSystem();
	}

	protected void toggleGHGSystem() {
		getWS().toggleGHGSystem();
	}

	private WorldSimulator getWS() {
		return (WorldSimulator) getController();
	}

	@Override
	public void layout(float x, float y, float width, float height) {
		table.pack();
		table.invalidate();
		table.validate();
	}

	@Override
	public void update(Model model) {
		
	}

	@Override
	public void dispose() {
		
	}

}
