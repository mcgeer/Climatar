package game.climatar.architecture;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public abstract class ControllerManager implements ApplicationListener {
	private Color backgroundColour = Color.BLACK;

	private List<Controller> controllers = new ArrayList<Controller>();
	private Set<Controller> viewControllers = new HashSet<Controller>();
	private List<Controller> viewControllersBeingRemoved = new ArrayList<Controller>();

	private static Stage stage;
	private InputMultiplexer inputMultiplexer;
	private boolean globalPause = false;

	protected void initialize(ControllerManager manager) {
		if (inputMultiplexer != null)
			throw new RuntimeException("Tried to initialize " + getClass().getSimpleName() + " twice!");
		inputMultiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);

		Class<? extends ControllerManager> entryClass = manager.getClass();

		for (Field field : entryClass.getDeclaredFields()) {
			if (Controller.class.isAssignableFrom(field.getType())) {
				try {
					Controller controller = (Controller) field.getType().newInstance();

					controller.manager = manager;

					field.setAccessible(true);
					field.set(manager, controller);
					field.setAccessible(false);

					initializeControllerControllers(controller);
					initializeControllerModel(controller);
					initializeControllerViews(controller);

					controllers.add(controller);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// all controllers, views, models are initialized!
		for (Controller c : controllers) {
			c.init();
		}
	}

	public void addViewController(Controller controller) {
		if (viewControllers.contains(controller)) {
			return;
		}

		viewControllers.add(controller);
		inputMultiplexer.addProcessor(controller.getStage());

		controller.layoutViews();
		controller.resizeViews(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void removeViewController(Controller controller) {
		if (!viewControllers.contains(controller)) {
			return;
		}

		inputMultiplexer.removeProcessor(controller.getStage());
		controller.hideView();
		viewControllersBeingRemoved.add(controller);
	}

	public Color getBackgroundColor() {
		return this.backgroundColour;
	}

	public void setBackgroundColor(Color colour) {
		if (colour != null) {
			this.backgroundColour = colour;
		}
	}

	public static void delay(float seconds, Runnable runnable) {
		if (stage == null)
			stage = new Stage();
		stage.addAction(Actions.sequence(Actions.delay(seconds), Actions.run(runnable)));
	}

	@Override
	public void render() {
		if (!globalPause) {
			if (stage == null)
				stage = new Stage();
			stage.act();

			Gdx.gl.glClearColor(backgroundColour.r, backgroundColour.g, backgroundColour.b, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

			Iterator<Controller> iterable = viewControllersBeingRemoved.iterator();
			while (iterable.hasNext()) {
				Controller controller = iterable.next();

				if (!controller.hasViewsWhichAreRendering()) {
					iterable.remove();
					viewControllers.remove(controller);
				}
			}
			for (Controller c : viewControllers) {
				c.renderViews();
			}

			nextTick();
		}
	}

	private void nextTick() {
		for (Controller c : controllers) {
			c.nextTick();
		}
	}

	@Override
	public void dispose() {
		for (Controller c : viewControllers) {
			c.disposeView();
		}
	}

	@Override
	public void resize(int width, int height) {
		for (Controller controller : viewControllers) {
			controller.resizeViews(width, height);
		}
	}

	// TODO: intializeControllerXYZ methods can probably be abstracted into one
	// big method calling three smaller methods

	private void initializeControllerModel(Controller controller)
			throws InstantiationException, IllegalAccessException {
		Class<? extends Controller> controllerClass = controller.getClass();

		Model model;

		SetModel annotation = controllerClass.getAnnotation(SetModel.class);
		if (annotation != null) {
			Class<? extends Model> modelClass = annotation.value();
			model = modelClass.newInstance();

		} else {
			model = new Model();
		}

		controller.model = model;
	}

	private void initializeControllerControllers(Controller controller)
			throws InstantiationException, IllegalAccessException {
		Class<? extends Controller> controllerClass = controller.getClass();

		for (Field field : controllerClass.getDeclaredFields()) {
			Class<?> fieldType = field.getType();

			if (Controller.class.isAssignableFrom(fieldType)) {
				Controller childController = (Controller) fieldType.newInstance();

				childController.setParentController(controller);

				initializeControllerControllers(childController);
				initializeControllerModel(childController);
				initializeControllerViews(childController);

				field.setAccessible(true);
				field.set(controller, childController);
				field.setAccessible(false);

				controller.addChildController(childController);
			}
		}
	}

	private void initializeControllerViews(Controller controller)
			throws InstantiationException, IllegalAccessException {
		Class<? extends Controller> controllerClass = controller.getClass();

		for (Field field : controllerClass.getDeclaredFields()) {
			Class<?> fieldType = field.getType();

			if (View.class.isAssignableFrom(fieldType)) {
				View view = (View) fieldType.newInstance();

				field.setAccessible(true);
				field.set(controller, view);
				field.setAccessible(false);

				// The next line is handled by View#setController()
				// controller.addView(view);

				AllowController annotation = fieldType.getAnnotation(AllowController.class);
				if (annotation != null) {

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
		globalPause = true;
	}

	@Override
	public void resume() {
		// unused for now
		globalPause = false;
	}

}
