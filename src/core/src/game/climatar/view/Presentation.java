package game.climatar.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public abstract class Presentation {
	
	public Group asActor() {
		return this.group;
	}

	private static final float DEFAULT_FADE_DURATION = 0.2f;
	private static final float DEFAULT_HUD_SCALE = 1f;
	
	private float fadeDuration = DEFAULT_FADE_DURATION;
	private float hudScale = DEFAULT_HUD_SCALE;
	
	private Rectangle f;

	private Group group;

	/** Layout presentation! */
	public abstract void layout(float x, float y, float widht, float height);
	
	/** Update presentation values. */
	public abstract void update();
	
	/** Dispose all textures/resources used. */
	public abstract void dispose();
	
	public void addTo(View view) {
		group = new Group();
		hide(false); // hide by default
		
		build(group);
		
		Rectangle f = getFrame();
		
		layout(f.x, f.y, f.width, f.height);
		
		view.getStage().addActor(group);
	}
	
	public abstract void build(Group group);
		
	public float getHudScale() {
		return hudScale;
	}
	
	public void setHudScale(float hudScale) {
		this.hudScale = hudScale;
	}
	
	public float getFadeDuration() {
		return this.fadeDuration;
	}

	public void setFadeDuration(float fadeDuration) {
		this.fadeDuration = fadeDuration;
	}
	
	public void setFrame(int x, int y, int width, int height) {
		if(group == null) return;
		
		group.setPosition(x, y);
		group.setSize(width, height);
		
		Rectangle f = getFrame();
		
		layout(f.x, f.y, f.width, f.height);
	}
	
	public Rectangle getFrame() {
		if(f == null) {
			f = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		} 

		return this.f;
	}

	public void hide(boolean animate) {
		if(animate) {
			hide();
		} else {
			group.setVisible(false);
		}
	}
	
	public void show(boolean animate) {
		if(animate) {
			show();
		} else {
			group.setVisible(true);
		}
	}
	
	public void hide() {
		if(group == null) return;
		
		group.addAction(Actions.sequence(Actions.fadeOut(fadeDuration), Actions.hide()));
	}

	public void show() {
		if(group == null) return;
		
		group.addAction(Actions.sequence(Actions.show(), Actions.fadeIn(fadeDuration)));
	}

}
