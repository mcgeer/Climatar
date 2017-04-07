package game.climatar.map;

import com.badlogic.gdx.math.MathUtils;
import java.util.Arrays;

/**
 * Implements the midpoint displacement fractal noise technique in 2
 * dimensions. Based heavily off of the implementation found at
 * http://javagamexyz.blogspot.ca/2013/03/terrain-generation.html
 */
public class MidpointDisplacement {

	public static float[][] noise(int n, int wmult, int hmult, float smoothness) {

		// get the dimensions of the noise
		int power = (int) Math.pow(2, n);
		int width = wmult * power + 1;
		int height = hmult * power + 1;

		// initialize noise array
		float[][] noise = new float[width][height];

		int step = power / 2;
		float sum;
		int count;

		// h determines the fineness of the scale it is working on. After every
		// step, h
		// is decreased by a factor of "smoothness"
		float h = 1;

		// Initialize the grid points
		for (int i = 0; i < width; i += 2 * step) {
			for (int j = 0; j < height; j += 2 * step) {
				noise[i][j] = MathUtils.random(2 * h);
			}
		}

		// Do the rest of the magic
		while (step > 0) {
			// Diamond step
			for (int x = step; x < width; x += 2 * step) {
				for (int y = step; y < height; y += 2 * step) {
					sum = noise[x - step][y - step] + // down-left
						noise[x - step][y + step] + // up-left
						noise[x + step][y - step] + // down-right
						noise[x + step][y + step]; // up-right
					noise[x][y] = sum / 4 + MathUtils.random(-h, h);
				}
			}

			// Square step
			for (int x = 0; x < width; x += step) {
				for (int y = step * (1 - (x / step) % 2); y < height; y += 2 * step) {
					sum = 0;
					count = 0;
					if (x - step >= 0) {
						sum += noise[x - step][y];
						count++;
					}
					if (x + step < width) {
						sum += noise[x + step][y];
						count++;
					}
					if (y - step >= 0) {
						sum += noise[x][y - step];
						count++;
					}
					if (y + step < height) {
						sum += noise[x][y + step];
						count++;
					}
					if (count > 0)
						noise[x][y] = sum / count + MathUtils.random(-h, h);
					else
						noise[x][y] = 0;
				}

			}
			h /= smoothness;
			step /= 2;
		}

		// Normalize
		float max = Float.MIN_VALUE;
		float min = Float.MAX_VALUE;
		for (float[] row : noise) {
			for (float d : row) {
				if (d > max)
					max = d;
				if (d < min)
					min = d;
			}
		}

		// Use the thresholds to fill in the return map
		for (int row = 0; row < noise.length; row++) {
			for (int col = 0; col < noise[row].length; col++) {
				noise[row][col] = (noise[row][col] - min) / (max - min);
			}
		}

		return noise;
	}
}
