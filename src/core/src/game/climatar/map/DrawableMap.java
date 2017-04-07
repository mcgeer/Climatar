package game.climatar.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;
import java.util.HashMap;

class Coordinates<T> {
	public T x;
	public T y;
	public Coordinates(T x, T y) {
		this.x = x;
		this.y = y;
	}
}

public class DrawableMap extends Actor {

	private static final int TILE_SIZE = 16;

	private Texture computedMapTexture;
	private Color tint = new Color(Color.WHITE);

	private float scale;
	private Rectangle frame;
	private OrthographicCamera camera;

	public DrawableMap(WorldMap world, float scale) {
		this.scale = scale;
		camera = new OrthographicCamera();
		frame = new Rectangle();

		// build the tile map with the tile specifications
		Texture terrainTiles = new Texture(Gdx.files.internal("tiles.png"));
		TextureRegion[][] terrainSplits = TextureRegion.split(terrainTiles, TILE_SIZE, TILE_SIZE);

		Texture nationTiles = new Texture(Gdx.files.internal("nation-tiles-wide.png"));
		TextureRegion[][] nationSplits = TextureRegion.split(nationTiles, TILE_SIZE, TILE_SIZE);

		int mapWidth = world.terrain.get(0).size();
		int mapHeight = world.terrain.size();
		Pixmap fullMap = new Pixmap(mapWidth * TILE_SIZE, mapHeight * TILE_SIZE, Format.RGB888);

		terrainTiles.getTextureData().prepare();
		Pixmap terrainTileset = terrainTiles.getTextureData().consumePixmap();
		
		nationTiles.getTextureData().prepare();
		Pixmap nationTileset = nationTiles.getTextureData().consumePixmap();

		HashMap<String, Coordinates<Integer>> nationTileLookup = new HashMap<String, Coordinates<Integer>>();
		nationTileLookup.put("Fire", new Coordinates<Integer>(0, 0));
		nationTileLookup.put("Water", new Coordinates<Integer>(1, 0));
		nationTileLookup.put("Earth", new Coordinates<Integer>(1, 1));
		nationTileLookup.put("Air", new Coordinates<Integer>(0, 1));
		nationTileLookup.put("UN", new Coordinates<Integer>(1, 2));

		// reading topleft -> right -> down
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				// indexed by [row][column]

				// draw terrain tiles
				
				int terrainID = world.terrain.get(y).get(x);
				// tileID runs 0 through 8, conveniently in the
				// same order as our split tiles texture...
				int ty = (int) terrainID / 3;
				int tx = terrainID % 3;

				TextureRegion terrainRegion = terrainSplits[ty][tx];

				fullMap.drawPixmap(terrainTileset,
								   x * TILE_SIZE,
								   y * TILE_SIZE,
								   terrainRegion.getRegionX(),
								   terrainRegion.getRegionY(),
								   terrainRegion.getRegionWidth(),
								   terrainRegion.getRegionHeight());

				// draw nation border tiles

				Nation nation = world.nations.get(y).get(x);
				Coordinates<Integer> c = nationTileLookup.get(nation.getName());

				TextureRegion nationRegion = nationSplits[c.y][c.x];

				fullMap.drawPixmap(nationTileset,
								   x * TILE_SIZE,
								   y * TILE_SIZE,
								   nationRegion.getRegionX(),
								   nationRegion.getRegionY(),
								   nationRegion.getRegionWidth(),
								   nationRegion.getRegionHeight());

			}
		}

		computedMapTexture = new Texture(fullMap);

		if (terrainTiles.getTextureData().disposePixmap()) {
			terrainTileset.dispose();
		}

		if (nationTiles.getTextureData().disposePixmap()) {
			nationTileset.dispose();
		}

		setPosition(0, 0);
		setSize(computedMapTexture.getWidth(), computedMapTexture.getHeight());

		setFrame(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float getScale() {
		return this.scale;
	}

	public void setFrame(float x, float y, float width, float height) {
		camera.viewportWidth = width / scale;
		camera.viewportHeight = height / scale;
		// camera.position.set(x + width/2, y + height/2, 1);
		camera.position.set(x + width / scale / 2f, y + height / scale / 2f, 0);

		frame.set(x, y, width, height);
	}

	public float getWidth() {
		return computedMapTexture.getWidth();
	}

	public float getHeight() {
		return computedMapTexture.getHeight();
	}

	public enum MapSize {
		BIGGER_THAN_VIEWPORT, SMALLER_THAN_VIEWPORT;
	}

	public MapSize getHorizontalMapSize() {
		if (frame.width / scale >= getWidth()) {
			return MapSize.SMALLER_THAN_VIEWPORT;
		} else {
			return MapSize.BIGGER_THAN_VIEWPORT;
		}
	}

	public MapSize getVerticalMapSize() {
		if (frame.height / scale >= getHeight()) {
			return MapSize.SMALLER_THAN_VIEWPORT;
		} else {
			return MapSize.BIGGER_THAN_VIEWPORT;
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		camera.update();
		Matrix4 projection = batch.getProjectionMatrix();

		batch.end();

		Gdx.gl.glViewport((int) frame.x, (int) frame.y, (int) frame.width, (int) frame.height);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		tint.a = parentAlpha;
		batch.setColor(tint);
		batch.draw(computedMapTexture, getX(), getY(), getWidth(), getHeight());

		batch.end();
		batch.setProjectionMatrix(projection);
		batch.begin();
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void dispose() {
		computedMapTexture.dispose();
	}

	public Rectangle getFrame() {
		return frame;
	}

}
