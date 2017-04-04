package game.climatar.systems.ghg;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.AllowModel;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;
import game.climatar.play.PlayController;

@AllowModel(GHGSystemModel.class)
@AllowController({PlayController.class})
public class GHGSystemView extends View {

	// components
	private VisTable table;
	private VisLabel GHGEmissionsPerUpdate;
	private VisLabel GHGEmissionsPerUpdateValue;
	
	@Override
	public void build(Group group) {
		table = new VisTable();
		
		GHGEmissionsPerUpdate = new VisLabel("GHG Emissions: ");
		GHGEmissionsPerUpdateValue = new VisLabel("");
		
		Value widthVal = new Value() {
			@Override
			public float get(Actor context) {
				return getFrame().width + 20;
			}
		};
		
		table.add(GHGEmissionsPerUpdate).width(widthVal);
		table.add(GHGEmissionsPerUpdateValue).width(widthVal);
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
		
	}

	@Override
	public void dispose() {
		
	}

}
