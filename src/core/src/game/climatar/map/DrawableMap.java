package game.climatar.map;

import java.util.HashMap;
import java.util.List;

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

class Coordinates<T> {
	public T x;
	public T y;
	public Coordinates(T x, T y) {
		this.x = x;
		this.y = y;
	}
}

abstract class TileRenderer {
	abstract void draw(Coordinates<Integer> coords, Pixmap map);
}

class TerrainTileRenderer extends TileRenderer {
	private Pixmap tilesPixmap;
	private TextureRegion[][] tileRegions;
	private List<List<Integer>> world;
	private int tileSize;

	private Pixmap debug;
	
	public TerrainTileRenderer(List<List<Integer>> world,
							   int tileSize) {
		this.world = world;
		this.tileSize = tileSize;
		// build the tile map with the tile specifications
		Texture tilesTexture = new Texture(Gdx.files.internal("tiles.png"));
		tilesTexture.getTextureData().prepare();
		this.tilesPixmap = tilesTexture.getTextureData().consumePixmap();
		this.tileRegions = TextureRegion.split(tilesTexture, tileSize, tileSize);
		
		if (tilesTexture.getTextureData().disposePixmap()) {
			tilesTexture.dispose();
		}
	}

	public void draw(Coordinates<Integer> coords, Pixmap map) {
		int terrainID = world.get(coords.y).get(coords.x);
		
		// tileID runs 0 through 8, conveniently in the
		// same order as our split tiles texture...
		int ty = (int) terrainID / 3;
		int tx = terrainID % 3;

		TextureRegion tileRegion = tileRegions[ty][tx];

		map.drawPixmap(tilesPixmap,
					   coords.x * tileSize,
					   coords.y * tileSize,
					   tileRegion.getRegionX(),
					   tileRegion.getRegionY(),
					   tileRegion.getRegionWidth(),
					   tileRegion.getRegionHeight());
	}
}

class NationTileRenderer extends TileRenderer {
	private Nation nation;
	private List<List<Nation>> world;
	private Pixmap tilePixmap;
	private int tileSize;
	public NationTileRenderer(Nation nation,
							  List<List<Nation>> world,
							  String textureName,
							  int tileSize) {
		this.nation = nation;
		this.world = world;
		this.tileSize = tileSize;

		Texture tileTexture = new Texture(Gdx.files.internal(textureName));
		tileTexture.getTextureData().prepare();
		this.tilePixmap = tileTexture.getTextureData().consumePixmap();
		
		if (tileTexture.getTextureData().disposePixmap()) {
			tileTexture.dispose();
		}
	}
	
	public void draw(Coordinates<Integer> coords, Pixmap map) {
		// this comment makes this implementation look nicer
		if (world.get(coords.y).get(coords.x) == nation)
			map.drawPixmap(tilePixmap,
						   coords.x * tileSize,
						   coords.y * tileSize,
						   0,
						   0,
						   tileSize,
						   tileSize);
		
	}
}

public class DrawableMap extends Actor {

	private static final int TILE_SIZE = 16;

	private Texture terrain;
	private Texture fire;
	private Texture water;
	private Texture earth;
	private Texture air;

	private HashMap<Nation, Boolean> visibilityLookup;
	
	private Color tint = new Color(Color.WHITE);

	private float scale;
	private Rectangle frame;
	private OrthographicCamera camera;

	public DrawableMap(WorldMap world, float scale) {
		this.scale = scale;
		camera = new OrthographicCamera();
		frame = new Rectangle();

		// set up the renderers
		TerrainTileRenderer terrainRenderer = new TerrainTileRenderer(world.terrain,
																	  TILE_SIZE);
		NationTileRenderer fireNationRenderer = new NationTileRenderer(Nation.FIRE,
																	   world.nations, 
																	   "tile-fire.png",
																	   TILE_SIZE);
		NationTileRenderer waterNationRenderer = new NationTileRenderer(Nation.WATER,
																		world.nations, 
																		"tile-water.png",
																		TILE_SIZE);
		NationTileRenderer earthNationRenderer = new NationTileRenderer(Nation.EARTH,
																		world.nations, 
																		"tile-earth.png",
																		TILE_SIZE);
		NationTileRenderer airNationRenderer = new NationTileRenderer(Nation.AIR,
																	  world.nations, 
																	  "tile-air.png",
																	  TILE_SIZE);

		int mapWidth = world.terrain.get(0).size();
		int mapHeight = world.terrain.size();

		Pixmap terrainMap = new Pixmap(mapWidth * TILE_SIZE, mapHeight * TILE_SIZE, Format.RGB888);
		Pixmap fireNationMap = new Pixmap(mapWidth * TILE_SIZE, mapHeight * TILE_SIZE, Format.RGBA8888);
		Pixmap waterNationMap = new Pixmap(mapWidth * TILE_SIZE, mapHeight * TILE_SIZE, Format.RGBA8888);
		Pixmap earthNationMap = new Pixmap(mapWidth * TILE_SIZE, mapHeight * TILE_SIZE, Format.RGBA8888);
		Pixmap airNationMap = new Pixmap(mapWidth * TILE_SIZE, mapHeight * TILE_SIZE, Format.RGBA8888);

		// reading topleft -> right -> down
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				Coordinates<Integer> coords = new Coordinates<Integer>(x, y);
				terrainRenderer.draw(coords, terrainMap);
				fireNationRenderer.draw(coords, fireNationMap);
				waterNationRenderer.draw(coords, waterNationMap);
				earthNationRenderer.draw(coords, earthNationMap);
				airNationRenderer.draw(coords, airNationMap);
			}
		}

		terrain = new Texture(terrainMap);
		fire = new Texture(fireNationMap);
		water = new Texture(waterNationMap);
		earth = new Texture(earthNationMap);
		air = new Texture(airNationMap);

		// setup visibility lookup
		visibilityLookup = new HashMap<Nation, Boolean>();
		visibilityLookup.put(Nation.FIRE, false);
		visibilityLookup.put(Nation.WATER, false);
		visibilityLookup.put(Nation.EARTH, false);
		visibilityLookup.put(Nation.AIR, false);

		setPosition(0, 0);
		setSize(terrain.getWidth(), terrain.getHeight());
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
		return terrain.getWidth();
	}

	public float getHeight() {
		return terrain.getHeight();
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

	public Boolean getVisibility(Nation nation) {
		return visibilityLookup.get(nation);
	}
	
	public void setVisibility(Nation nation, Boolean isVisible) {
		visibilityLookup.put(nation, isVisible);
	}
	
	public void toggleVisibility(Nation nation) {
		setVisibility(nation, !getVisibility(nation));
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		camera.update();
		Matrix4 projection = batch.getProjectionMatrix();

//		batch.end();

//		Gdx.gl.glViewport((int) frame.x, (int) frame.y, (int) frame.width, (int) frame.height);
//		batch.setProjectionMatrix(camera.combined);
//		batch.begin();

		tint.a = parentAlpha;
		batch.setColor(tint);
		batch.draw(terrain, getX(), getY(), getWidth(), getHeight());

		// draw nation layers
		if (visibilityLookup.get(Nation.FIRE))
			batch.draw(fire, getX(), getY(), getWidth(), getHeight());
		if (visibilityLookup.get(Nation.WATER))
			batch.draw(water, getX(), getY(), getWidth(), getHeight());
		if (visibilityLookup.get(Nation.EARTH))
			batch.draw(earth, getX(), getY(), getWidth(), getHeight());
		if (visibilityLookup.get(Nation.AIR))
			batch.draw(air, getX(), getY(), getWidth(), getHeight());

//		batch.end();
//		batch.setProjectionMatrix(projection);
//		batch.begin();
//		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void dispose() {
		terrain.dispose();
		fire.dispose();
		water.dispose();
		earth.dispose();
		air.dispose();
	}

	public Rectangle getFrame() {
		return frame;
	}
}
