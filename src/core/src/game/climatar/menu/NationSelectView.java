package game.climatar.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;
import game.climatar.map.Nation;

@AllowController(TitleController.class)
public class NationSelectView extends View {

	private VisTextButton[] nationButtons;
	private VisTextButton backButton;
	private VisTable table;

	@Override
	public void build(Group group) {
		int i = 0;
		nationButtons = new VisTextButton[Nation.values().length];
		for(Nation n : Nation.values()) {
			final Nation playerNation = n;
			nationButtons[i] = new VisTextButton(n.name() + " nation", new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					((TitleController) getController()).newGame(playerNation);
				}
			});
					
			i++;
		}
		
		backButton = new VisTextButton("Back", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				((TitleController) getController()).openTitleView();
			}
		});

		Value heightVal = new Value() {
			@Override
			public float get(Actor context) {
				return getFrame().height / 8f;
			}
		};

		Value widthVal = new Value() {
			@Override
			public float get(Actor context) {
				return getFrame().width - 2 * 10f;
			}
		};

		table = new VisTable();
		for(VisTextButton button : nationButtons) {
			table.add(button).pad(0, 10f, 10f, 10f).width(widthVal).height(heightVal).row();
		}
		table.add(backButton).pad(0, 10f, 10f, 10f).width(widthVal).height(heightVal).row();

		group.addActor(table);
	}

	@Override
	public void layout(float x, float y, float width, float height) {
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
		
	}

}
