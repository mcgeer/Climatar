package game.climatar.systems.ghg;

import game.climatar.architecture.Model;

/**
 * Holds information on GHG Emissions for a Region
 */
public class GHGSystemModel extends Model {
	enum GHGProperty {
		DELTA_EMISSIONS, EMISSIONS_PER_UPDATE, TOTAL_EMISSIONS_PER_UPDATE;
		
		public String id() {
			return this.name();
		}
	}
	
    /**
     * Initializes this GHGSystemModel with an initial emission level of initialEmissions
     */
    public void init(int initialEmissions) {
    	set(GHGProperty.EMISSIONS_PER_UPDATE.id(), initialEmissions);
    }

}
