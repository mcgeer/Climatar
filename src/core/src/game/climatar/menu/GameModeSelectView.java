package game.climatar.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class GameModeSelectView extends Stage {
	
	private MenuScreenController controller;

	public GameModeSelectView(MenuScreenController controller) {
		this.controller = controller;
	}
	
	public GameModeSelectView(Viewport viewport) {
		super(viewport);
		
		float width = viewport.getWorldWidth();
		float height = viewport.getWorldHeight();
		
		VisTextButton overlordModeButton = new VisTextButton("Overlord", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				controller.overlordMode();
			}
		});
		
		VisTextButton survivalModeButton = new VisTextButton("Survival", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				controller.survivalMode();
			}
		});
		
		VisTable table = new VisTable();
		
		table.addActor(survivalModeButton);
		table.addActor(overlordModeButton);
		
		addActor(table);
		
		table.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	}
	
}
