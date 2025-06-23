package tile;

import assets.AssetManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static main.Constants.*;

public class TileAnimated extends Tile {

	private final int animationSpeed = 120;
	private int animationCounter = 0;
	private int currentFrame = 0;
	private int totalFrame = 1;
	
	public TileAnimated(BufferedImage tileImage, int tileID, int totalFrame) {
		super(tileImage, tileID);
		this.totalFrame = totalFrame;
	}
	
	public BufferedImage getSpriteAnimation() {
		int frameLocation = ORIGINAL_TILE_SIZE * currentFrame;
		return AssetManager.getSprite(tileImage, frameLocation, 0, ORIGINAL_TILE_SIZE, ORIGINAL_TILE_SIZE);
	}

	@Override
	public void draw(Graphics2D g2, int screenX, int screenY) {
		
		animationCounter++;
		if(animationCounter > animationSpeed) {
			currentFrame = (currentFrame + 1) % totalFrame;
			animationCounter = 0;
		}
		
		g2.drawImage(getSpriteAnimation(), screenX, screenY, ORIGINAL_TILE_SIZE,
				ORIGINAL_TILE_SIZE, null);
	}
}
