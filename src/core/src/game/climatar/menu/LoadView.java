package game.climatar.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

import game.climatar.architecture.SetController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;

@SetController(TitleController.class)
public class LoadView extends View {

	// components
	private VisTable table;
	private VisTextButton[] saveButtons;
	private VisTextButton back;

	@Override
	public void build(Group group) {
		Value heightVal = new Value() {
			@Override
			public float get(Actor context) {
				return getFrame().height / 8f;
			}
		};

		Value widthVal = new Value() {
			@Override
			public float get(Actor context) {
				return getFrame().width - 2 * 10f;
			}
		};
		
		int saveSlots = ((TitleController) getController()).getNumberOfSaveSlots();
		saveButtons = new VisTextButton[saveSlots];
		for(int i = 0; i < saveSlots; i++) {
			final int slotIndex = i + 1;
			saveButtons[i] = new VisTextButton(getSaveButtonText(i, false), new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					((TitleController) getController()).loadState(slotIndex);	
				}
			});
		}
		
		back = new VisTextButton("Back", new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				((TitleController) getController()).openTitleView();
			}
		});

		table = new VisTable();
		for(int i = 0; i < saveButtons.length; i++) {
			table.add(saveButtons[i]).pad(0, 10f, 10f, 10f).width(widthVal).height(heightVal).row();
		}
		table.add(back).pad(0, 10f, 10f, 10f).width(widthVal).height(heightVal).row();

		group.addActor(table);
	}

	private String getSaveButtonText(int slotNumber, boolean hasSaveData) {
		if(!hasSaveData) return "(No save data)";
		else return "Slot " + slotNumber;
	}

	@Override
	public void layout(float x, float y, float width, float height) {
		table.pack();
		table.invalidate();
		table.validate();
		table.layout();
	}

	@Override
	public void update(Model model) {
		TitleController titleController = (TitleController) getController();
		
		for(int i = 0; i < saveButtons.length; i++) {
			int slotIndex = i + 1;
			
			boolean saveDataAvailable = (titleController.isGameSaved(slotIndex));
			saveButtons[i].setDisabled(!saveDataAvailable);
			saveButtons[i].setText(getSaveButtonText(slotIndex, saveDataAvailable));
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
