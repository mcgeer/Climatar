package game.climatar.architecture;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class Controller {

	private Stage stage;
	private List<View> views = new ArrayList<View>();
	
	protected abstract void tick();
	
	protected Stage getStage() {
		if(stage == null) stage = new Stage(new ScreenViewport());
		
		return stage;
	}

	protected void renderView(float delta) {
		getStage().act(delta);
		getStage().draw();
	}
	
	protected void addView(View view) {
		if(!views.contains(view)) {
			views.add(view);
			getStage().addActor(view.get());
		}
	}
	
	protected void removeView(View view) {
		views.remove(view);
		
		getStage().getRoot().removeActor(view.get());
	}
	
	protected void showView(View... views) {
		showView(true, views);
	}
	
	/**
	 * @param views
	 * 	These views should have been added previously. Views that should be shown. All others are hidden.
	 */
	protected void showView(boolean animate, View... views) {
		for(View view : this.views) {
			boolean shouldHide = true;
			
			for(int i = 0; i < views.length; i++) {
				if(views[i] == view) {
					shouldHide = false;		
				}
			}
			
			if(shouldHide) {
				view.hide(animate);
			} else {
				view.show(animate);
			}
		}
	}

	protected void resizeView(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	protected void hideView() {
		
	}

	protected void disposeView() {
		for(View v : views) {
			v.dispose();
		}
	}

	
}
