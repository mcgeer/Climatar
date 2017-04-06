package game.climatar;

import game.climatar.architecture.Model;
import game.climatar.map.Nation;

/**
 * Created by riley_000 on 2017-04-06.
 */

public class GameState extends Model {
    public enum WorldProperty {
        NATION, AVG_RELATIONS, AVG_TEMP, AVG_PRECIP, TOTAL_GHG, PLAYING;

        public String id() {
            return this.name();
        }
    }

    /**
     * Initialize World State with Nation n
     */
    public void init(Nation n) {
        set(WorldProperty.NATION.id(), n);
    	set(WorldProperty.PLAYING.id(), true);
        set(WorldProperty.TOTAL_GHG.id(), 0);
        set(WorldProperty.AVG_PRECIP.id(), 0.0);
        set(WorldProperty.AVG_RELATIONS.id(), 0.0);
        set(WorldProperty.AVG_TEMP.id(), 0.0);
    }

}
