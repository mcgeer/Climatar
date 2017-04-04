package game.climatar.architecture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public abstract class View {
	
	private static final float DEFAULT_FADE_DURATION = 0.2f;
	private static final float DEFAULT_HUD_SCALE = 1f;
	
	private float fadeDuration = DEFAULT_FADE_DURATION;
	private float hudScale = DEFAULT_HUD_SCALE;
	
	private Controller controller;
	private Rectangle frame;
	private final Group group = new Group();

	public void initializeView() {
		frame = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// build and layout
		build(group);
		layout();
		
		hide(false); // hide by default
	}
	
	public void setController(Controller controller) {
		if(this.controller != null) this.controller.removeView(this);
		
		this.controller = controller;
		this.controller.addView(this);
	}
	
	public Controller getController() {
		return this.controller;
	}
	
	/*
	 * Abstract Methods
	 */
	
	/** Builds the presentation */
	public abstract void build(Group group);
	
	/** Layout presentation! */
	public abstract void layout(float x, float y, float width, float height);
	
	/** Update presentation values. */
	public abstract void update(Model model);
	
	/** Dispose all textures/resources used. */
	public abstract void dispose();
	/*
	 * Default Implementation
	 */
	public void layout() {
		Rectangle frame = getFrame();
		
		float x = frame.x;
		float y = frame.y;
		float width = frame.width;
		float height = frame.height;
		
		group.setPosition(x, y);
		group.setSize(width, height);
		group.debug();

		layout(x, y, width, height);
	}

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
		Rectangle f = getFrame();
		f.set(x, y, width, height);
		
		layout();
	}
	
	public Rectangle getFrame() {
		return frame;
	}
	
	public boolean isHidden() {
		return group.isVisible();
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

	protected Actor get() {
		return group;
	}

}
