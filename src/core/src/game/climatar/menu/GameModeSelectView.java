package game.climatar.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import game.climatar.architecture.SetController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;

@SetController(TitleController.class)
public class GameModeSelectView extends View {

	// components
	private VisTextButton overlordModeButton;
	private VisTextButton survivalModeButton;
	private VisTextButton backButton;
	private VisTable table;

	private TitleController controller() {
		return (TitleController) getController();
	}

	@Override
	public void build(Group group) {
		overlordModeButton = new VisTextButton("Overlord", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				controller().overlordMode();
			}
		});
		overlordModeButton.setDisabled(true);

		survivalModeButton = new VisTextButton("Survival", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				controller().survivalMode();
			}
		});

		backButton = new VisTextButton("Back", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				controller().openTitleView();
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
		table.add(survivalModeButton).pad(0, 10f, 10f, 10f).width(widthVal).height(heightVal).row();
		table.add(overlordModeButton).pad(0, 10f, 10f, 10f).width(widthVal).height(heightVal).row();
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
