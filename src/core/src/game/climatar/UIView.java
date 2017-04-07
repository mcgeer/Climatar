package game.climatar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
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
	private Nation selectedNation;

	private Drawable fireTexture;
	private Drawable  waterTexture;
	private Drawable  airTexture;
	private Drawable earthTexture;
	private Drawable backButtonTexture;

	public Nation getSelectedNation() {
		return selectedNation;
	}

	public void setSelectedNation(Nation selectedNation) {
		this.selectedNation = selectedNation;
	}

	@Override
	public void build(Group group) {
		backButtonTexture = new TextureRegionDrawable(new TextureRegion(new Texture("back.png")));
		
		fireTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Nation.FIRE.getImageFileName())));
		waterTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Nation.WATER.getImageFileName())));
		airTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Nation.AIR.getImageFileName())));
		earthTexture = new TextureRegionDrawable(new TextureRegion(new Texture(Nation.EARTH.getImageFileName())));
		
		table = new VisTable();
		group.debug();
		nationButtons = new VisImageButton[Nation.values().length];

		Value width = new Value() {
			@Override
			public float get(Actor context) {
				return getFrame().getWidth();
			}
		};

		Value height = new Value() {
			@Override
			public float get(Actor context) {
				return getFrame().getHeight() / Nation.values().length;
			}
		};

		int i = 0;
		for (Nation n : Nation.values()) {
			if (n == Nation.BLUE_LOTUS)
				continue;
			final Nation nation = n;
			final WorldSimulator sim = (WorldSimulator) getController();

			TextureRegionDrawable drawable = new TextureRegionDrawable(
					new TextureRegion(new Texture(n.getImageFileName())));
			 nationButtons[i] = new VisImageButton(drawable);
			nationButtons[i].setUserObject(n);
			nationButtons[i].addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					sim.openNationView(nation);
				}
			});
			table.add(nationButtons[i]).width(width).height(height).row();
			i++;
		}

		group.addActor(table);
	}

	@Override
	public void layout(float x, float y, float width, float height) {
		table.pack();
		table.invalidate();
		table.validate();
	}

	@Override
	public void update(Model model) {
		for(VisImageButton b : nationButtons) {
			if(b == null) continue;
			if(b.getUserObject() != null) {
				Nation nation = (Nation) b.getUserObject();
				
				if(nation == selectedNation) {
					b.setBackground(getBackButtonDrawable());
				} else {
					b.setBackground(getNationDrawable(nation));
				}
			}
		}
	}
	
	private Drawable getNationDrawable(Nation nation) {
		switch(nation) {
		case AIR:
			return airTexture;
		case EARTH:
			return earthTexture;
		case FIRE:
			return fireTexture;
		case WATER:
			return waterTexture;
		}
		return null;
	}

	private Drawable getBackButtonDrawable() {
		return backButtonTexture;
	}

	@Override
	public void dispose() {
	}

}
