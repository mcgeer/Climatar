package game.climatar;

import game.climatar.map.Nation;

public class GameState2 {
    //Nation the Player Controls
    private Nation player;

    //World State Variables
    private int ghgLevel = 50;
    private double politicalLevel = 50;
    private double percipicationAverage = 50;
    private double temperatureAverage = 50;


    /**
     * Create a new Game state with the player Nation
     * @param nation
     */
    public GameState2(Nation nation){
        player = nation;
    }

    /**
     * Return the player nation
     * @return
     */
    public Nation getPlayer() {
        return player;
    }


    //UPDATE FUNCTIONS

    /**
     * Update the state of the total GHGs in the world
     * @param update Addition for GHGLevel
     */
    public void updateWorldGHG(int update) {
        ghgLevel += update;
    }

    /**
     * Update Average Political Stance
     * @param update New Political stance
     */
    public void updateWorldPlayerPolitics(double update) {
        politicalLevel = update;
    }

    /**
     * Update Average Precipitation in the world
     * @param update New Average Precipitation
     */
    public void updatePrecipitationAverage(double update){
        percipicationAverage = update;
    }

    /**
     * Update Average Temperature in the world
     * @param update New Average Temperature
     */
    public void updateTemperatureAverage(double update){
        temperatureAverage = update;
    }

}
