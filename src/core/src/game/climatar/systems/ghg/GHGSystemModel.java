package game.climatar.systems.ghg;

import game.climatar.architecture.Model;
import game.climatar.map.Nation;

/**
 * Holds information on GHG Emissions for a Region
 */
public class GHGSystemModel extends Model {
	enum GHGProperty {
		DELTA_EMISSIONS, EMISSIONS_PER_UPDATE, TOTAL_EMISSIONS_PER_UPDATE, NATION;
		
		public String id() {
			return this.name();
		}
	}
	
    /**
     * Initializes this GHGSystemModel with an initial emission level of initialEmissions
     */
    public void init(Nation nation, int initialEmissions) {
    	set(GHGProperty.NATION.id(), nation);
    	set(GHGProperty.EMISSIONS_PER_UPDATE.id(), initialEmissions);
    }

}
