package game.climatar.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;

import game.climatar.ApplicationController;
import game.climatar.GameState;
import game.climatar.architecture.Controller;

public class TitleController extends Controller {
	private TitleView titleView;
	private GameModeSelectView gameModeSelectView;
	private LoadView loadView;

	@Override
	protected void layoutView() {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		titleView.setFrame(width / 2, height / 2, width / 2, height / 2);
		gameModeSelectView.setFrame(0, height / 2, width / 2, height / 2);
		loadView.setFrame(width / 2, 0, width / 2, height / 2);
		
		showView(titleView);
	}

	@Override
	protected void tick() {

	}

	public void setHudScale(float hudScale) {
		titleView.setHudScale(hudScale);
	}

	public void openTitleView() {
		showView(titleView);
	}

	public void openGameModeSelectView() {
		showView(gameModeSelectView);
	}

	public void openLoadViewScreen() {
		showView(loadView);
	}

	public void overlordMode() {
		// stub; no time to implement this mode yet
	}

	public void survivalMode() {
		((ApplicationController) getControllerManager()).play();
	}

	/**
	 * Saves to a save slot, overwriting any save that existed previously.
	 * 
	 * @param slot
	 *            Slot to save to.
	 * @param state
	 *            GameStateTEMP to save.
	 */
	public void saveState(int slot, GameState state) {
		String saveFileName = getSaveFileName(slot);
		FileHandle handle = Gdx.files.internal(saveFileName);

		String gameStateJSON = new Gson().toJson(state);

		// overwrite!
		handle.writeString(gameStateJSON, false);
	}

	public GameState loadState(int slot) {
		String saveFileName = getSaveFileName(slot);
		FileHandle handle = Gdx.files.internal(saveFileName);

		if (handle.exists()) {
			try {
				String gameStateJSON = handle.readString();

				return new Gson().fromJson(gameStateJSON, GameState.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private String getSaveFileName(int slot) {
		return "save" + slot + ".climatar";
	}

	public int getNumberOfSaveSlots() {
		return 3;
	}

	public boolean isGameSaved(int slotIndex) {
		String saveFileName = getSaveFileName(slotIndex);
		FileHandle handle = Gdx.files.internal(saveFileName);

		if (handle.exists()) {
			try {
				String gameStateJSON = handle.readString();

				return new Gson().fromJson(gameStateJSON, GameState.class) != null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
