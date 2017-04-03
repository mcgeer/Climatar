package game.climatar.map;

public class GameState {

    private Nation player;

    public GameState(Nation nation){
        player = nation;
    }

    public Nation getPlayer() {
        return player;
    }

    public static void UpdateWorldGHG(int update) {
    }

    public static void UpdateWorldPlayerPolitics(double update) {
    }
}
