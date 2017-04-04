package game.climatar.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import game.climatar.view.Presentation;

public class GameModeSelectPresentation extends Presentation {
	// constants
	/* none */
	
	// controller
	private MenuScreenController controller;
	
	// components
	private VisTextButton overlordModeButton;
	private VisTextButton survivalModeButton;

	private VisTable table;
	
	public GameModeSelectPresentation(MenuScreenController controller) {
		this.controller = controller;
	}
	
	private float cellWidth() {
		float cellWidth = getFrame().width;
		
		return cellWidth;
	}
	
	private float cellHeight() {
		float cellHeight = getFrame().height/8f;
		
		return cellHeight;
	}
	
	@Override
	public void build(Group group) {
		overlordModeButton = new VisTextButton("Overlord", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				controller.overlordMode();
			}
		});
		
		survivalModeButton = new VisTextButton("Survival", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				controller.survivalMode();
			}
		});
		
		Value heightVal = new Value() {
			@Override
			public float get(Actor context) {
				return cellHeight();
			}
		};
		
		Value widthVal = new Value() {
			@Override
			public float get(Actor context) {
				return cellWidth() - 2 * 10f;
			}
		};
		
		table = new VisTable();
		table.add(survivalModeButton).pad(0, 10f, 10f, 10f).width(widthVal).height(heightVal).row();
		table.add(overlordModeButton).pad(0, 10f, 10f, 10f).width(widthVal).height(heightVal).row();
		
		table.addActor(survivalModeButton);
		table.addActor(overlordModeButton);
		
		group.addActor(table);
	}

	@Override
	public void layout(float x, float y, float width, float height) {
		table.setSize(width, height);
		table.setPosition(x, y);
		table.pack();
		table.invalidate();
		table.validate();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void dispose() {
		
	}
	
}
