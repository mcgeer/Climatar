package game.climatar.architecture;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public abstract class Main implements ApplicationListener {
	private Color backgroundColour = Color.BLACK;

	private List<Controller> controllers = new ArrayList<>();
	private Controller viewController;

	protected void initialize(Main entryPoint) {
		Class<? extends Main> entryClass = entryPoint.getClass();

		for (Field field : entryClass.getDeclaredFields()) {
			if (Controller.class.isAssignableFrom(field.getType())) {
				try {
					Controller controller = (Controller) field.getType().newInstance();
					controller.model = new Model();

					field.setAccessible(true);
					field.set(entryPoint, controller);
					field.setAccessible(false);

					initializeController(controller);

					controllers.add(controller);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Sets the current screen. {@link Screen#hide()} is called on any old
	 * screen, and {@link Screen#show()} is called on the new screen, if any.
	 * 
	 * @param screen
	 *            may be {@code null}
	 */
	public void setViewController(Controller controller) {
		if (this.viewController != null)
			this.viewController.hideView();
		this.viewController = controller;
		if (this.viewController != null) {
			this.viewController.layoutView();
			Gdx.input.setInputProcessor(controller.getStage());
			this.viewController.resizeView(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	public Color getBackgroundColor() {
		return this.backgroundColour;
	}

	public void setBackgroundColor(Color colour) {
		if (colour != null) {
			this.backgroundColour = colour;
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(backgroundColour.r, backgroundColour.g, backgroundColour.b, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		if (viewController != null)
			viewController.renderView(Gdx.graphics.getDeltaTime());

		nextTick();
	}

	private void nextTick() {
		for (Controller c : controllers) {
			c.tick();
		}
	}

	@Override
	public void dispose() {
		if (viewController != null)
			viewController.disposeView();
	}

	@Override
	public void resize(int width, int height) {
		if (viewController != null)
			viewController.resizeView(width, height);
	}

	private void initializeController(Controller controller) throws InstantiationException, IllegalAccessException {
		Class<? extends Controller> controllerClass = controller.getClass();

		for (Field field : controllerClass.getDeclaredFields()) {
			Class<?> fieldType = field.getType();

			if (View.class.isAssignableFrom(fieldType)) {
				View view = (View) fieldType.newInstance();

				field.setAccessible(true);
				field.set(controller, view);
				field.setAccessible(false);

				// Handled by View#setController()
				// controller.addView(view);

				if (fieldType.getAnnotation(AllowController.class) != null) {
					AllowController annotation = fieldType.getAnnotation(AllowController.class);

					boolean valid = false;

					for (Class<? extends Controller> c : annotation.value()) {
						if (c.isInstance(controller)) {
							view.setController(controller);
							view.initializeView();
							valid = true;
						}
					}

					if (!valid) {
						throw new RuntimeException(
								fieldType.getSimpleName() + " is not allowed in " + controllerClass.getSimpleName());
					}
				}
			}
		}
	}

	@Override
	public void pause() {
		// unused for now
	}

	@Override
	public void resume() {
		// unused for now
	}

}
