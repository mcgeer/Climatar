package game.climatar.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.building.utilities.Alignment;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import game.climatar.view.Presentation;

public final class TitlePresentation extends Presentation {
	
	// controller
	private MenuScreenController controller;
	
	// components
	private VisTextButton startGameButton;
	private VisTextButton loadGameButton;
	private VisTable buttonsTable;

	private VisLabel titleLabel;
	
	private static final String TITLE_TEXT = "Climatar";

	private BitmapFont titleFont;
	
	public TitlePresentation(MenuScreenController controller, Stage stage, float hudScale, float fadeDuration) {
		super(hudScale, fadeDuration);
		
		this.controller = controller;
	}
	
	private float cellWidth() {
		float cellWidth = Gdx.graphics.getWidth();
		
		return cellWidth;
	}
	
	private float cellHeight() {
		float cellHeight = Gdx.graphics.getHeight()/8f;
		
		return cellHeight;
	}
	
	private float getTitleScale() {
		return getHudScale() / 4;
	}
	
	@Override
	public void build(Group group) {
		titleFont = new BitmapFont(Gdx.files.internal("fonts/title-font.fnt"), Gdx.files.internal("fonts/title-font.png"), false);
		
		LabelStyle labelStyle = VisUI.getSkin().get(LabelStyle.class);
		labelStyle.font = titleFont;

		titleLabel = new VisLabel(TITLE_TEXT);
		titleLabel.setScale(getTitleScale());
		
		startGameButton = new VisTextButton("Play", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				controller.openGameModeSelectView();
			}
		});
		startGameButton.getLabel().setFontScale(getHudScale());
		
		loadGameButton = new VisTextButton("Load", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				controller.openLoadViewScreen();
			}
		});
		loadGameButton.getLabel().setFontScale(getHudScale());
		
		buttonsTable = new VisTable();
		
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
		
		buttonsTable.add(startGameButton).pad(0, 10f, 10f, 10f).height(heightVal).width(widthVal).row();
		buttonsTable.add(loadGameButton).pad(0, 10f, 10f, 10f).height(heightVal).width(widthVal).row();
		buttonsTable.pack();
		
		group.addActor(buttonsTable);
		group.addActor(titleLabel);
		
		resize();
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void resize() {
		buttonsTable.pack();
		buttonsTable.invalidate();
		buttonsTable.validate();
		
		titleLabel.setPosition(Gdx.graphics.getWidth()/2f, 2*Gdx.graphics.getHeight()/3, Align.center);
	}

}
