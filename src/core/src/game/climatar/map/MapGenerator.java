package game.climatar.map;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

abstract class Thresholder<T> {
	abstract T threshold(float x);
}

public class MapGenerator {
	
	private static Thresholder<Integer> terrainThresholder = new Thresholder<Integer>() {
			// thresholds determining cutoffs for different terrain types
			float ice = 0.205f;
			float deepWater = 0.34f;
			float shallowWater = 0.4f;
			float desert = 0.45f;
			float plains = 0.54f;
			float grassland = 0.75f;
			float forest = 0.8f;
			float hills = 0.88f;

			Integer threshold(float x) {
				if (x < ice) return 0;
				else if (x < deepWater) return 1;
				else if (x < shallowWater) return 2;
				else if (x < desert) return 3;
				else if (x < plains) return 4;
				else if (x < grassland) return 5;
				else if (x < forest) return 6;
				else if (x < hills) return 7;
				else return 8;
			}
		};

	private static Thresholder<Nation> nationThresholder = new Thresholder<Nation>() {
			// thresholds determining cutoffs for nations
			float waterNation = 0.23f;
			float internationalWaters = 0.38f;
			float earthOrFireNation = 0.82f;

			Nation threshold(float x) {
				if (x < waterNation) return Nation.WATER;
				else if (x < internationalWaters) return Nation.BLUE_LOTUS;
				// this is pretty sketchy, but we'll reassign some
				// earth nation tiles as fire nation tiles later.
				else if (x < earthOrFireNation) return Nation.EARTH;
				else return Nation.AIR;
			}
		};

	/**
	 * procedurally generates and returns map metadata
	 * @param n map dimensions are based off of 2^n
	 * @param wmult relative width multiplier, using the base calculated with n
	 * @param hmult relative height multiplier, using the base calculated with n
	 * @return WorldMap metadata container
	 */
	public static WorldMap generateMap(int n, 
									   int wmult,
									   int hmult,
									   float smoothness) {
		
		// get noise from fractal noise class
		float[][] noise = MidpointDisplacement.noise(n, wmult, hmult, smoothness);

		// apply weighting for northern and southern water tribes
		for (int row = 0; row < noise.length; row++) {
			float f = (float) Math.sin(Math.PI * (float) row / noise.length);
			for (int col = 0; col < noise[row].length; col++) {
				noise[row][col] = noise[row][col] * f;
			}
		}

		// apply thresholds
		List<List<Integer>> terrain = threshold(noise, terrainThresholder);
		List<List<Nation>> nations = threshold(noise, nationThresholder);

		// we'll patch the nations map here and assign the still
		// undecided earth or fire tiles 
		for (int row = 0; row < nations.size(); row++) {
			for (int col = 0; col <  nations.get(row).size(); col++) {
				// we'll just assign fire to the left 20% of the map
				// and earth to the right for now.
				// still need to assign either earth or fire
				if (nations.get(row).get(col) == Nation.EARTH &&
					col < nations.get(row).size()/5) nations.get(row).set(col, Nation.FIRE);
			}
		}

		return new WorldMap(terrain, nations);
	}

	private static <T> List<List<T>> threshold(float[][] noise,
											   Thresholder<T> t) {

		List<List<T>> thresholded = new ArrayList<List<T>>(noise.length);
		
		for (int row = 0; row < noise.length; row++) {
			thresholded.add(new ArrayList<T>());
			for (int col = 0; col < noise[row].length; col++){
				thresholded.get(row).add(t.threshold(noise[row][col]));
			}
		}
		return thresholded;
	}
}
