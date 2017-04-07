package game.climatar.map;

import java.util.Arrays;
import java.util.List;

public class tester {
	public static void main(String[] args) {
		WorldMap world = MapGenerator.generateMap(4, 14, 7, 4);
		for (List<Integer> row : world.terrain) {
			System.out.println(row.toString());
		}
		for (List<Nation> row : world.nations) {
			System.out.println(row.toString());
		}
	}
}
