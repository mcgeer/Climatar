package game.climatar.architecture;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import game.climatar.ApplicationController;

public abstract class Controller {

	private boolean isActive;
	Model model;
	private Controller parentController;
	ControllerManager manager;
	private Stage stage;
	private List<View> views = new ArrayList<View>();
	private List<Controller> children = new ArrayList<Controller>();

	public Controller getParentController() {
		return this.parentController;
	}
	
	void setParentController(Controller parentController) {
		this.parentController = parentController;
	}
	
	public boolean isActive() {
		return this.isActive;
	}
	
	public void setActive(boolean active) {
		this.isActive = active;
	}

	void init() {
		initialize();

		for (Controller child : children) {
			child.init();
		}
	}

	void layoutViews() {
		layoutView();

		for (Controller c : children) {
			c.layoutViews();
		}
	}

	void renderViews() {
		if(isActive) {
			renderView();
			
			for (Controller c : children) {
				c.renderViews();
			}
		}
	}

	protected void nextTick() {
		if(isActive) {
			tick();

			for (Controller c : children) {
				c.nextTick();
			}
		}
	}

	void resizeViews(int width, int height) {
		resizeView(width, height);

		for (Controller child : children) {
			child.resizeViews(width, height);
		}
	}

	protected abstract void layoutView();

	protected abstract void tick();

	/**
	 * Optional constructor-like method which can be overridden to set model
	 * values or view settings.
	 */
	protected void initialize() {
	}

	protected void renderView() {
		for (View view : views) {
			view.update(model);
		}
		
		getStage().act(Gdx.graphics.getDeltaTime());
		getStage().getViewport().apply();
		getStage().draw();

		for (Controller child : children) {
			child.renderView();
		}
	}

	protected void resizeView(int width, int height) {
		getStage().getViewport().update(width, height, true);

		for (View view : views) {
			view.layout();
		}

		for (Controller child : children) {
			child.resizeView(width, height);
		}
	}

	public boolean hasViewsWhichAreRendering() {
		for (View v : views) {
			if (!v.isHidden())
				return true;
		}

		for (Controller child : children) {
			if (child.hasViewsWhichAreRendering())
				return true;
		}

		return false;
	}

	protected ControllerManager getControllerManager() {
		return this.manager;
	}

	protected Stage getStage() {
		if (stage == null)
			stage = new Stage(new ScreenViewport(new OrthographicCamera(ApplicationController.WIDTH, ApplicationController.HEIGHT)));

		return stage;
	}

	public Model getModel() {
		return this.model;
	}

	protected void addView(View view) {
		if (!views.contains(view)) {
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
	 *            These views should have been added previously. Views that
	 *            should be shown. All others are hidden.
	 */
	protected void showView(boolean animate, View... views) {
		for (View view : this.views) {
			boolean shouldHide = true;

			if(views != null) {
				for (int i = 0; i < views.length; i++) {
					if (views[i] == view) {
						shouldHide = false;
					}
				}
			}

			if (shouldHide) {
				view.hide(animate);
			} else {
				view.show(animate);
			}
		}
	}

	protected void hideView() {
		for (View view : this.views) {
			view.hide();
		}

		for (Controller child : children) {
			child.hideView();
		}
	}

	protected void disposeView() {
		for (View v : views) {
			v.dispose();
		}

		for (Controller child : children) {
			child.disposeView();
		}
	}

	public void addChildController(Controller childController) {
		this.children.add(childController);
	}

}
