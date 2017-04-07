package game.climatar.systems.political;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

import game.climatar.architecture.AllowController;
import game.climatar.architecture.Model;
import game.climatar.architecture.View;
import game.climatar.map.Nation;
import game.climatar.systems.ghg.GHGSystemModel;

@AllowController(PoliticalSystemController.class)
public class PoliticalSystemView extends View {

	// components
	private VisTable table;
	private VisLabel relationsLabel;
	private VisLabel relationValueLabel;
	private VisLabel walletLabel;
	private VisLabel walletValueLabel;

	@Override
	public void build(Group group) {
		table = new VisTable();

		relationsLabel = new VisLabel("Popularity: ");
		relationValueLabel = new VisLabel("0%");

		walletLabel = new VisLabel("Wallet: ");
		walletValueLabel = new VisLabel("$0");

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

		table.add(relationsLabel).maxWidth(widthVal).height(heightVal);
		table.add(relationValueLabel).maxWidth(widthVal).fillX().expandX().width(widthVal).height(heightVal);
		table.row();
		table.add(walletLabel).maxWidth(widthVal).height(heightVal);
		table.add(walletValueLabel).maxWidth(widthVal).fillX().expandX().width(widthVal).height(heightVal);
		table.row(); //GHG
		table.add("");
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
		int relations = (Integer) model.get(PoliticalSystemModel.PoliticalProperty.RELATIONS.id());
		int wallet = (Integer) model.get(PoliticalSystemModel.PoliticalProperty.WALLET.id());
		Nation nation = (Nation) model.get(PoliticalSystemModel.PoliticalProperty.NATION.id());
		relationValueLabel.setText(relations + " %Pop");
		walletValueLabel.setText( "$" + wallet);
	}

	@Override
	public void dispose() {

	}
}
