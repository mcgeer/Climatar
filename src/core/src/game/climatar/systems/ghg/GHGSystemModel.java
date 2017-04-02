package game.climatar.systems.ghg;

/**
 * Holds information on GHG Emissions for a Region
 */
public class GHGSystemModel {
    //GHG Emissions by given country in
    private int GHGEmissionsPerUpdate;

    /**
     * Generate a new GHGSystemModel with an initial emission level of initialEmissions
     */
    public GHGSystemModel(int initialEmissions){
        GHGEmissionsPerUpdate = initialEmissions;
    }

    /**
     * Returns the current level of GHG being added per update
     * @return Current level of emissions for the region
     */
    public int getGHGEmissionsPerUpdate(){
        return GHGEmissionsPerUpdate;
    }

    /**
     * Apply a change to emissions per update
     * @param deltaGHGEmissions Change being applied to the emissions per update
     */
    public void updateGHGPerUpdate(int deltaGHGEmissions){
        GHGEmissionsPerUpdate += deltaGHGEmissions;
    }

    /**
     * Update implies that a phase has passed, update world state above
     * @return How much GHG emissions are being contributed by this phase
     */
    public int Update(){
        return GHGEmissionsPerUpdate;
    }
}
