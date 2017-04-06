package game.climatar.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class DrawableMap extends Actor {

	private static final int TILE_SIZE = 16;

	private Texture computedMapTexture;
	private Color tint = new Color(Color.WHITE);

	public DrawableMap(int[][] tileSpec) {
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
				
				float maxWidth = Gdx.graphics.getWidth();
				float maxHeight = Gdx.graphics.getHeight();

				float textureWidth = computedMapTexture.getWidth();
				float textureHeight = computedMapTexture.getHeight();
				
				if(canMoveHorizontally()) {
					float newPosX = posX + dragDistanceX;
					if(newPosX < 0) newPosX = 0;
					if(newPosX > maxWidth - textureWidth) newPosX = maxWidth- textureWidth;
					
					setX(newPosX);
				}
				
				if(canMoveVertically()) {
					float newPosY = posY + dragDistanceY;
					if(newPosY < 0) newPosY = 0;
					if(newPosY > maxHeight - textureHeight) newPosY = maxHeight - textureHeight;
					
					setY(newPosY);
				}
				
			}
		});
	}
	
	public boolean canMoveVertically() {
		return true;
	}
	
	public boolean canMoveHorizontally() {
		// TODO: this should have some logic where 
		return true;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		tint.a = parentAlpha;
		batch.setColor(tint);
		batch.draw(computedMapTexture, getX(), getY(), getWidth(), getHeight());
	}

	public void dispose() {
		computedMapTexture.dispose();
	}
	
}
