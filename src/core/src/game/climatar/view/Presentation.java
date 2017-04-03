package game.climatar.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public abstract class Presentation {
	private float fadeDuration;
	private float hudScale;

	private Group group;

	public Presentation(float hudScale, float fadeDuration) {
		this.fadeDuration = fadeDuration;
		this.hudScale = hudScale;
	}
	
	public void addTo(Stage stage) {
		group = new Group();

		build(group);
		stage.addActor(group);
	}
	
	public abstract void build(Group group);
	
	public abstract void resize();
	
	public abstract void update();
	
	public float getHudScale() {
		return hudScale;
	}
	
	public void setHudScale(float hudScale) {
		this.hudScale = hudScale;
	}

	public void setPosition(int x, int y, int width, int height) {
		if(group == null) return;
		
		group.setPosition(x, y);
		group.setSize(width, height);
		
		resize();
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
