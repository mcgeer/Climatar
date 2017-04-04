package game.climatar.architecture;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class Controller {

	Model model;
	ControllerManager manager;
	private Stage stage;
	private List<View> views = new ArrayList<View>();
	
	protected abstract void layoutView();
	protected abstract void tick();
	
	protected ControllerManager getControllerManager() {
		return this.manager;
	}
	
	protected Stage getStage() {
		if(stage == null) stage = new Stage(new ScreenViewport());
		
		return stage;
	}

	protected void renderView() {
		for(View view : views) {
			view.update(model);
		}
		
		getStage().act(Gdx.graphics.getDeltaTime());
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
		
		for(View view : views) {
			view.layout();
		}
	}
	
	protected void hideView() {
		for(View view : this.views) {
			view.hide();
		}
	}

	protected void disposeView() {
		for(View v : views) {
			v.dispose();
		}
	}
	public boolean hasViewsWhichAreRendering() {
		for(View v : views) {
			if(!v.isHidden()) return true;
		}
		return false;
	}
}
