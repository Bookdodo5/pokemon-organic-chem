package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static main.Constants.ORIGINAL_TILE_SIZE;

public class Tile {
	protected BufferedImage tileImage;
	protected int tileID;
	
	public Tile(BufferedImage tileImage, int tileID) {
		this.tileImage = tileImage;
		this.tileID = tileID;
	}

	public int getTileID() {
		return tileID;
	}
	
	public void draw(Graphics2D g2, int screenX, int screenY) {
		if(tileImage == null) return;
		g2.drawImage(tileImage, screenX, screenY, ORIGINAL_TILE_SIZE,
				ORIGINAL_TILE_SIZE, null);
	}
}
