package game.climatar.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.Actor;

// 
public class DrawableMap extends Actor {

    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    
    // 
    public DrawableMap(int[][] tileSpec) {

	float width = Gdx.graphics.getWidth();
	float height = Gdx.graphics.getHeight();
	
	// setup the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
	camera.translate(200, 0);
        camera.update();

	// build the tile map with the tile specifications
	TiledMap map = new TiledMap();
	Texture tiles = new Texture(Gdx.files.internal("tiles.png"));
	TextureRegion[][] splitTiles = TextureRegion.split(tiles, 16, 16);
	MapLayers layers = map.getLayers();

	
	int mapWidth = tileSpec[0].length;
	int mapHeight = tileSpec.length;
	System.out.println(mapWidth);
	TiledMapTileLayer layer = new TiledMapTileLayer(mapWidth, mapHeight, 1, 1);

	// reading topleft -> right -> down
	for (int y = 0; y < mapHeight; y++) {
	    for (int x = 0; x < mapWidth; x++) {
		// indexed by [row][column]
		int tileID = tileSpec[y][x];
		// tileID runs 0 through 8, conveniently in the
		// same order as our split tiles texture...
		int ty = (int) tileID/3;
		int tx = tileID%3;
		
		Cell cell = new Cell();
		cell.setTile(new StaticTiledMapTile(splitTiles[ty][tx]));
		layer.setCell(x, y, cell);
	    }
	}

	layers.add(layer);
	renderer = new OrthogonalTiledMapRenderer(map);
    }

    public void draw (Batch batch, float parentAlpha) {
	Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	camera.zoom = 5f;
	    
	camera.update();
	renderer.setView(camera);
	renderer.render();
    }
}

