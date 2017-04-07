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

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;

@AllowController(WorldSimulator.class)
public class PauseView extends View {

	private VisImageButton pauseButton;
	private Drawable pauseButtonTexture;
	private Drawable backButtonTexture;

	private Value widthVal = new Value() {
		@Override
		public float get(Actor context) {
			return getFrame().getWidth();
		}
	};

	private Value heightVal = new Value() {
		@Override
		public float get(Actor context) {
			return getFrame().getHeight();
		}
	};

	@Override
	public void build(Group group) {
		pauseButtonTexture = new TextureRegionDrawable(new TextureRegion(new Texture("pause.png")));
		backButtonTexture = new TextureRegionDrawable(new TextureRegion(new Texture("back.png")));
		
		pauseButton = new VisImageButton(pauseButtonTexture);
		pauseButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				((WorldSimulator)getController()).toggleOverlayMenu();
			}
		});
		
		pauseButton.setWidth(widthVal.get(pauseButton));
		pauseButton.setHeight(heightVal.get(pauseButton));
		
		group.addActor(pauseButton);
	}

	@Override
	public void layout(float x, float y, float width, float height) {
		pauseButton.setWidth(this.widthVal.get(pauseButton));
		pauseButton.setHeight(this.heightVal.get(pauseButton));
		
	}

	public WorldSimulator getWS() {
		return (WorldSimulator) getController();
	}
	
	@Override
	public void update(Model model) {
		if(getWS().overlayMenuIsShowing) {
			pauseButton.getStyle().imageUp = (backButtonTexture);
		} else {
			pauseButton.getStyle().imageUp = (pauseButtonTexture);
		}
	}

	@Override
	public void dispose() {
		
	}

}
