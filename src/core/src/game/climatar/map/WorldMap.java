package game.climatar.map;

import java.util.List;

/**
 * holds metadata pertaining to the world map
 */
public class WorldMap {
	// terrain map 0 ... 8 <=> ice ... mountains
	public List<List<Integer>> terrain;
	// nation map 0 ... 3 <=> fire ... air
	public List<List<Nation>> nations;

	WorldMap(List<List<Integer>> terrain, List<List<Nation>> nations) {
		this.terrain = terrain;
		this.nations = nations;
	}
}
