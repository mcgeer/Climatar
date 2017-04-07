package game.climatar.systems.ghg;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
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

		//1: GHG
		//2: Rel
		//3: Wallet
		//4: Weather
		//5: Temperature



		table.add(GHGEmissionsPerUpdate).maxWidth(widthVal).height(heightVal).align(Align.left);
		table.add(GHGEmissionsPerUpdateValue).maxWidth(widthVal).fillX().expandX().width(widthVal).height(heightVal).align(Align.right);
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
		GHGEmissionsPerUpdateValue.setText(epu + " EPU");
	}

	@Override
	public void dispose() {
		
	}

}
