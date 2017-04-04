package game.climatar.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;

@AllowController(TitleController.class)
public final class TitleView extends View {
	// constants
	private static final String TITLE_TEXT = "Climatar";

	// components
	private VisTextButton startGameButton;
	private VisTextButton loadGameButton;
	private VisTable buttonsTable;
	private VisLabel titleLabel;

	private BitmapFont titleFont;

	private float getTitleScale() {
		float screenWidth = getFrame().width;
		
		GlyphLayout layout = new GlyphLayout(titleFont, TITLE_TEXT);
		float titleWidth = layout.width;
		
		float scale = screenWidth / (titleWidth);
		return scale;
	}

	@Override
	public void build(Group group) {
		titleFont = new BitmapFont(Gdx.files.internal("fonts/title-font.fnt"),
				Gdx.files.internal("fonts/title-font.png"), false);

		LabelStyle titleStyle = new LabelStyle(titleFont, Color.WHITE);
		
		titleLabel = new VisLabel(TITLE_TEXT, titleStyle);
		titleLabel.setScale(getTitleScale());

		startGameButton = new VisTextButton("Play", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				((TitleController) getController()).openGameModeSelectView();
			}
		});
		startGameButton.getLabel().setFontScale(getHudScale());

		loadGameButton = new VisTextButton("Load", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				((TitleController) getController()).openLoadViewScreen();
			}
		});
		loadGameButton.getLabel().setFontScale(getHudScale());

		buttonsTable = new VisTable();

		Value heightVal = new Value() {
			@Override
			public float get(Actor context) {
				return getFrame().height / 8;
			}
		};

		Value widthVal = new Value() {
			@Override
			public float get(Actor context) {
				return getFrame().width - 2 * 10f;
			}
		};
		
		buttonsTable.add(startGameButton).pad(0, 10f, 10f, 10f).height(heightVal).width(widthVal).row();
		buttonsTable.add(loadGameButton).pad(0, 10f, 10f, 10f).height(heightVal).width(widthVal).row();
		buttonsTable.pack();

		group.addActor(buttonsTable);
		group.addActor(titleLabel);
	}

	@Override
	public void update(Model model) {
		// no model to update the view with (title screen doens't have a model)
	}

	@Override
	public void layout(float x, float y, float width, float height) {
		buttonsTable.pack();
		buttonsTable.invalidate();
		buttonsTable.validate();
		buttonsTable.layout();
		
		titleLabel.setFontScale(getTitleScale());
		titleLabel.setSize(width, height/8);
		titleLabel.setPosition(width / 2f, 2 * height / 3, Align.center);
	}

	@Override
	public void dispose() {
		titleFont.dispose();
	}

}
