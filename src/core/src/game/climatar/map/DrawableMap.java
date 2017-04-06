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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class DrawableMap extends Actor {

	private static final int TILE_SIZE = 16;
	
	private Texture computedMapTexture;
	private Color tint = new Color(Color.WHITE);

	private float scale;
	private Rectangle frame;
	private OrthographicCamera camera;

	public DrawableMap(int[][] tileSpec, float scale) {
		this.scale = scale;
		camera = new OrthographicCamera();
		frame = new Rectangle();
		
		// build the tile map with the tile specifications
		Texture tiles = new Texture(Gdx.files.internal("tiles.png"));
		TextureRegion[][] splitTiles = TextureRegion.split(tiles, TILE_SIZE, TILE_SIZE);

		int mapWidth = tileSpec[0].length;
		int mapHeight = tileSpec.length;

		Pixmap fullMap = new Pixmap(mapWidth * TILE_SIZE, mapHeight * TILE_SIZE, Format.RGB888);

		tiles.getTextureData().prepare();
		Pixmap tileset = tiles.getTextureData().consumePixmap();

		// reading topleft -> right -> down
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				// indexed by [row][column]
				int tileID = tileSpec[y][x];
				// tileID runs 0 through 8, conveniently in the
				// same order as our split tiles texture...
				int ty = (int) tileID / 3;
				int tx = tileID % 3;

				TextureRegion region = splitTiles[ty][tx];

				fullMap.drawPixmap(tileset, x * TILE_SIZE, y * TILE_SIZE, region.getRegionX(), region.getRegionY(),
						region.getRegionWidth(), region.getRegionHeight());
			}
		}

		computedMapTexture = new Texture(fullMap);

		if (tiles.getTextureData().disposePixmap()) {
			tileset.dispose();
		}

		setPosition(0, 0);
		setSize(computedMapTexture.getWidth(), computedMapTexture.getHeight());

		addListener(new DragListener() {
			@Override
			public void drag(InputEvent event, float x, float y, int pointer) {
				float dragDistanceX = getDeltaX();
				float dragDistanceY = getDeltaY();

				float posX = getX();
				float posY = getY();

				float maxWidth = frame.width / getScale();
				float maxHeight = frame.height / getScale();

				float textureWidth = computedMapTexture.getWidth();
				float textureHeight = computedMapTexture.getHeight();

				float newPosY = posY + dragDistanceY;

				if (getVerticalMapSize() == MapSize.SMALLER_THAN_VIEWPORT) {
					if (newPosY < 0)
						newPosY = 0;
					if (newPosY > maxHeight - textureHeight)
						newPosY = maxHeight - textureHeight;

					setY(newPosY);
				} else if (getVerticalMapSize() == MapSize.BIGGER_THAN_VIEWPORT) {
					if (newPosY > 0)
						newPosY = 0;
					if (newPosY < maxHeight - textureHeight)
						newPosY = maxHeight - textureHeight;

					setY(newPosY);
				}

				float newPosX = posX + dragDistanceX;
				if (getHorizontalMapSize() == MapSize.SMALLER_THAN_VIEWPORT) {
					if (newPosX < 0)
						newPosX = 0;
					if (newPosX > maxWidth - textureWidth)
						newPosX = maxWidth - textureWidth;

					setX(newPosX);
				} else if (getHorizontalMapSize() == MapSize.BIGGER_THAN_VIEWPORT) {
					if (newPosX > 0)
						newPosX = 0;
					if (newPosX < maxWidth - textureWidth)
						newPosX = maxWidth - textureWidth;

					setX(newPosX);
				}
			}
		});
		
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
//		camera.position.set(x + width/2, y + height/2, 1);
		camera.position.set(x + width / scale / 2f, y + height / scale / 2f, 0);		
		
		frame.set(x, y, width, height);
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

	public float getWidth() {
		return computedMapTexture.getWidth();
	}

	public float getHeight() {
		return computedMapTexture.getHeight();
	}

	private enum MapSize {
		BIGGER_THAN_VIEWPORT, SMALLER_THAN_VIEWPORT;
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

}
