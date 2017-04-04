package game.climatar.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.google.gson.Gson;

import game.climatar.GameState;
import game.climatar.architecture.Model;

public class MenuScreenModel extends Model {

	/**
	 * Saves to a save slot, overwriting any save that existed previously.
	 * @param slot Slot to save to.
	 * @param state GameState to save.
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
}
