package game.climatar.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.View;

@AllowController(TitleController.class)
public class LoadView extends View {
	
	// components
	private VisTable table;
	private VisTextButton slot1;
	private VisTextButton slot2;
	private VisTextButton slot3;
	private VisTextButton back;
	
	@Override
	public void layout(float x, float y, float width, float height) {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void build(Group group) {
		back = new VisTextButton("Back", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				((TitleController) getController()).openTitleView();
			}
		});
		
		table = new VisTable();
		
		table.add(back);
	}

}
