package game.climatar.systems.political;

import java.util.HashMap;

import game.climatar.architecture.Model;
import game.climatar.map.Nation;

/**
 * Political System Model
 */
public class PoliticalSystemModel extends Model {

    enum PoliticalProperty {
        WALLET, DELTA_WALLET ,RELATIONS, DELTA_RELATIONS, NATION;

        public String id() {
            return this.name();
        }
    }

    /**
     * Initializes this GHGSystemModel with an initial emission level of initialEmissions
     */
    public void init(Nation n, int initialWallet) {
        set(PoliticalProperty.DELTA_WALLET.id(), initialWallet);
        set(PoliticalProperty.DELTA_RELATIONS.id(), 75.0);
        set(PoliticalProperty.WALLET.id(), initialWallet);
        set(PoliticalProperty.RELATIONS.id(), 75.0);
        set(PoliticalProperty.NATION.id(), n);
    }


}
