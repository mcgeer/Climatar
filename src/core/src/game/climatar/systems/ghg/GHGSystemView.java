package game.climatar.systems.ghg;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;
import game.climatar.map.Nation;
import game.climatar.systems.ghg.GHGSystemModel.GHGProperty;

@AllowController({GHGSystemController.class})
public class GHGSystemView extends View {

	// components
	private VisTable table;
	private VisLabel GHGEmissionsPerUpdate;
	private VisLabel GHGEmissionsPerUpdateValue;
	
	@Override
	public void build(Group group) {
		table = new VisTable();
		
		GHGEmissionsPerUpdate = new VisLabel("GHG Emissions: ");
		GHGEmissionsPerUpdateValue = new VisLabel("0/sec");
		
		Value widthVal = new Value() {
			@Override
			public float get(Actor context) {
				return getFrame().width/2 - 20;
			}
		};
		
		Value heightVal = new Value() {
			@Override
			public float get(Actor context) {
				return getFrame().height/8;
			}
		};
		
		table.add(GHGEmissionsPerUpdate).maxWidth(widthVal).height(heightVal);
		table.add(GHGEmissionsPerUpdateValue).maxWidth(widthVal).fillX().expandX().width(widthVal).height(heightVal);
		
		group.addActor(table);
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
		int epu = (Integer) model.get(GHGProperty.EMISSIONS_PER_UPDATE.id());
		Nation nation = (Nation) model.get(GHGProperty.NATION.id());
		GHGEmissionsPerUpdateValue.setText(epu + " " + nation.name() + " EPU");
	}

	@Override
	public void dispose() {
		
	}

}
