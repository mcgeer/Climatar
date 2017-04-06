package game.climatar.systems.political;
import game.climatar.architecture.Model;
import game.climatar.map.Nation;

/**
 * Political System Model
 */
public class PoliticalSystemModel extends Model {

    enum PoliticalProperty {
        WALLET, DELTA_WALLET ,RELATIONS, DELTA_RELATIONS;

        public String id() {
            return this.name();
        }
    }

    /**
     * Initializes this PoliticalSystem with an initialwallet and relations
     */
    public void init(int initialWallet) {
        set(PoliticalProperty.DELTA_WALLET.id(), 0);
        set(PoliticalProperty.DELTA_RELATIONS.id(), 0.0);
        set(PoliticalProperty.WALLET.id(), initialWallet);
        set(PoliticalProperty.RELATIONS.id(), 75.0);
    }
}
