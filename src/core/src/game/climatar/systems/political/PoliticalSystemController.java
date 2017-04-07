package game.climatar.systems.political;

import com.badlogic.gdx.Gdx;

import game.climatar.architecture.Controller;
import game.climatar.architecture.SetModel;
import game.climatar.systems.political.PoliticalSystemModel.PoliticalProperty;

/**
 * Control all political systems
 */
@SetModel(PoliticalSystemModel.class)
public class PoliticalSystemController extends Controller {

	private PoliticalSystemView politicalSystemView;

	@Override
	protected void initialize() {
		getModel().set(PoliticalProperty.RELATIONS.id(), 0);
		getModel().set(PoliticalProperty.WALLET.id(), 0);
	}

	@Override
	protected void layoutView() {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		politicalSystemView.setFrame(0, 0, width / 2, height / 2);

		showView(politicalSystemView);
	}

	@Override
	protected void tick() {
		// Update the Wallet
		int wallet = (Integer) getModel().get(PoliticalProperty.WALLET.id());
		wallet += (Integer) getModel().get(PoliticalProperty.DELTA_WALLET.id());

		getModel().set(PoliticalProperty.WALLET.id(), wallet);

		// Update the Relations
		int relations = (Integer) getModel().get(PoliticalProperty.RELATIONS.id());
		relations += (Integer) getModel().get(PoliticalProperty.DELTA_RELATIONS.id());

		getModel().set(PoliticalProperty.RELATIONS.id(), relations);

		// Reset Deltas For this class
		getModel().set(PoliticalProperty.DELTA_RELATIONS.id(), 0.0);
		getModel().set(PoliticalProperty.DELTA_WALLET.id(), 0);
	}

	/**
	 * Set a change to be in effect on next update for a nations emissions
	 * 
	 * @param deltaWallet
	 *            Change being applied int in [-10, 10] - {0}
	 */
	public void setDeltaWallet(int deltaWallet) {
		getModel().set(PoliticalProperty.DELTA_WALLET.id(), deltaWallet);
	}

	/**
	 * Set a change to be in effect next update for a nations public relations
	 * 
	 * @param deltaRelations
	 *            Changeto be applied to relations
	 */
	public void setDeltaRelations(int deltaRelations) {
		getModel().set(PoliticalProperty.DELTA_RELATIONS.id(), deltaRelations);
	}

	/**
	 * Get the Wallet amount
	 */
	public int getWallet() {
		return (Integer) getModel().get(PoliticalProperty.WALLET.id());
	}

	/**
	 * Get the Relations level
	 */
	public double getRelations() {
		return (Double) getModel().get(PoliticalProperty.RELATIONS.id());
	}
}
