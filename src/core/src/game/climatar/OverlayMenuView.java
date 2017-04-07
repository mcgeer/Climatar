package game.climatar;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
	
	private Value sizeVal = new Value() {
		@Override
		public float get(Actor context) {
			return getFrame().width / 3 - PAD * 2;
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
		
		ghg = new VisTextButton("ghg", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				toggleGHGSystem();
			}
		});
		
		weather = new VisTextButton("weather", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				toggleWeatherSystem();
			}
		});
		
		political = new VisTextButton("political", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				togglePoliticalSystem();
			}
		});
		
		table.add(ghg).width(sizeVal).height(heightVal).padLeft(PAD).padRight(PAD);
		table.add(weather).width(sizeVal).height(heightVal).padRight(PAD);
		table.add(political).width(sizeVal).height(heightVal).padRight(PAD);
		
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
